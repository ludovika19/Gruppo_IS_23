package boundary.utente;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ricetta extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JSpinner spinnerTempo;
    private DefaultTableModel tableModel;
    private JTable table;
    private List<Ingrediente> listaIngredienti = new ArrayList<>();
    
    private DefaultTableModel tagModel;
    private JTable tagTable;
    private List<String> listaTag = new ArrayList<>();
    private JTextField textField_1;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Ricetta frame = new Ricetta();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public Ricetta() {
        setTitle("Pubblica ricetta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 690, 541);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel LabelTitolo = new JLabel("Titolo");
        LabelTitolo.setBounds(39, 43, 45, 13);
        contentPane.add(LabelTitolo);

        textField = new JTextField();
        textField.setBounds(94, 40, 200, 19);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel LabelTempo = new JLabel("Tempo");
        LabelTempo.setBounds(336, 43, 45, 13);
        contentPane.add(LabelTempo);

        spinnerTempo = new JSpinner();
        spinnerTempo.setBounds(391, 40, 50, 20);
        contentPane.add(spinnerTempo);

        // ---- Tabelle ingredienti ----
        tableModel = new DefaultTableModel(new Object[] { "Ingrediente", "Quantità" }, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return true;
            }
        };
        table = new JTable(tableModel);
        table.getColumnModel().getColumn(1)
             .setCellEditor(new DefaultCellEditor(new JTextField()));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(24, 259, 330, 200);
        contentPane.add(scrollPane);

        // Pulsante per aggiungere riga
        JButton btnAggiungi = new JButton("Aggiungi ingrediente");
        btnAggiungi.setBounds(24, 469, 160, 25);
        btnAggiungi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.addRow(new Object[] { "", 0.0 });
            }
        });
        contentPane.add(btnAggiungi);

        // Pulsante per salvare gli ingredienti
        JButton btnSalva = new JButton("Salva ingredienti");
        btnSalva.setBounds(194, 469, 160, 25);
        btnSalva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaIngredienti.clear();
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    String nome = tableModel.getValueAt(i, 0).toString().trim();
                    Object val = tableModel.getValueAt(i, 1);
                    double q;
                    try {
                        q = Double.parseDouble(val.toString());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                            Ricetta.this,
                            "Quantità non valida alla riga " + (i + 1),
                            "Errore",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                    if (!nome.isEmpty()) {
                        listaIngredienti.add(new Ingrediente(nome, q));
                    }
                }
                JOptionPane.showMessageDialog(
                    Ricetta.this,
                    "Salvati " + listaIngredienti.size() + " ingredienti."
                );
                // Qui puoi aggiungere logica per persistere su DB o file
            }
        });
        contentPane.add(btnSalva);
        
        JCheckBox CheckBoxPrivata = new JCheckBox("Privata");
        CheckBoxPrivata.setBounds(468, 39, 93, 21);
        contentPane.add(CheckBoxPrivata);
        
        String testo = "Descrizione ricetta...";
        JTextArea textArea = new JTextArea(testo);
        textArea.setBounds(24, 84, 330, 165);
        contentPane.add(textArea);
        textArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (textArea.getText().equals(testo)) {
                    textArea.setText("");
                }
            }
            public void focusLost(java.awt.event.FocusEvent e) {
                if (textArea.getText().isEmpty()) {
                    textArea.setText(testo);
                }
            }
        });
        
        
        
     // Tabella per i tag
        tagModel = new DefaultTableModel(new Object[] { "Tag" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        tagTable = new JTable(tagModel);
        JScrollPane tagScroll = new JScrollPane(tagTable);
        tagScroll.setBounds(532, 88, 93, 161); // posizionamento più compatto
        contentPane.add(tagScroll);

        // Bottone per aggiungere tag
        JButton btnAggiungiTag = new JButton("Aggiungi tag");
        btnAggiungiTag.setBounds(402, 113, 120, 25);
        btnAggiungiTag.addActionListener(e -> tagModel.addRow(new Object[] { "" }));
        contentPane.add(btnAggiungiTag);

        // Bottone per salvare tag
        JButton btnSalvaTag = new JButton("Salva tag");
        btnSalvaTag.setBounds(402, 148, 120, 25);
        btnSalvaTag.addActionListener(e -> {
            listaTag.clear();
            for (int i = 0; i < tagModel.getRowCount(); i++) {
                String tag = tagModel.getValueAt(i, 0).toString().trim();
                if (!tag.isEmpty()) {
                    listaTag.add(tag);
                }
            }
            JOptionPane.showMessageDialog(this, "Salvati " + listaTag.size() + " tag.");
        });
        contentPane.add(btnSalvaTag);
        
        JLabel LabelRaccolta = new JLabel("Raccolta");
        LabelRaccolta.setBounds(401, 271, 57, 13);
        contentPane.add(LabelRaccolta);
        
        textField_1 = new JTextField();
        textField_1.setBounds(468, 268, 115, 19);
        contentPane.add(textField_1);
        textField_1.setColumns(10);
        
        String test1= "Descrizione raccolta...";
        JTextArea textArea_1 = new JTextArea(test1);
        textArea_1.setBounds(408, 317, 217, 142);
        contentPane.add(textArea_1);
        textArea_1.setVisible(false);
        textArea_1.addFocusListener(new java.awt.event.FocusAdapter() {
        	public void focusGained(java.awt.event.FocusEvent e) {
                if (textArea_1.getText().equals(test1)) {
                	textArea_1.setText("");
                }
            }
            public void focusLost(java.awt.event.FocusEvent e) {
                if (textArea_1.getText().isEmpty()) {
                	textArea_1.setText(test1);
                }
            }
        });
        
        JButton ButtonSalva = new JButton("Salva");
        ButtonSalva.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		Ricetta.this.setVisible(false);
        	}
        });
        ButtonSalva.setBounds(581, 10, 85, 21);
        contentPane.add(ButtonSalva);
        
        JButton ButtonIndietro = new JButton("Indietro");
        ButtonIndietro.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		Ricetta.this.setVisible(false);
        	}
        });
        ButtonIndietro.setBounds(581, 39, 85, 21);
        contentPane.add(ButtonIndietro);

        // (Eventuali altri componenti già presenti)
        // ...
    }

    // Classe interna per rappresentare un ingrediente
    private static class Ingrediente {
        private String nome;
        private double quantità;

        public Ingrediente(String nome, double quantità) {
            this.nome = nome;
            this.quantità = quantità;
        }

        public String getNome() {
            return nome;
        }

        public double getQuantità() {
            return quantità;
        }

        @Override
        public String toString() {
            return nome + " – " + quantità;
        }
    }
}

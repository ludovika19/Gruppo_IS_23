package boundary.utente;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Feed extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Feed frame = new Feed();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Feed() {
    	this.setTitle("Feed");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // --- ComboBox con ActionListener ---
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(
            new String[] { "Pubblica ricetta", "Modifica profilo", "Sezione personale" }
        ));
        comboBox.setToolTipText("scegli");
        comboBox.setBounds(29, 25, 124, 21);
        contentPane.add(comboBox);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String scelta = (String) comboBox.getSelectedItem();
                switch (scelta) {
                    case "Pubblica ricetta":
                    	new Ricetta().setVisible(true);
                		// qui la tua logica per pubblicare ricetta
                        break;
                    case "Modifica profilo":
                    	new Utente().setVisible(true);
                		Feed.this.setVisible(false);
                        // qui la tua logica per modifica profilo
                        break;
                    case "Sezione personale":
                    	new SezionePersonale().setVisible(true);
                		Feed.this.setVisible(false);
                        // qui la tua logica per sezione personale
                        break;
                }
            }
        });

        // --- JList con ListSelectionListener ---
        JList<String> list = new JList<>();
        list.setModel(new AbstractListModel<String>() {
            String[] values = new String[] {
                "Pubblica ricetta",
                "Sezione personale",
                "Modifica profilo"
            };
            @Override public int getSize() { return values.length; }
            @Override public String getElementAt(int index) { return values[index]; }
        });
        list.setBounds(32, 56, 394, 185);
        contentPane.add(list);

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // evitiamo i due eventi (caricamento e fine selezione)
                if (!e.getValueIsAdjusting()) {
                    String scelta = list.getSelectedValue();
                    switch (scelta) {
                        case "Pubblica ricetta":
                            System.out.println("JList: Pubblica ricetta");
                            // logica pubblica ricetta
                            break;
                        case "Modifica profilo":
                            System.out.println("JList: Modifica profilo");
                            // logica modifica profilo
                            break;
                        case "Sezione personale":
                            System.out.println("JList: Sezione personale");
                            // logica sezione personale
                            break;
                    }
                }
            }
        });
    }
}

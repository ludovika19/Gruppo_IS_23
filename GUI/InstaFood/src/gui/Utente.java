package gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;

public class Utente extends JFrame {
    private JPanel contentPane;
    private JButton btnCaricaFile;
    private JTextField txtPercorso;
    private JButton ButtonInvia;
    private JTextField textNome;
    private JTextField textCognome;
    private JLabel LabelEmail;
    private JTextField textEmail;
    private JLabel LabelPassword;
    private JPasswordField textPassword;
    private JTextField textLuogoN;
    private JTextField textDataN;

    public Utente() {
        setTitle("Utente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 608, 509);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtPercorso = new JTextField();
        txtPercorso.setBounds(40, 381, 242, 25);
        contentPane.add(txtPercorso);
        txtPercorso.setColumns(10);

        btnCaricaFile = new JButton("Carica Immagine");
        btnCaricaFile.setBounds(304, 381, 132, 25);
        contentPane.add(btnCaricaFile);
        
        ButtonInvia = new JButton("Invia");
        ButtonInvia.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		new Home().setVisible(true);
        		Utente.this.setVisible(false);
        	}
        });
        ButtonInvia.setBounds(241, 431, 87, 25);
        contentPane.add(ButtonInvia);
        
        JLabel LabelNome = new JLabel("Nome");
        LabelNome.setBounds(40, 21, 45, 13);
        contentPane.add(LabelNome);
        
        textNome = new JTextField();
        textNome.setBounds(156, 18, 122, 19);
        contentPane.add(textNome);
        textNome.setColumns(10);
        
        JLabel LabelCognome = new JLabel("Cognome");
        LabelCognome.setBounds(330, 21, 56, 13);
        contentPane.add(LabelCognome);
        
        textCognome = new JTextField();
        textCognome.setBounds(427, 18, 122, 19);
        contentPane.add(textCognome);
        textCognome.setColumns(10);
        
        LabelEmail = new JLabel("E-mail");
        LabelEmail.setBounds(40, 74, 45, 13);
        contentPane.add(LabelEmail);
        
        textEmail = new JTextField();
        textEmail.setBounds(156, 71, 122, 19);
        contentPane.add(textEmail);
        textEmail.setColumns(10);
        
        LabelPassword = new JLabel("Password");
        LabelPassword.setBounds(330, 74, 56, 13);
        contentPane.add(LabelPassword);
        
        textPassword = new JPasswordField();
        textPassword.setBounds(427, 71, 122, 19);
        contentPane.add(textPassword);
        
        JLabel LabelLuogoN = new JLabel("Luogo di nascita");
        LabelLuogoN.setBounds(40, 128, 106, 13);
        contentPane.add(LabelLuogoN);
        
        textLuogoN = new JTextField();
        textLuogoN.setBounds(156, 125, 122, 19);
        contentPane.add(textLuogoN);
        textLuogoN.setColumns(10);
        
        JLabel LabelDataN = new JLabel("Data di nascita");
        LabelDataN.setBounds(330, 128, 87, 13);
        contentPane.add(LabelDataN);
        
        textDataN = new JTextField();
        textDataN.setBounds(427, 125, 122, 19);
        contentPane.add(textDataN);
        textDataN.setColumns(10);
        
        JTextArea textBiografia = new JTextArea();
        textBiografia.setToolTipText("");
        textBiografia.setBounds(40, 177, 509, 165);
        contentPane.add(textBiografia);

        // Azione sul click del pulsante carica file
        btnCaricaFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Immagini", "jpg", "jpeg", "png", "gif"));
                int result = fileChooser.showOpenDialog(Utente.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    txtPercorso.setText(selectedFile.getAbsolutePath());
 
                    // Salva il contenuto del file in un array di byte
                    try {
                    	// 1. Leggo tutti i byte del file
                        byte[] imgBytes = Files.readAllBytes(selectedFile.toPath());

                        // 2. Apro la connessione al DB

                        // 3. Preparo lo statement per salvare il file
                        
                        //da inserire solo che inserisco tutti i dati nel db
                        //JOptionPane.showMessageDialog(Utente.this,"Immagine salvata nel database!");
                        
                    } //catch (IOException | SQLException ex) {
                      catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(Utente.this,
                            "Errore nel salvataggio dell’immagine:\n" + ex.getMessage(),
                            "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Utente frame = new Utente();
            frame.setVisible(true);
        });
    }
}

package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Apprezzamento extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Apprezzamento frame = new Apprezzamento();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Apprezzamento() {
        setTitle("Apprezzamento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 360);
        setLocationRelativeTo(null);

        // 1) Usiamo BorderLayout sul contentPane
        JPanel contentPane = new JPanel(new BorderLayout(5,5));
        setContentPane(contentPane);

        // --- Nord: like e contatore ---
        JPanel north = new JPanel();
        north.add(new JLabel("Like ricetta:"));
        textField = new JTextField("0", 5);
        textField.setEditable(false);
        north.add(textField);
        north.add(new JCheckBox("Like"));
        contentPane.add(north, BorderLayout.NORTH);

        // --- Centro: area di testo descrittiva (se ce n'è) ---
        JTextArea textAreaTesto = new JTextArea();
        textAreaTesto.setLineWrap(true);
        textAreaTesto.setWrapStyleWord(true);
        contentPane.add(new JScrollPane(textAreaTesto), BorderLayout.CENTER);

        // --- Sud: commento + bottone ---
        JPanel south = new JPanel(new BorderLayout(5,5));
        String placeholder = "Commento";
        JTextArea textAreaCommento = new JTextArea(5, 30);
        textAreaCommento.setText(placeholder);
        textAreaCommento.setLineWrap(true);
        textAreaCommento.setWrapStyleWord(true);
        textAreaCommento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (textAreaCommento.getText().equals(placeholder))
                    textAreaCommento.setText("");
            }
            public void focusLost(java.awt.event.FocusEvent e) {
                if (textAreaCommento.getText().trim().isEmpty())
                    textAreaCommento.setText(placeholder);
            }
        });
        // JScrollPane con scroll verticale AS_NEEDED
        JScrollPane scrollCommento = new JScrollPane(textAreaCommento,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        south.add(scrollCommento, BorderLayout.CENTER);

        JButton btnInvia = new JButton("Invia");
        btnInvia.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		new Feed().setVisible(true);
        		Apprezzamento.this.setVisible(false);
        	}
        });
        south.add(btnInvia, BorderLayout.EAST);

        contentPane.add(south, BorderLayout.SOUTH);
    }
}

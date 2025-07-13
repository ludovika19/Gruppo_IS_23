package boundary.utente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SezionePersonale extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldCommenti;
	private JTextField textFieldLike;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SezionePersonale frame = new SezionePersonale();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SezionePersonale() {
		this.setTitle("Sezione Personale");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LabelDesc = new JLabel("Statistiche delle tue ricette");
		LabelDesc.setBounds(132, 10, 154, 13);
		contentPane.add(LabelDesc);
		
		JLabel LabelLike = new JLabel("Like");
		LabelLike.setBounds(51, 48, 45, 13);
		contentPane.add(LabelLike);
		
		JLabel LabelCommenti = new JLabel("Commenti");
		LabelCommenti.setBounds(51, 99, 58, 13);
		contentPane.add(LabelCommenti);
		
		JLabel LabelRicetta = new JLabel("Ricetta con più like");
		LabelRicetta.setBounds(51, 159, 109, 13);
		contentPane.add(LabelRicetta);
		
		textFieldCommenti = new JTextField();
		textFieldCommenti.setEditable(false);
		textFieldCommenti.setBounds(170, 96, 52, 19);
		contentPane.add(textFieldCommenti);
		textFieldCommenti.setColumns(10);
		
		textFieldLike = new JTextField();
		textFieldLike.setEditable(false);
		textFieldLike.setBounds(170, 45, 52, 19);
		contentPane.add(textFieldLike);
		textFieldLike.setColumns(10);
		
		JTextArea textAreaRicetta = new JTextArea();
		textAreaRicetta.setEditable(false);
		textAreaRicetta.setBounds(170, 153, 235, 100);
		contentPane.add(textAreaRicetta);
		
		JButton ButtonIndietro = new JButton("Indietro");
		ButtonIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Feed().setVisible(true);
        		SezionePersonale.this.setVisible(false);
			}
		});
		ButtonIndietro.setBounds(341, 44, 85, 21);
		contentPane.add(ButtonIndietro);
	}

}

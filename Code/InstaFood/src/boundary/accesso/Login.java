package boundary.accesso;

import boundary.utente.Feed;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textEmail;
	private JTextField textField;
	private JLabel MsgPassword;
	private JButton ButtonInvia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Log-in");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 348, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LabelEmail = new JLabel("Email");
		LabelEmail.setBounds(24, 20, 45, 13);
		contentPane.add(LabelEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(94, 17, 120, 19);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
		JLabel LabelPassword = new JLabel("Password");
		LabelPassword.setBounds(24, 73, 60, 13);
		contentPane.add(LabelPassword);
		
		textField = new JTextField();
		textField.setBounds(94, 70, 120, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel MsgEmail = new JLabel("");
		MsgEmail.setEnabled(false);
		MsgEmail.setBounds(221, 20, 92, 13);
		contentPane.add(MsgEmail);
		
		MsgPassword = new JLabel("");
		MsgPassword.setBounds(221, 73, 92, 13);
		contentPane.add(MsgPassword);
		
		ButtonInvia = new JButton("Invia");
		ButtonInvia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Feed().setVisible(true);
        		Login.this.setVisible(false);
			}
		});
		ButtonInvia.setBounds(129, 130, 85, 21);
		contentPane.add(ButtonInvia);
	}

}

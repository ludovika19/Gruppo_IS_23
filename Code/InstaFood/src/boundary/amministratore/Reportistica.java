package boundary.amministratore;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Reportistica extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldData1;
	private JTextField textFieldData2;
	private JTextField textFieldLike;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reportistica frame = new Reportistica();
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
	public Reportistica() {
		this.setTitle("Reportistica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LabelData1 = new JLabel("Data inizio");
		LabelData1.setBounds(20, 20, 61, 13);
		contentPane.add(LabelData1);
		
		JLabel LabelData2 = new JLabel("Data fine");
		LabelData2.setBounds(20, 54, 61, 13);
		contentPane.add(LabelData2);
		
		textFieldData1 = new JTextField();
		textFieldData1.setBounds(104, 17, 96, 19);
		contentPane.add(textFieldData1);
		textFieldData1.setColumns(10);
		
		textFieldData2 = new JTextField();
		textFieldData2.setBounds(104, 51, 96, 19);
		contentPane.add(textFieldData2);
		textFieldData2.setColumns(10);
		
		JButton ButtonInvio = new JButton("Invio");
		ButtonInvio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		ButtonInvio.setBounds(20, 88, 85, 21);
		contentPane.add(ButtonInvio);
		
		JLabel LabelLike = new JLabel("Like");
		LabelLike.setBounds(245, 39, 45, 13);
		contentPane.add(LabelLike);
		
		textFieldLike = new JTextField();
		textFieldLike.setBounds(300, 36, 49, 19);
		contentPane.add(textFieldLike);
		textFieldLike.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(20, 119, 122, 134);
		contentPane.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(152, 119, 122, 134);
		contentPane.add(textArea_1);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(284, 119, 122, 134);
		contentPane.add(textArea_2);
	}

}

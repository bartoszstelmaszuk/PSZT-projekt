import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class GUIMenu {

	private JFrame frame;
	private JTextField textField;
	private Vector<Integer> netData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIMenu window = new GUIMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		netData = new Vector<Integer>();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(350, 92, 65, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					netData.add(Integer.parseInt(textField.getText()));
					frame.dispose();
					NetSpecification netSpec = new NetSpecification(netData);
					netSpec.setVisible(true);
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Enter proper value!");
				}
			}
		});
		btnOk.setBounds(159, 162, 117, 29);
		frame.getContentPane().add(btnOk);
		
		JLabel lblNewLabel = new JLabel("Wybierz liczbę neuronów w pierwszej warstwie: ");
		lblNewLabel.setBounds(29, 98, 309, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPsztSie = new JLabel("PSZT - Sieć Neuronowa");
		lblPsztSie.setBounds(148, 6, 146, 16);
		frame.getContentPane().add(lblPsztSie);
		
	}
}

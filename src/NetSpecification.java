import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class NetSpecification extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NetSpecification frame = new NetSpecification();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	 * Create the frame.
	 */
	public NetSpecification(final Vector<Integer> netData) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		int netNr = netData.size()+1;
		JLabel lblNewLabel = new JLabel("Nr warstwy - " + netNr);
		lblNewLabel.setBounds(69, 38, 124, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Dodaj warstwę");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent agr0) {
				try{
					netData.add(Integer.parseInt(textField.getText()));
					dispose();
					NetSpecification netSpec = new NetSpecification(netData);
					netSpec.setVisible(true);
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Enter proper value!");
				}
			}
		});
		btnNewButton.setBounds(252, 185, 147, 57);
		contentPane.add(btnNewButton);
		
		JButton btnZakoczIPrzejd = new JButton("Rozpoczni symulacje");
		btnZakoczIPrzejd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Simulation simul = new Simulation(netData);
				simul.setVisible(true);
			}
		});
		
		btnZakoczIPrzejd.setHorizontalAlignment(SwingConstants.LEADING);
		btnZakoczIPrzejd.setBounds(58, 185, 155, 57);
		contentPane.add(btnZakoczIPrzejd);
		
		textField = new JTextField();
		textField.setBounds(283, 88, 134, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblWybierzLiczbeNeuronw = new JLabel("Wybierz liczbe neuronów w warstwie:");
		lblWybierzLiczbeNeuronw.setBounds(23, 94, 248, 16);
		contentPane.add(lblWybierzLiczbeNeuronw);
	}
}

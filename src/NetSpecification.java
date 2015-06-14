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


	public NetSpecification(final Vector<Integer> netData) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		int netNr = netData.size()+1;
		JLabel lblNewLabel = new JLabel("Nr warstwy - " + netNr);
		lblNewLabel.setBounds(125, 21, 231, 16);
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
				netData.add(2);
				dispose();
				Simulation simul = new Simulation(netData);
				simul.setVisible(true);
			}
		});
		
		btnZakoczIPrzejd.setHorizontalAlignment(SwingConstants.LEADING);
		btnZakoczIPrzejd.setBounds(58, 185, 155, 57);
		contentPane.add(btnZakoczIPrzejd);
		
		textField = new JTextField();
		textField.setBounds(306, 119, 80, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblWybierzLiczbeNeuronw = new JLabel("Wybierz liczbe neuronów w warstwie:");
		lblWybierzLiczbeNeuronw.setBounds(23, 125, 248, 16);
		contentPane.add(lblWybierzLiczbeNeuronw);
		
		JLabel lblWektorNeuronwW = new JLabel("Wektor neuronów w poszczególnych warstwach sieci:");
		lblWektorNeuronwW.setBounds(16, 49, 357, 28);
		contentPane.add(lblWektorNeuronwW);
		
		String netDataDupl= new String();
		netDataDupl="<";
		for (int i=0; i<netData.size(); i++)
		{
			netDataDupl+=netData.elementAt(i);
			if(i!= netData.size()-1)	netDataDupl+=", ";
		}
		netDataDupl+=">";
		JLabel lblNewLabel_1 = new JLabel(netDataDupl);
		lblNewLabel_1.setBounds(23, 89, 376, 16);
		contentPane.add(lblNewLabel_1);
	}
}

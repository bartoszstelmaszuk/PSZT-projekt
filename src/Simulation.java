import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Simulation extends JFrame {

	private JPanel contentPane;
	static Graph graph;

	public Simulation(Vector <Integer> netData) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 768, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 768, 1000));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		graph=new Graph(netData); 
		graph.setBounds(0, 0,768, 1000);
		add(graph);
	}

}

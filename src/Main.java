import java.awt.Color;

import javax.swing.JFrame;


public class Main {

	/**
	 * @param args
	 */
	static JFrame frame;
	static Graph graph;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		frame=new JFrame();
		frame.setBounds(0,0,768, 1000);
		frame.setVisible(true);
		graph=new Graph(); 
		graph.setBounds(0, 0,768, 1000);
		//frame.setBackground(Color.BLUE);
		frame.add(graph);
		
	}

}

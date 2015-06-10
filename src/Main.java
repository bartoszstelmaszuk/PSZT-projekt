import java.awt.Color;
import java.util.Vector;

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
		/* Wektor tworzący struktórę sieci neuronowej*/
		Vector<Integer> webConfig = new Vector<Integer>();
		webConfig.add(10);
		webConfig.add(20);
		webConfig.add(2);
		
		
		
		
		graph=new Graph(webConfig); 
		graph.setBounds(0, 0,768, 1000);
		//frame.setBackground(Color.BLUE);
		frame.add(graph);
		
		
	}

}

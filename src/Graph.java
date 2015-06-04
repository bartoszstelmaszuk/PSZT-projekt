import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;


import javax.swing.JComponent;
import javax.swing.JPanel;


public class Graph extends JPanel implements ActionListener, MouseListener {

	Vector<Point3> clicsF;
	Vector<Point3> clicsS;
	//Network network;
	NewNet network;
	NewNet network2;
	public Graph() {
		// TODO Auto-generated constructor stub
		addMouseListener(this);
		clicsF=new Vector<Point3>();
		clicsS=new Vector<Point3>();
		//network=new Network(clicsF);
		network=new NewNet(clicsF);
		network2=new NewNet(clicsS);
	}
	public void paint(Graphics g){
		g.clearRect(0, 0, this.getParent().getHeight(), this.getParent().getWidth());
		for(int i=0; i<clicsF.size(); i++){
			drawCircle(g, (int)clicsF.get(i).x, (int)clicsF.get(i).y, Color.BLUE);
		}
		network.mixup();
		Vector<Point3> line=new Vector<Point3>();
		for(int i=0; i<this.getWidth(); i=i+5){
			line.add(new Point3(i, network.getY(i)));
			
		}
			
		for(int i=0; i<line.size()-1 && line.size()>1 ; i++){
			g.drawLine((int)line.get(i).x, (int)line.get(i).y, (int)line.get(i+1).x, (int)(int)line.get(i+1).y);
		}
		
		line.clear();
		for(int i=0; i<clicsS.size(); i++){
			drawCircle(g, (int)clicsS.get(i).x, (int)clicsS.get(i).y, Color.RED);
		}
		
		network2.mixup();
		for(int i=0; i<this.getWidth(); i=i+5){
			line.add(new Point3(i, network2.getY(i)));
			
		}
		
		for(int i=0; i<line.size()-1 && line.size()>1 ; i++){
			g.drawLine((int)line.get(i).x, (int)line.get(i).y, (int)line.get(i+1).x, (int)(int)line.get(i+1).y);
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(clicsF.size()>clicsS.size()){
			clicsS.add(new Point3(clicsF.lastElement(), arg0.getY()));
		}else{
			clicsF.add(new Point3((double)arg0.getX(), (double)arg0.getY()));
		}
        repaint();
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void drawCircle(Graphics g, int x, int y, Color c) {
        g.drawOval(x, y, 4, 4);
        g.setColor(c);
        g.fillOval(x, y, 4, 4);
    }

}

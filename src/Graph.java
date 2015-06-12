import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.util.concurrent.TimeUnit;


import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Graph extends JPanel implements ActionListener, MouseListener{

	Vector<Point3> clicsF;
	Vector<Point3> clicsS;
	Timer timer;
	NewNet network;
	//NewNet network2;
	public Graph(Vector<Integer> webConf) {
		// TODO Auto-generated constructor stub
		addMouseListener(this);
		clicsF=new Vector<Point3>();
		clicsS=new Vector<Point3>();
		
		network=new NewNet(webConf,clicsF, clicsS);
		 Thread t = new Thread(network, "My Thread");
		 t.start();
		 timer=new Timer(33, this);
		 timer.start();
		//network2=new NewNet(clicsS, clicsF);
	}
	public void paint(Graphics g){
		g.clearRect(0, 0, this.getParent().getHeight(), this.getParent().getWidth());
		for(int i=0; i<clicsF.size(); i++){
			drawCircle(g, (int)clicsF.get(i).x, (int)clicsF.get(i).y, Color.BLUE);
		}
		for(int i=0; i<clicsS.size(); i++){
			drawCircle(g, (int)clicsS.get(i).x, (int)clicsS.get(i).y, Color.RED);
		}
		//network.mixup();
		Vector<Point3> line =network.getAprksymation(this.getWidth(), 10, 0);
		for(int i=0; ((i<line.size()-1) &&line.size()>1) ; i++){
			g.setColor(Color.BLUE);
			g.drawLine((int)line.get(i).x, (int)line.get(i).y,
					(int)line.get(i+1).x, (int)(int)line.get(i+1).y);
			g.setColor(Color.RED);
			g.drawLine((int)line.get(i).x, (int)line.get(i).z, 
					(int)line.get(i+1).x, (int)(int)line.get(i+1).z);
		}
		
		/*
		Vector<Point3> lineFirst=new Vector<Point3>();
		Vector<Point3> lineSecond=new Vector<Point3>();
		for(int i=0; i<this.getWidth(); i=i+1){
			Point3 v=network.getY(i);
			lineFirst.add(new Point3(i, v.x));
			lineSecond.add(new Point3(i, v.y));			
		}
			
		for(int i=0; ((i<lineFirst.size()-1)&&i<lineFirst.size()-1) && lineFirst.size()>1 &&lineSecond.size()>1 ; i++){
			g.setColor(Color.BLUE);
			g.drawLine((int)lineFirst.get(i).x, (int)lineFirst.get(i).y,
					(int)lineFirst.get(i+1).x, (int)(int)lineFirst.get(i+1).y);
			g.setColor(Color.RED);
			g.drawLine((int)lineSecond.get(i).x, (int)lineSecond.get(i).y, 
					(int)lineSecond.get(i+1).x, (int)(int)lineSecond.get(i+1).y);
		}*/
		
	
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
		if(arg0.getSource()==timer){
		      repaint();// this will call at every 1 second
		    }
		
	}
	
	public void drawCircle(Graphics g, int x, int y, Color c) {
		g.setColor(c);
		g.drawOval(x-2, y-2, 4, 4);
        g.fillOval(x-2, y-2, 4, 4);
    }
	

}

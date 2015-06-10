import java.util.Vector;


public class Point3 {
	public double x;
	public double y;
	public double z;
	
	
	Point3(double a, double b, double c){
		x=a;
		y=b;
		z=c;
	}
	Point3(Point3 P, double b){
		x=P.x;
		y=b;
		z=0;
	}
	Point3(double a, double b){
		x=a;
		y=b;
		z=0;
	}
	
	void SetYZ(Point3 point){
		y=point.x;
		z=point.y;
	}
}

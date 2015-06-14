import java.util.Random;
import java.util.Vector;
import java.util.concurrent.TimeUnit;


public class NewNet implements Runnable{
		Vector<Layer> web;
		double abberation;
		double abberationRed;
		double abberationBlue;
		double avr_abberation;
		private Vector<Point3> clics;
		private Vector<Point3> clicsSec;
		private double  delta=0.01;
		private double del=0.1;

		
NewNet(Vector<Integer> layerDescr, Vector<Point3> cl, Vector<Point3> cr){
	web=new Vector<Layer>();
	int inp=1;
	for(int i=0; i<layerDescr.size();i++){
		web.add(new Layer(layerDescr.get(i), inp));
		inp=layerDescr.get(i);
	}
	
	clics= cl;
	clicsSec=cr;
}		
NewNet(Vector<Point3> cl, Vector<Point3> cr){
	web=new Vector<Layer>();
	web.add(new Layer(20, 1));
	//web.add(new Layer(20, 30));
	web.add(new Layer(20, 20));
	web.add(new Layer(2, 20));
	clics= cl;
	clicsSec=cr;
}

public synchronized Vector<Point3> getAprksymation(int length, int step, int var){
	Vector<Point3> line=new Vector<Point3>();
		if(var==0){
			if(step<1) step=1;
			
			for(int i=0; i<length; i=i+step){
				line.add(new Point3(i, 0,0));
				line.lastElement().SetYZ(getY(i));
				
			//	line.add((getY(i)));
			
			}
		}else if(var==2){
		//	newMixup();
			mixup();
		//grad();
		}
		
		return line;
}

Point3 getY(int x){ 
			
			Vector<Double> out;
			Vector<Double> in=new Vector<Double>();
			in.clear();
			in.add((double)x);
			//Vector<Double> last=new Vector<Double>();
	//****//
			out=web.firstElement().calulateLayer(in);
			in.clear();
				for(int j=0; j<out.size(); j++){
					in.add(out.get(j));
				}
			
			for(int i=1; i<web.size()-1; i++){

				out=web.get(i).calulateLayer(in);
				in.clear();
				for(int j=0; j<out.size(); j++){
				
					in.add(out.get(j));
					
				}
			
			}
			
			
				out=web.lastElement().calulateLayer(in);
				//out=web.lastElement().calulateLayerLinear(in);
				double sumaY1=0;
				double sumaY2=0;
				if(out.size()==2){
					sumaY1=out.get(0);
					sumaY2=out.get(1);
					
				}else{
					for(int j=0; j<out.size(); j++){
						sumaY1+=out.get(j);
						sumaY2+=out.get(j);
					}
				}
				
			Point3 ret=new Point3(sumaY1, sumaY2);
			
		
			return ret;
}
void addLearningPoint(Point3 p){
	clics.add(p);
}
void countError(){
	abberation=0.0;
	abberationRed=0.0;
	abberationBlue=0.0;
	if(clics.size()>0){
		//System.out.println("_________________________________");
		Point3 val;
		double e, f;
		for(int i=0; i< clics.size(); i++){
			val=getY((int)clics.get(i).x);
			e=Math.abs(clics.get(i).y-val.x);
			if(i<clicsSec.size()){
				f=Math.abs(clicsSec.get(i).y-val.y);
			}else{f=0;}
			abberation+=e*e+f*f;
			abberationBlue+=e*e;
			abberationRed+=f*f;
	
		}
		avr_abberation=abberation/(clics.size()+clicsSec.size());
	}
	
}
void setWeigth(int a, int b, int c, double w){
	web.get(a).setLayerINeuronWeigthJ(b, c, w);
}
double getWeigth(Vector<Vector<Vector<Double>>> we, int a, int b, int c){
	if(a<we.size()&&b<we.get(a).size()&&c<we.get(a).get(b).size()){
		return we.get(a).get(b).get(c);
	}else{
		return 0.0;
	}
}

double getWeigth(int a, int b, int c){
	if(a<web.size()&&b<web.get(a).line.size()&&c<web.get(a).line.get(b).weigth.size()){
		return web.get(a).line.get(b).weigth.get(c);
	}else{
		System.out.println("Error in getWeigth");
		return 0.0;
	}
}

Point3 errorJ(Vector<Point3> point){
	Point3 ret=new Point3(0.0,0.0,0.0);
	for(int i=0; i<point.size(); i++){
		Point3 value=getY((int)point.get(i).x);
		
	//	ret.y+=(point.get(i).y-value.x);
	//	ret.z+=(point.get(i).z-value.y);
		ret.y+=(point.get(i).y-value.x)*(point.get(i).y-value.x);
		ret.z+=(point.get(i).z-value.y)*(point.get(i).z-value.y);
	}
	ret.x=ret.y+ret.z;
	return ret;
}
double getF(Neuron x){
	return x.output;
}

double getdF(Neuron x){
	return (getF(x)*(1-getF(x)));
}

void backProps(){
	Vector<Double> gamma_old=new Vector<Double>();
	Vector<Double> gamma_new=new Vector<Double>();
	Random randomGenerator = new Random();
		
	 if(clicsSec.size()>0 ){
		 int w=Math.abs(randomGenerator.nextInt()% clicsSec.size());
		
		Point3 point=new Point3(clics.get(w).x,clics.get(w).y,clicsSec.get(w).y);
		Point3 value=getY((int)point.x);
		Vector<Vector<Vector<Double>>> we=new Vector<Vector<Vector<Double>>>();
		for(int i=0; i<web.size(); i++){
			we.add(web.get(i).getLayerWeigthsVectors());
		}
		double dJb =point.y-value.y;
		double dJr =point.z-value.z;
	 }
}

void newMixup(){
	 Random randomGenerator = new Random();
	
	 if(clicsSec.size()>0 ){
		 
		 Vector<Vector<Vector<Double>>> we=new Vector<Vector<Vector<Double>>>();
			
			
			for(int i=0; i<web.size(); i++){
				we.add(web.get(i).getLayerWeigthsVectors());
			}
		 
		 
		Vector<Point3> select=new Vector<Point3>();
		//int size=(int)(clicsSec.size());
		int size=1;
		//for(int i=0; i<size; i++){
			int w=Math.abs(randomGenerator.nextInt()% clicsSec.size());
			Point3 point=new Point3(clics.get(w).x,clics.get(w).y,clicsSec.get(w).y);
		//	if(select.contains(point)){
		//		i--;
		//	}else{
				select.add(point);
		//	}
		//}
		
		Point3 value=errorJ(select);
		
		double dJb =value.y;
		double dJr =value.z;
	//	System.out.println( dJb);
	//	System.out.println( dJr);
		double n=0.01;
		Vector<Double> gamma_old=new Vector<Double>();
		Vector<Double> gamma_new=new Vector<Double>();
		gamma_new.add(dJb);
		gamma_new.add(dJr);//pomnożyć razy pochodną
		
		for(int i=web.size()-1; i>=0; i--){
			for(int j=0;j<web.get(i).size(); j++){
				double dF=getdF(web.get(i).line.get(j));
			//	dF=(web.get(i).line.get(j).getdF());
					if(i<web.size()-1){
						double weigthSum=0.0;
						for(int l=0; l<gamma_old.size(); l++){
							weigthSum+=getWeigth(we, i+1, l, j)*gamma_old.get(l);
						//	weigthSum+=web.get(i+1).line.get(l).weigth.get(j)*gamma_old.get(l);
						}
						//gamma_new.add(weigthSum*dF);
						gamma_new.add(j, weigthSum*dF);
					}
				for(int k=0; k<web.get(i).layerNuronSize(j); k++){
					double weigth=getWeigth(i, j, k);
					double in;
					if(k<web.get(i).layerNuronSize(j)-1){
						in=web.get(i).line.get(j).input.get(k);
					//	in=1;
					/*	System.out.print(i);
						System.out.print(" ");
						System.out.print(j);
						System.out.print(" ");
						System.out.print(k);
						System.out.print(" ");
						System.out.println(in);*/
						
						web.get(i).setLayerINeuronWeigthJ(j, k, weigth+n*gamma_new.get(j)*in);
					}else{
						in=1;
						web.get(i).setLayerINeuronWeigthJ(j, k, weigth+n*gamma_new.get(j)*in);
					}
					System.out.print(k);
					System.out.print(" ");
					System.out.print(weigth);
					System.out.print(" ");
					System.out.print(gamma_new.get(j));
					System.out.print(" ");
					
					System.out.println(in);
				}
				
			}
			
			gamma_old=new Vector<Double>(gamma_new);
		//	gamma_new.clear();
			
			
		}
		
		 
		countError();
	//	System.out.println(Double.toString(abberation)+"  "+Double.toString(abberationBlue)+"  "+Double.toString(abberationRed));
	 }
	/* System.out.println("");
	 for(int x=0; x<web.size(); x++){
			for(int y=0; y<web.get(x).line.size(); y++){
				for(int z=0; z<web.get(x).layerNuronSize(y); z++){
					System.out.print(web.get(x).line.get(y).weigth.get(z));
				}
			}
		}
	 System.out.println(" >>");*/
}
void grad(){
	 Random randomGenerator = new Random();
	
	 if(clicsSec.size()>0 ){
		 
		 Vector<Vector<Vector<Double>>> we=new Vector<Vector<Vector<Double>>>();
			
			
			for(int i=0; i<web.size(); i++){
				we.add(web.get(i).getLayerWeigthsVectors());
			}
		 
		 
		Vector<Point3> select=new Vector<Point3>();
		int size=(int)(clicsSec.size());
		
		for(int i=0; i<size; i++){
			int w=Math.abs(randomGenerator.nextInt()% clicsSec.size());
			Point3 point=new Point3(clics.get(w).x,clics.get(w).y,clicsSec.get(w).y);
			if(select.contains(point)){
				i--;
			}else{
				select.add(point);
			}
		}
		
			

	
		
		double n=1;
		for(int i=0; i<web.size(); i++){
			for(int j=0;j<web.get(i).size(); j++){
			
				for(int k=0; k<web.get(i).layerNuronSize(j)-1; k++){
					double weigth=getWeigth(i, j, k);
					System.out.print(weigth);
					System.out.print("  ");
					//Point3 value=errorJ(select);
					countError();
					double J1=abberation;
					//web.get(i).setLayerINeuronWeigthJ(j, k, (weigth+delta));
					web.get(i).line.get(j).weigth.set(k, (weigth+delta));
					System.out.print(web.get(i).line.get(j).weigth.get(k));
					System.out.print("  ");
					//value=errorJ(select);
					countError();
					double J2=abberation;
					double dJ=(J2-J1)/delta;
					System.out.print(J1);
					System.out.print(" ");
					System.out.print(J2);
					System.out.print(" ");
					System.out.println(dJ);
					
						web.get(i).setLayerINeuronWeigthJ(j, k, weigth+n*dJ);
				}
				
			}
				
			
		}
		
		 
		countError();
	//	System.out.println(Double.toString(abberation)+"  "+Double.toString(abberationBlue)+"  "+Double.toString(abberationRed));
	 }
	/* System.out.println("");
	 for(int x=0; x<web.size(); x++){
			for(int y=0; y<web.get(x).line.size(); y++){
				for(int z=0; z<web.get(x).layerNuronSize(y); z++){
					System.out.print(web.get(x).line.get(y).weigth.get(z));
				}
			}
		}
	 System.out.println(" >>");*/
}



void mixup(){
		countError();
	if(clics.size()>0){
		
		Vector<Vector<Vector<Double>>> we=new Vector<Vector<Vector<Double>>>();
			for(int i=0; i<web.size(); i++){
				we.add(web.get(i).getLayerWeigthsVectors());
			}
		int tmp;
		int var=0;
		for(int z=0; z<1; z++){
			tmp=0;
			for(int i=0; i<web.size(); i++){
				for(int j=0;j<web.get(i).size(); j++){
					for(int k=0; k<web.get(i).layerNuronSize(j); k++){
						var=0;
						double abb=abberation;
						double weigth=getWeigth(we, i, j, k);
						if (web.get(i).line.get(j).getNeuronDir()==0){
							web.get(i).setLayerINeuronWeigthJ(j, k, weigth+del);
							countError();
					
							if(abb>abberation){
								var=1;
								tmp=1;
								abb=abberation;
								web.get(i).line.get(j).setNeuronDir(0);
								//System.out.println("+1");
							//	--k;
								continue;
							}
							web.get(i).setLayerINeuronWeigthJ(j, k, (weigth-del));
							countError();
					
							if(abb>abberation){
								var=2;
								tmp=2;
								abb=abberation;
								web.get(i).line.get(j).setNeuronDir(1);
								//System.out.println("-1");
							//	--k;
								continue;
							}
							if(var==0){
								//web.get(i).setLayerINeuronWeigthJ(j, k, getWeigth(we, i, j, k));
								web.get(i).setLayerINeuronWeigthJ(j, k, weigth);
								countError();
							//	System.out.println("0");
								continue;
							}
						}else{
							
							web.get(i).setLayerINeuronWeigthJ(j, k, (weigth-del));
							countError();
					
							if(abb>abberation){
								var=2;
								tmp=2;
								abb=abberation;
								web.get(i).line.get(j).setNeuronDir(1);
								//System.out.println("-1");
							//	--k;
								continue;
							}
							
							
							
							web.get(i).setLayerINeuronWeigthJ(j, k, weigth+del);
							countError();
					
							if(abb>abberation){
								var=1;
								tmp=1;
								abb=abberation;
								web.get(i).line.get(j).setNeuronDir(0);
								//System.out.println("+1");
							//	--k;
								continue;
							}
							
							if(var==0){
								//web.get(i).setLayerINeuronWeigthJ(j, k, getWeigth(we, i, j, k));
								web.get(i).setLayerINeuronWeigthJ(j, k, weigth);
								countError();
							//	System.out.println("0");
								continue;
							}
							
						}
						
					}
				}
			}
			if(tmp==0){break;}
		}
	
	}
}

@Override
public void run() {
	while (true){
		getAprksymation(0, 0, 2);
		try {
		TimeUnit.MILLISECONDS.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
}
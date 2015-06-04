import java.util.Vector;


public class NewNet {
		Vector<Layer> web;
		double abberation;
		double avr_abberation;
		private Vector<Point3> clics;

/*NewNet(){
	web=new Vector<Layer>();
	web.add(new Layer(20, 1));
	web.add(new Layer(20, 20));
	web.add(new Layer(1, 20));
	abberation=0.0;
	avr_abberation=0.0;
	
	clics= new Vector<Point3>();
}*/
NewNet(Vector<Point3> cl){
	web=new Vector<Layer>();
	web.add(new Layer(30, 1));
	web.add(new Layer(20, 30));
	web.add(new Layer(20, 20));
	web.add(new Layer(1, 20));
	clics= cl;
}

double getY(int x){ 
			double sum=0;
			double suma=0;
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
				for(int j=0; j<out.size(); j++){
					suma+=out.get(j);
				}
			
			//System.out.println(sum);*/
			return suma;
}
void addLearningPoint(Point3 p){
	clics.add(p);
}
void countError(){
	abberation=0.0;
	if(clics.size()>0){
		//System.out.println("_________________________________");
		
		for(int i=0; i< clics.size(); i++){
			double e=Math.abs(clics.get(i).y-getY((int)clics.get(i).x));
			abberation+=e*e;
			//String s=Double.toString(clics.get(i).y)+"+"+Double.toString(getY((int)clics.get(i).x))+"="+Double.toString(e);
			//double s=getY((int)clics.get(i).x);
		//	System.out.println(s);
		}
		avr_abberation=abberation/clics.size();
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
void mixup(){
		countError();
	if(clics.size()>0){
		
		Vector<Vector<Vector<Double>>> we=new Vector<Vector<Vector<Double>>>();
			for(int i=0; i<web.size(); i++){
				we.add(web.get(i).getLayerWeigthsVectors());
			}
		int tmp;
		int var=0;
		for(int z=0; z<10; z++){
			tmp=0;
			for(int i=0; i<web.size(); i++){
				for(int j=0;j<web.get(i).size(); j++){
					for(int k=0; k<web.get(i).layerNuronSize(j); k++){
						var=0;
						double abb=abberation;
						double weigth=getWeigth(we, i, j, k);
						if (web.get(i).line.get(j).getNeuronDir()==0){
							web.get(i).setLayerINeuronWeigthJ(j, k, weigth+0.2);
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
							web.get(i).setLayerINeuronWeigthJ(j, k, (weigth-0.2));
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
							
							web.get(i).setLayerINeuronWeigthJ(j, k, (weigth-0.2));
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
							
							
							
							web.get(i).setLayerINeuronWeigthJ(j, k, weigth+0.2);
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
}
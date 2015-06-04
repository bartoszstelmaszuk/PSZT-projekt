import java.util.Vector;


public class Layer {
	Vector<Neuron> line;
	//Vector<Double> input;
/*	Layer(int count){
		line= new Vector<Neuron>();
		for(int j=0; j<count; j++){
			line.add(new Neuron());
		}
	}*/
	
/*	Layer(int count, Vector<Double> i ){
		line= new Vector<Neuron>();{
			for(int j=0; j<count; j++){
				line.add(new Neuron());
			}
		}
		input =i;
	}*/
	
	Layer(int count, int i ){
		line= new Vector<Neuron>();
			for(int j=0; j<count; j++){
				line.add(new Neuron(i));
			}
	}
	
	/*Vector<Double> calulateLayer(int x){
		Vector<Double> out=new Vector<Double>();
		for(int i=0; i<line.size(); i++){
		
			out.add( line.get(i).countY(x));
		}
		return out;
	}*/
	public Vector<Double> calulateLayer(Vector<Double> x){
		Vector<Double> out=new Vector<Double>();
		for(int i=0; i<line.size(); i++){
		
			out.add( line.get(i).countY(x));
		}
		return out;
	}
	
	Vector<Vector<Double>> getLayerWeigthsVectors(){
		Vector<Vector<Double>> var=new Vector<Vector<Double>>(line.size());
			for (int i=0; i<line.size(); i++){
				var.add(line.get(i).weigth);
			}
			return var;
	}
	int size(){return line.size();}
	int layerNuronSize(int i){return line.get(i).neuronSize();}
	void setLayerINeuronWeigthJ(int i, int j, double w){ 
			if(i<line.size()){
				if(j<line.get(i).neuronSize()){
				//	System.out.println("layerNuronSize"+Integer.toString(i)+" "+Integer.toString(j)+" "+Double.toString(w));
				//	System.out.println(line.get(i).weigth.get(j));
					line.get(i).weigth.set(j, w);
				//	System.out.println(line.get(i).weigth.get(j));
				}else {System.out.println("Error in Layer setLayerINeuronWeigthJ - out of range in weigth size");}
			}else {System.out.println("Error in Layer setLayerINeuronWeigthJ - out of range in line size");}
		}
	public void setLayerNeuronDir(int i, int dir){ line.get(i).setNeuronDir(dir);}
	public int getLayerNeuronDir(int i){ return line.get(i).getNeuronDir();}
}

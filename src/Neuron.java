import java.util.Random;
import java.util.Vector;
public class Neuron {
	
	Vector<Double> weigth;
	Vector<Double> input;
	double activation;
	double output;
	int dir;
	
	Neuron(int x){
		weigth=new Vector<Double>();
		dir=0;
		 Random randomGenerator = new Random();
		 for(int i=0; i<x; i++){
			double w=randomGenerator.nextDouble()%1;
			weigth.add((double) (w));
		 }
		 double w=randomGenerator.nextDouble()%1;
		 weigth.add((double) (w));
	
		activation=0;
	}
	private void calculate_output(){
		output= (1/(1+ Math.pow(Math.E,-activation)));
		
	}
	private void calulate_activation(Vector<Double> in){
		activation=0;
		for(int i=0; i<in.size(); i++){
			activation+=weigth.get(i)*in.get(i);
		}
			activation+=weigth.lastElement();
	}
	
	public double countY(Vector<Double> x){
		input=(Vector<Double>)x.clone();
		if(input.size()==neuronSize()){
			calulate_activation(input);
			calculate_output();
		}else{
			System.out.println("Neuron -- input and weigth vec size difrent"+Integer.toString(x.size())+" "
					+Integer.toString(neuronSize())) ;
		}
			return output;
	}
	public double countLinear(Vector<Double> x){
		input=(Vector<Double>)x.clone();
		double out=0.0;
		if(x.size()==neuronSize()){
			for(int i=0; i<x.size(); i++){
				out+=input.get(i)*weigth.get(i);
			}
				out+=weigth.lastElement();
		}else{
			System.out.println("Neuron -- input and weigth vec size difrent"+Integer.toString(x.size())+" "
					+Integer.toString(neuronSize())) ;
		}
			return out;
	}

	
	private void update(Vector<Double> in){
		calulate_activation(in);
		calculate_output();
	}
	public int neuronSize(){ return weigth.size()-1;}
	
	public int getNeuronDir(){return dir;}
	public void setNeuronDir(int i){ dir=1;}
	
}

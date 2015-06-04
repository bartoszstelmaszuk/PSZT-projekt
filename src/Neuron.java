import java.util.Random;
import java.util.Vector;
public class Neuron {
	
	Vector<Double> weigth;
	double activation;
	double output;
	int dir;
	/*Neuron(){
		weigth=new Vector<Double>();
		 Random randomGenerator = new Random();
		 double w=randomGenerator.nextDouble();
		weigth.add((double) (20));
		activation=0;
	}*/
	Neuron(int x){
		weigth=new Vector<Double>();
		dir=0;
		 Random randomGenerator = new Random();
		 for(int i=0; i<x; i++){
			double w=randomGenerator.nextDouble();
			weigth.add((double) (w));
		 }
	
		activation=0;
	}
	private void calculate_output(){
		output= 50+(1000/(1+ Math.pow(Math.E,-activation/700)));
		
	}
	private void calulate_activation(Vector<Double> input){
		activation=0;
		for(int i=0; i<input.size(); i++){
			activation+=weigth.get(i)*input.get(i);
		}
			activation+=1;
	}
	
/*	void calulate_activation(double input){
			
			activation=weigth.get(0)*input;
		
	}*/
	public double countY(Vector<Double> x){
		if(x.size()==neuronSize()){
			calulate_activation(x);
			calculate_output();
		}else{
			System.out.println("Neuron -- input and weigth vec size difrent"+Integer.toString(x.size())+" "
					+Integer.toString(neuronSize())) ;
		}
			return output;
	}
/*	double countY(int x){
		calulate_activation(x);
		calculate_output();
		return output;
	}*/
	
	private void update(Vector<Double> input){
		calulate_activation(input);
		calculate_output();
	}
	public int neuronSize(){ return weigth.size();}
	
	public int getNeuronDir(){return dir;}
	public void setNeuronDir(int i){ dir=1;}
	
}

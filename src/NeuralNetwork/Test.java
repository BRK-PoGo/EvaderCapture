package NeuralNetwork;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		GA ga = new GA(2, 2, 2);
		System.out.println(ga.distance(ga.getPopulation().get(0), ga.getPopulation().get(1)));
		int i=0;
		for(Network n:ga.getPopulation()){
			i++;
			n.write("file"+i);
		}
		
	}

}

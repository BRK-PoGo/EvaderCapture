package NeuralNetwork;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		Network net = new Network("Test1");
		double[] in = {100, 100};
		double[] out = net.execute(in);
		for(int i=0;i<out.length;i++){
			System.out.println(out[i]);
		}
		
	}

}

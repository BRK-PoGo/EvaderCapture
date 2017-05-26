package NeuralNetwork;

public class or implements Fitness {

	public double getFitness(Network net) {
		//run 4 possible input combinations
		double outputError = 0;
		double[] input = new double[2];
		input[0]=0;
		input[1]=0;
		double error = Math.abs(net.execute(input)[0]);
		outputError += error ;//1 (0,0)
		net.clearMemory();
		double temp= net.execute(input)[0];
		net.clearMemory();
		input[0]=1;
		error = Math.abs(net.execute(input)[0]-1);
		outputError += error;//2 (1,0)
		net.clearMemory();
		temp= net.execute(input)[0];
		net.clearMemory();
		input[1]=1;
		error = Math.abs(net.execute(input)[0]-1);
		outputError += error;//3 (1,1)
		net.clearMemory();
		temp= net.execute(input)[0];
		net.clearMemory();
		input[0]=0;
		error = Math.abs(net.execute(input)[0]-1);
		outputError += error;//4 (0,1)
		net.clearMemory();
		temp= net.execute(input)[0];
		net.clearMemory();
		double fitness = Math.pow(4-outputError,2);
		net.setFitness(fitness);
		return fitness;
	}

}

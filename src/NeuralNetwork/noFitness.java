package NeuralNetwork;

public class noFitness implements Fitness {

	@Override
	public double getFitness(Network net) {
		System.err.println("NO FITNESS SELECTED");
		return 0;
	}

}

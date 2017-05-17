package NeuralNetwork;

public class Tester {
	public static void main(String[] args) {
		GA ga = new GA("testing",100,2,1);
		ga.setFitness(new xor());
		ga.evolve(16);
		Network net = new Network("testing/Champions/champion0");
		Fitness xor = new xor();
		System.out.println(xor.getFitness(net));
	}
}

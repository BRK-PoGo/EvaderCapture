package NeuralNetwork;

import java.util.Random;

import javax.swing.JFrame;

import game.Evader;
import game.GamePanel;
import game.Graph;
import game.MainGameLoop;
import game.Pursuer;

public class Tester {
	public static void main(String[] args) {
		GA ga = new GA("testing",100,2,1);
		ga.setFitness(new or());
		ga.evolve(16);
		Network net = new Network("champion");
		Fitness fitness = new xor();
		System.out.println(fitness.getFitness(net));
	}
}

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
//		GA ga = new GA("testing",100,2,1);
//		ga.setFitness(new or());
//		ga.evolve(16);
//		Network net = new Network("champion");
//		Fitness xor = new xor();
//		System.out.println(xor.getFitness(net));
		GamePanel panel = new GamePanel();
		Graph g = new Graph(30, 30, 30);
		panel.setGraph(g);
		Random r = new Random();
		for(int i=0;i<=190;i++){
			g.addWall(r.nextInt(g.getNodeGrid().length), r.nextInt(g.getNodeGrid()[0].length));
		if(i==190){
			g.addEntity(new Evader(g.getNodeGrid()[r.nextInt(g.getNodeGrid().length)][r.nextInt(g.getNodeGrid()[0].length)],100, new game.Random()));
			g.addEntity(new Pursuer(g.getNodeGrid()[r.nextInt(g.getNodeGrid().length)][r.nextInt(g.getNodeGrid()[0].length)],100, new game.Random()));

		}
		}
		JFrame f = new JFrame();
		f.setSize(1000, 1000);
		f.add(panel);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		MainGameLoop mg = new MainGameLoop(panel);
		mg.gameLoop();
	}
}

package NeuralNetwork;

import game.GamePanel;

public class EvadeCaptureNN implements Fitness{
	private final int TESTS=5;
	private GamePanel gamePanel;

	public EvadeCaptureNN(){
		super();
		
	}
	public double getFitness(Network net) {
		double fitness = 0;
		for(int i=0;i<TESTS;i++){
			fitness= gamePanel.getFitness(net);
		}
		return fitness/TESTS;
	}
	public void setGamePanel(GamePanel p){
		gamePanel = p;
	}

}

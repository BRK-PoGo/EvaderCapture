package gameOld;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main{
	private static int[] RESOLUTION = {600, 600};//try also {600, 600} OR {1024, 768} OR {800, 600} OR {1920, 1080}
	private static  JFrame FRAME;
	private static  GamePanel GAME_PANEL;
	private static MainGameLoop loop;
	
	public static void main(String[] args){						//Main method  ********
		FRAME =  MakeMainFrame();								//Create frame
		GAME_PANEL = new GamePanel();							//Create GamePanel
		FRAME.add(GAME_PANEL);									//add GamePanel
		loop = new MainGameLoop(GAME_PANEL);					//Make a new Game Loop, Using the GamePanel
	}
	private static JFrame MakeMainFrame(){
		JFrame frame = new JFrame("Pursuit-Escape Task Simulator.");
		frame.setVisible(true);
		frame.setSize(RESOLUTION[0], RESOLUTION[1]);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		return frame;
		
	}
	public static int[] getRESOLUTION() {
		return RESOLUTION;
	}
	public static JFrame getFRAME() {
		return FRAME;
	}
	public static GamePanel getGAME_PANEL() {
		return GAME_PANEL;
	}
	public static MainGameLoop getLoop() {
		return loop;
	}
	
}



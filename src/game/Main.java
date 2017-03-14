package game;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main{
	public static void main(String[] args){						//Main method  ********
		GameFrame game = new GameFrame();
		game.setVisible(true);
		while(true){
			game.repaint();
		}
	}
}



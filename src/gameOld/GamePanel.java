package gameOld;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	public GamePanel(){
		
		super();
		setVisible(true);
		initializeGUI();
	}
	private void initializeGUI() {
		//gui components, here
		setLayout(new BorderLayout());			//set a border layout.
		Menu menu = new Menu();
		TopController tc = new TopController();
		this.add(menu,BorderLayout.WEST);
		this.add(tc, BorderLayout.NORTH);
		
			
	}
	public void addTopController(){
		
	}
	public void removeTopController(){
		
	}
	public void addMenu(){
		
	}
	public void removeMenu(){
		
	}
	public void update() {
		
	}
}

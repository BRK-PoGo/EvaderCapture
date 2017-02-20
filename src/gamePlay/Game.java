package gamePlay;

import java.util.ArrayList;

import gui.DesignPanel;
import gui.MainFrame;
import updateable.Updateable;
import watchable.Watchable;

public class Game implements Updateable{

	Grid grid = new Grid();
	MainFrame frame;
	ArrayList<Watchable> watchables = new ArrayList<>();
	
	public Game() {
		DesignPanel panel = new DesignPanel(this);
		watchables.add(panel);
		frame = new MainFrame(panel);
		frame.showPanel();
	}
	
	public void updateGrid(int x, int y) {
		grid.updateGrid(x, y);
	}

	@Override
	public void update(int x, int y) {
		updateGrid(x, y);
		for (Watchable watch : watchables) {
			watch.update(grid.getGrid());
		}
	}


}

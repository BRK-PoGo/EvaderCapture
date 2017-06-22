package game;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Loader {
	public Graph LoadGraph(GamePanel parent) {
		SaverGraph saverGraph = null;
		JFileChooser c = new JFileChooser();
		int rVal = c.showOpenDialog(parent);
	    if (rVal == JFileChooser.APPROVE_OPTION) {
	        String fileName = c.getSelectedFile().getName();
	        String pathName = c.getCurrentDirectory().toString();
	        String dirName = pathName + "\\" + fileName;
	        try (ObjectInputStream ois =
					new ObjectInputStream(new FileInputStream(dirName))) {

				saverGraph = (SaverGraph) ois.readObject();

			} catch (Exception ex) {
				ex.printStackTrace();
			}
	    }
	    
	    return createGraph(saverGraph);
	}
	
	private Graph createGraph(SaverGraph saverGraph) {
		if (saverGraph == null) return null;
		String[][] copyGrid = saverGraph.getGrid();
		Graph graph = new Graph(copyGrid.length, copyGrid[0].length, 20);
		for (int x = 0; x < copyGrid.length; x++) {
			for (int y = 0; y < copyGrid[0].length; y++) {
				if (copyGrid[x][y].equals("wall")) graph.addWall(x, y);
				else if (copyGrid[x][y].equals("evader")) graph.addEvader(x, y);
				else if (copyGrid[x][y].equals("pursuer")) graph.addPurs(x, y);
			}
		}
		return graph;
	}
}

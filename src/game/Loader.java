package game;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Loader {
	public Graph LoadGraph(GamePanel parent) {
		Graph graph = null;
		JFileChooser c = new JFileChooser();
		int rVal = c.showOpenDialog(parent);
	    if (rVal == JFileChooser.APPROVE_OPTION) {
	        String fileName = c.getSelectedFile().getName() + ".ser";
	        String pathName = c.getCurrentDirectory().toString();
	        String dirName = pathName + "\\" + fileName;
	        try (ObjectInputStream ois =
					new ObjectInputStream(new FileInputStream(dirName))) {

				graph = (Graph) ois.readObject();
				System.out.println("Done");

			} catch (Exception ex) {
				ex.printStackTrace();
			}
	    }
	    return graph;
	}
}

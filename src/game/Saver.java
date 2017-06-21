package game;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Saver {
	
	public void saveGraph(JFrame parent, Graph graph) {
		JFileChooser c = new JFileChooser();
		int rVal = c.showSaveDialog(parent);
	    if (rVal == JFileChooser.APPROVE_OPTION) {
	        String fileName = c.getSelectedFile().getName() + ".ser";
	        String pathName = c.getCurrentDirectory().toString();
	        String dirName = pathName + "\\" + fileName;
	        try (ObjectOutputStream oos =
					new ObjectOutputStream(new FileOutputStream(dirName))) {

				oos.writeObject(graph);
				System.out.println("Done");

			} catch (Exception ex) {
				ex.printStackTrace();
			}
	    }
	}
	
}

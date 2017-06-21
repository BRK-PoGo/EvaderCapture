package game;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Saver {
	
	public void saveGraph(GamePanel parent, Graph graph) {
		JFileChooser c = new JFileChooser();
		int rVal = c.showSaveDialog(parent);
	    if (rVal == JFileChooser.APPROVE_OPTION) {
	        String fileName = c.getSelectedFile().getName() + ".ser";
	        System.out.println("fileName: " + fileName);
	        String pathName = c.getCurrentDirectory().toString();
	        System.out.println("pathName: " + pathName);
	        String dirName = pathName + "\\" + fileName;
	        System.out.println("dirName: " + dirName);
	        try (ObjectOutputStream oos =
					new ObjectOutputStream(new FileOutputStream(dirName))) {
	        	System.out.println("open stream");
				oos.writeObject(graph);
				System.out.println("Done");

			} catch (Exception ex) {
				ex.printStackTrace();
			}
	    }
	}
	
}

package AI; 

import java.util.ArrayList;

import game.Entity;
import game.Graph;
public class Tree {
	private ArrayList<ArrayList<Leaf>> levels;
	private Graph graph;

	public Tree(Entity startingEntity, Graph g) {
		graph = g;
		ArrayList<Leaf> root = new ArrayList<Leaf>();
		root.add(new Leaf(startingEntity.getNode(),startingEntity.getDirtyClean()));
		levels.add(root);
	}

	public ArrayList<Leaf> getLevel(int i) {
		
		return levels.get(i);
	}

}

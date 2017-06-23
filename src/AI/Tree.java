package AI; 

import java.util.ArrayList;

import game.Entity;
import game.Graph;
import game.Node;
public class Tree {
	private ArrayList<ArrayList<Leaf>> levels = new ArrayList<ArrayList<Leaf>>();
	private Graph graph;

	public Tree(Entity startingEntity, Graph g, int depth) {
		graph = g;
		ArrayList<Leaf> root = new ArrayList<Leaf>();
		root.add(new Leaf(startingEntity.getNode(),startingEntity.getDirtyClean(),startingEntity.getDir()));
		levels.add(root);
		for(int i=0;i<depth;i++){
			ArrayList<Leaf> levelI = new ArrayList<Leaf>();
			for(Leaf l:levels.get(i)){
				for(Node n:l.getNode().getActiveNeighbors()){
					if(l.getParent()==null || n != l.getParent().getNode())
						levelI.add(new Leaf(n,null,l,l.getDir()));
				}
				
				for (int x = 0; x < 4; x++) {
					if (x == 0 && !l.getDir().equals("UP")) levelI.add(new Leaf(l.getNode(),null,l,"UP"));
					else if (x == 1 && !l.getDir().equals("DOWN")) levelI.add(new Leaf(l.getNode(),null,l,"DOWN"));
					else if (x == 2 && !l.getDir().equals("LEFT")) levelI.add(new Leaf(l.getNode(),null,l,"LEFT"));
					else if (x == 3 && !l.getDir().equals("RIGHT")) levelI.add(new Leaf(l.getNode(),null,l,"RIGHT"));
					
				}
				
			}
			levels.add(levelI);
		}
	}

	public ArrayList<Leaf> getLevel(int i) {
		
		return levels.get(i);
	}

}

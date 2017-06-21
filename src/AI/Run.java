package AI;

import java.util.ArrayList;

import game.Algorithm;
import game.Entity;
import game.Graph;
import logic.LineOfSightChecker;
import logic.Pair;

public class Run implements Algorithm {
	private Graph graph;
	private VisibilityMap visibilityMap;
	public Run(Graph graph) {
		this.graph=graph;
		if(graph.getVisibilityMap()==null){
			visibilityMap = new VisibilityMap(graph);
			graph.setVisibilityMap(visibilityMap);
		}
		else
			visibilityMap = graph.getVisibilityMap();
		
		
	}

	@Override
	public void move(Entity entity) {
		

	}

}

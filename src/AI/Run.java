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
	private Random random;
	private int counter =0;
	private  int mod = 5;
	public Run(Graph graph) {
		this.graph=graph;
		if(graph.getVisibilityMap()==null){
			visibilityMap = new VisibilityMap(graph);
			graph.setVisibilityMap(visibilityMap);
			visibilityMap.getBest(5);
		}
		else
			visibilityMap = graph.getVisibilityMap();
		
		random = new Random(graph);
		
	}

	@Override
	public void move(Entity entity) {
		if(counter%mod == 0){
			counter=0;
			//entity.pathFindingToNode()
		}else{
			random.move(entity);
			counter++;
		}
		

	}

}

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
	private int counter =5;
	private  int mod = 5;
	private int[][] bestNode;
	private int bestCounter=0;
	private boolean pathFinding=false;
	private PathFinding pf;
	public Run(Graph graph,int runningPoints) {
		this.graph=graph;
		if(graph.getVisibilityMap()==null){
			visibilityMap = new VisibilityMap(graph);
			graph.setVisibilityMap(visibilityMap);
		}
		else
			visibilityMap = graph.getVisibilityMap();
		    bestNode= visibilityMap.getBest(runningPoints);
		
		random = new Random(graph);
		
	}

	@Override
	public void move(Entity entity) {
		if(pathFinding){
			if(pf.hasNextNod()){
				entity.moveToNode(pf.removeNextNode(), graph);
			}else{
				pathFinding = false;
				move(entity);
			}
		}else{
			if(counter%mod == 0){
				pathFinding = true;
				counter=0;
				pf = new PathFinding(entity,graph,bestNode[bestCounter]);
				bestCounter++;
				if(bestCounter >= bestNode.length){
					bestCounter = bestCounter-bestNode.length;
				}
				move(entity);
			}else{
				random.move(entity);
				counter++;
			}		
		}
	}

}

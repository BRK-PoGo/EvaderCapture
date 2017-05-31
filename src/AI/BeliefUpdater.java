package AI;
import game.Algorithm;
import game.Entity;
import game.Graph;
import game.Node;

import java.util.ArrayList;
/* I am a bit confused about the structure of the code. Shouldn't the algorithm perform on the game or the entities
* rather than entities having algorithms.
 */

/**
 * Created by Alexander on 30/05/2017.
 */
public class BeliefUpdater implements Algorithm {
	private final int DEPTH=2;
	private int best = Integer.MIN_VALUE;

    //THis should only check on one entity
    private Graph currentState;
    private int[][] cleanDirtyMatrix;
    private Node bestMove;

    VisibilityChecker toCompare;
    SetEvaluator evaluator;

    public BeliefUpdater(Graph g){
    	currentState = g;    	
    }
    @Override
    public void move(Entity entity) {
        //Current now contains an Arraylist of all visibilty matrices of all current entities
    	if (entity.getDirtyClean()==null){
    		entity.setDirtyClean(new int[currentState.getNodeGrid().length][currentState.getNodeGrid()[0].length]);
    		for(int i=0;i<currentState.getNodeGrid().length;i++)
    			for(int j=0;j<currentState.getNodeGrid()[0].length;j++){
    				if(currentState.getNodeGrid()[i][j].getValue().equals("wall")){
    					entity.getDirtyClean()[i][j]=-5;
    				}
    		
    			}
    	}
    	int BestSum= Integer.MIN_VALUE;
    	//get 1st level
    	
    	ArrayList<Node> possMoves = entity.getNode().getActiveNeighbors();
       
           
       
    }
    
	
     

    private void recursive(Node n){
    	
    for (int i = 0; i < n.getActiveNeighbors().size(); i++) {   
       toCompare = new VisibilityChecker();
       toCompare.checkEntitiesCurrent(currentState,n.getActiveNeighbors().get(i));
       evaluator=new SetEvaluator(toCompare);
       evaluator.evaluateDirtyClean(entity);
       if (evaluator.getSumOfDirtyClean() > BestSum) {
	        BestSum=evaluator.getSumOfDirtyClean();
	        bestMove=list.get(i);
	   }
     }
    }
    public void setCurrentState(Graph g){currentState=g;}

    public int sumOfElements (int [][] matrix){
        int sum = 0;
        for (int i = 0; i<matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
            	if(matrix[i][j]>0)
            		sum+=matrix[i][j];

            }
        }
        return sum;
    }

}

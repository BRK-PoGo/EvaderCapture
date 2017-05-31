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
    	}
    	int BestSum= Integer.MIN_VALUE;
        ArrayList<Node> possMoves = entity.getNode().getActiveNeighbors();
        for (int i = 0; i < possMoves.size(); i++) {
            Node moveToCheck = possMoves.get(i);

            toCompare = new VisibilityChecker();
            toCompare.checkEntitiesCurrent(currentState,moveToCheck);
            evaluator=new SetEvaluator(toCompare);
            evaluator.evaluateDirtyClean(entity);
            if (evaluator.getSumOfDirtyClean() > BestSum) {
                BestSum=evaluator.getSumOfDirtyClean();
                bestMove=possMoves.get(i);
           
            }
        }
        entity.moveToNode(bestMove);
    }
    public void setCurrentState(Graph g){currentState=g;}

    public int sumOfElements (int [][] matrix){
        int sum = 0;
        for (int i = 0; i<matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                sum=+matrix[i][j];

            }
        }
        return sum;
    }

}

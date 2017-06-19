package AI;
import game.Algorithm;
import game.Entity;
import game.Graph;
import game.Node;
import game.Random;

import java.util.ArrayList;
/* I am a bit confused about the structure of the code. Shouldn't the algorithm perform on the game or the entities
* rather than entities having algorithms.
 */

/**
 * Created by Alexander on 30/05/2017.
 */
public class BeliefUpdater implements Algorithm {

    //THis should only check on one entity
	private Node previousMove = null;
    private Graph currentState;
    private Node bestMove;
    private int [][]bestDirtyClean;

    VisibilityChecker toCompare;
    SetEvaluator evaluator;
	private boolean firtsIteration=true;

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
        ArrayList<Node> possMoves = entity.getNode().getActiveNeighbors();
        for (int i = 0; i < possMoves.size(); i++) {
            Node moveToCheck = possMoves.get(i);

            toCompare = new VisibilityChecker();
            toCompare.checkEntitiesCurrent(currentState,moveToCheck);
            evaluator=new SetEvaluator(toCompare);
            evaluator.evaluateDirtyClean(entity.getDirtyClean());
            if (evaluator.getSumOfDirtyClean() >= BestSum) {
                BestSum=evaluator.getSumOfDirtyClean();
                bestDirtyClean=evaluator.getDirtyClean();
                bestMove=possMoves.get(i);
            }
        }
        entity.setDirtyClean(bestDirtyClean);
        if(previousMove != null)
        	System.out.println("x: "+previousMove.getY()+" y: "+previousMove.getX());
        
        if(previousMove==null || bestMove != previousMove){
        	previousMove = entity.getNode();
        	entity.moveToNode(bestMove);
        }
        else{
        	
        	
            Node temp = entity.getNode();
            Random r = new Random();
            r.move(entity,previousMove);
            previousMove = temp;
        }
        if(previousMove == null){
        	previousMove= entity.getNode(); //update previous
        	//firtsIteration=false;
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

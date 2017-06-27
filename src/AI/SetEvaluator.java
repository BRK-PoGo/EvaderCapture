package AI;

import game.Entity;

import java.util.ArrayList;

/**
 * Created by Alexander on 30/05/2017.
 */
public class SetEvaluator {

    /* Modifiy this whole thing tos pass these into the entities. Entities would have belief system*/

    private VisibilityChecker currentVisibility;
    private VisibilityChecker futureVisibility;
    private double [][] dirtyClean;
    private double sumOfDirtyClean;

    public SetEvaluator (VisibilityChecker future){
        futureVisibility = future;
    }

    public void evaluateDirtyClean(double [][] dirty){
        dirtyClean=dirty;
        double[][] tmp = sumOf2D(futureVisibility.getVisibilityMatrix(), futureVisibility.getVisibilityMatrix());
        double[][] toEvaluate = sumOf2D(tmp,dirtyClean);
        masterEvaluator(toEvaluate);
        cleanUp(toEvaluate);
        sumOfDirtyClean = sumOfElements(toEvaluate);
        dirtyClean=toEvaluate;
    }
    public double getSumOfDirtyClean(){return sumOfDirtyClean;}
    public double [][] getDirtyClean(){return dirtyClean;}
    
    public void masterEvaluator(double[][]toEvaluate){
    	for (int i=0; i<toEvaluate.length; i++){
            for (int j=0; j <toEvaluate[0].length;j++){
                if (true && toEvaluate[i][j] == 1){  //TODO change false, to disable dirtying, true to enable.
                	if (i+1<toEvaluate.length && toEvaluate [i+1][j]==0){
                        toEvaluate[i][j]=0;
                        recurrentEvaluator(toEvaluate, i,j);
                    }
                	else if (i-1>0 && toEvaluate [i-1][j]==0){
                        toEvaluate[i][j]=0;
                        recurrentEvaluator(toEvaluate, i,j);
                    }
                	else if (j+1<toEvaluate[0].length && toEvaluate [i][j+1]==0){
                        toEvaluate[i][j]=0;
                        recurrentEvaluator(toEvaluate,i,j);
                    }
                	else if (j-1>0 && toEvaluate [i][j-1]==0){
                        toEvaluate[i][j]=0;
                        recurrentEvaluator(toEvaluate, i,j);
                    }
                }
            }
    	}
    }

    private void recurrentEvaluator(double[][] toEvaluate, int i, int j) {
    	if (i+1<toEvaluate.length && toEvaluate[i+1][j]==1){
    		toEvaluate[i+1][j]=0;
    		recurrentEvaluator(toEvaluate,i+1,j);
    	}else if (i-1>0 && toEvaluate [i-1][j]==1){
            toEvaluate[i-1][j]=0;
            recurrentEvaluator(toEvaluate, i-1,j);
        }
    	else if (j+1<toEvaluate[0].length && toEvaluate [i][j+1]==1){
            toEvaluate[i][j+1]=0;
            recurrentEvaluator(toEvaluate,i,j+1);
        }
    	else if (j-1>0 && toEvaluate [i][j-1]==1){
            toEvaluate[i][j-1]=0;
            recurrentEvaluator(toEvaluate, i,j-1);
        }
		
	}

	/*This method is the first evaluator. It looks for 1 values i.e. fields that could be seen before and cannot be seen
    anymore and checks whether they neighbour dirty nodes and makes them dirty. It calls the second evaluator to perform
    this check on its neighbours
     */

    public void firstEvaluatorOLD (double [][] toEvaluate,int i, int j){
        for (int k=i; k<toEvaluate.length; k++){
            for (int l=j; l <toEvaluate[0].length;l++){
                if (toEvaluate[k][l] == 1){
                    //check if they  exist first
                    if (k+1<toEvaluate.length && toEvaluate [k+1][l]==0){
                        toEvaluate[j][k]=0;
                        secondEvaluator(toEvaluate, (k+1),l);
                    }
                    if (k-1>0 && toEvaluate [k-1][l]==0){
                        toEvaluate[j][k]=0;
                        secondEvaluator(toEvaluate, (k-1),l);
                    }
                    if (l+1<toEvaluate[0].length && toEvaluate [k][l+1]==0){
                        toEvaluate[j][k]=0;
                        secondEvaluator(toEvaluate, k,(l+1));
                    }
                    if (l-1>0 && toEvaluate [k][l-1]==0){
                        toEvaluate[j][k]=0;
                        secondEvaluator(toEvaluate, k,(l-1));
                    }
                }
            }
        }
    }


    /*This method checks the neighbours of the firstEvaluator and checks the neighbours of what it sets to 0 too
     */
    public void secondEvaluator (double [][] toEvaluate,int i, int j) {
        for (int k = i; k < toEvaluate.length; k++) {
            for (int l = j; l < toEvaluate[0].length; l++) {
                if (k+1<toEvaluate.length && toEvaluate[k + 1][l] == 1 ) {
                    toEvaluate[k + 1][l] = 0;
                    secondEvaluator(toEvaluate, k, l);
                }
                if (l+1<toEvaluate[0].length && toEvaluate[k][l + 1] == 1) {
                    toEvaluate[k][l + 1] = 0;
                    secondEvaluator(toEvaluate, k, l);
                }
                if (l-1>0 && toEvaluate[k][l - 1] == 1) {
                    toEvaluate[k][l - 1] = 0;
                    secondEvaluator(toEvaluate, k, l);
                }
                if (l-1>0 && toEvaluate[k - 1][l] == 1) {
                    toEvaluate[k - 1][l] = 0;
                    secondEvaluator(toEvaluate, k, l);
                }
                else {
                   // firstEvaluator(toEvaluate,k,l);
                }
            }
        }
     }

     /*This method cleans up the final Matrix of dirty and clean nodes
     Dirty nodes have value 0, clean have value 1, walls have value -5
      */

     public void cleanUp (double [][] toClean){
        for (int i = 0; i<toClean.length; i++){
            for (int j = 0; j<toClean[0].length; j++){
                if(toClean[i][j]>0){
                    toClean[i][j]=1;
                }
                if (toClean[i][j]<0){
                    toClean[i][j]=-5;
                }
            }
        }
     }

     /*The following methods are used for the probablistic evaluation*/

    /*This method sums two matrices*/

    public double [][] sumOf2D(double [][] sum1, double [][] sum2){
        double [][] sum = new double [sum1.length][sum2[0].length];
        if (sum1.length == sum2.length && sum1[1].length == sum2[0].length) {
            for (int i=0; i<sum1.length;i++){
                for (int j=0; j<sum1[0].length;j++){
                    sum[i][j]=sum1[i][j] + sum2[i][j];
                }
        }

        }
        return sum;
    }
    public double sumOfElements (double [][] matrix){
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

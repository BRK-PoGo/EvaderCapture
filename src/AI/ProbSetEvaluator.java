package AI;

/**
 * Created by Alexander on 20/06/2017.
 */
public class ProbSetEvaluator {
    public class SetEvaluator {

    /* Modifiy this whole thing to work pass these into the entities. Entities would have belief system*/

        private VisibilityChecker currentVisibility;
        private VisibilityChecker futureVisibility;
        private double [][] dirtyClean;
        private double sumOfDirtyClean;
        int XPos;
        int YPos;
        int countX;
        int countY;

        public SetEvaluator (VisibilityChecker future){
            futureVisibility = future;
        }

        public void evaluateDirtyClean(double [][] dirty, int XPos, int YPos){
            dirtyClean=dirty;
            this.XPos=XPos;
            this.YPos=YPos;
            countX=1;
            countY=1;
            double[][] tmp = sumOf2D(futureVisibility.getVisibilityMatrix(), futureVisibility.getVisibilityMatrix());
            double[][] toEvaluate = sumOf2D(tmp,dirtyClean);
            firstEvaluator(toEvaluate,0,0);
            cleanUp(toEvaluate);
            sumOfDirtyClean = sumOfElements(toEvaluate);
            dirtyClean=toEvaluate;
        }
        public double getSumOfDirtyClean(){return sumOfDirtyClean;}
        public double [][] getDirtyClean(){return dirtyClean;}


    /*This method is the first evaluator. It looks for 1 values i.e. fields that could be seen before and cannot be seen
    anymore and checks whether they neighbour dirty nodes and makes them dirty. It calls the second evaluator to perform
    this check on its neighbours
     */

        public void firstEvaluator (double [][] toEvaluate,int i, int j) {
            if (i <= XPos && j <= YPos) {
                for (int k = i; k <= XPos; k++) {
                    for (int l = j; l <= YPos; l++) {
                        if (toEvaluate[k][l] <= 1 && toEvaluate[k][l] <= 0) {
                            //check if they  exist first
                            if (k + 1 < toEvaluate.length && toEvaluate[k + 1][l] < 1 && toEvaluate[k + 1][l] > 0) {
                                toEvaluate[j][k] = toEvaluate[j][k] - (0.25 * toEvaluate[k + 1][l]);

                            }
                            if (k - 1 > 0 && toEvaluate[k - 1][l] < 1 && toEvaluate[k - 1][l] > 0) {
                                toEvaluate[j][k] = toEvaluate[j][k] - (0.25 * toEvaluate[k - 1][l]);

                            }
                            if (l + 1 < toEvaluate[0].length && toEvaluate[k][l + 1] < 1 && toEvaluate[k][l + 1] > 0) {
                                toEvaluate[j][k] = toEvaluate[j][k] - (0.25 * toEvaluate[k][l + 1]);

                            }
                            if (l - 1 > 0 && toEvaluate[k][l - 1] < 1 && toEvaluate[k][l - 1] > 0) {
                                toEvaluate[j][k] = toEvaluate[j][k] - 0.25 * toEvaluate[k][l - 1];

                            }
                        }
                    }
                    firstEvaluator(toEvaluate, 1, (toEvaluate[0].length - 2));
                }
            }

            if (i <= XPos && j > YPos) {
                for (int k = i; k <= countX; k++) {
                    for (int l = (toEvaluate[0].length - 2); l > YPos; l--) {
                        if (toEvaluate[k][l] <= 1) {
                            //check if they  exist first
                            if (k + 1 < toEvaluate.length && toEvaluate[k + 1][l] < 1 && toEvaluate[k + 1][l] > 0) {
                                toEvaluate[j][k] = toEvaluate[j][k] - (0.25 * toEvaluate[k + 1][l]);

                            }
                            if (k - 1 > 0 && toEvaluate[k - 1][l] < 1 && toEvaluate[k - 1][l] > 0) {
                                toEvaluate[j][k] = toEvaluate[j][k] - (0.25 * toEvaluate[k - 1][l]);

                            }
                            if (l + 1 < toEvaluate[0].length && toEvaluate[k][l + 1] < 1 && toEvaluate[k][l + 1] > 0) {
                                toEvaluate[j][k] = toEvaluate[j][k] - (0.25 * toEvaluate[k][l + 1]);

                            }
                            if (l - 1 > 0 && toEvaluate[k][l - 1] < 1 && toEvaluate[k][l - 1] > 0) {
                                toEvaluate[j][k] = toEvaluate[j][k] - 0.25 * toEvaluate[k][l - 1];

                            }
                        }
                    }

                }
                firstEvaluator(toEvaluate, (toEvaluate.length - 1), 1);
            }

            if (countX > XPos && countY <= YPos) {
                for (int k = i; k > XPos; k--) {
                    for (int l = j; l <= YPos; l++) {
                        if (toEvaluate[k][l] <= 1) {
                            //check if they  exist first
                            if (k + 1 < toEvaluate.length && toEvaluate[k + 1][l] < 1 && toEvaluate[k + 1][l] > 0) {
                                toEvaluate[j][k] = toEvaluate[j][k] - (0.25 * toEvaluate[k + 1][l]);

                            }
                            if (k - 1 > 0 && toEvaluate[k - 1][l] < 1 && toEvaluate[k - 1][l] > 0) {
                                toEvaluate[j][k] = toEvaluate[j][k] - (0.25 * toEvaluate[k - 1][l]);

                            }
                            if (l + 1 < toEvaluate[0].length && toEvaluate[k][l + 1] < 1 && toEvaluate[k][l + 1] > 0) {
                                toEvaluate[j][k] = toEvaluate[j][k] - (0.25 * toEvaluate[k][l + 1]);

                            }
                            if (l - 1 > 0 && toEvaluate[k][l - 1] < 1 && toEvaluate[k][l - 1] > 0) {
                                toEvaluate[j][k] = toEvaluate[j][k] - 0.25 * toEvaluate[k][l - 1];
                            }
                        }
                    }
                    firstEvaluator(toEvaluate, (toEvaluate.length - 1), (toEvaluate[0].length - 1));
                }
            }

            if (i > XPos && j > YPos) {
                for (int k = i; k > XPos; k--) {
                    for (int l = j; l > YPos; l--) {
                        if (toEvaluate[k][l] <= 1) {
                            //check if they  exist first
                            if (k + 1 < toEvaluate.length && toEvaluate[k + 1][l] < 1 && toEvaluate[k + 1][l] > 0) {
                                toEvaluate[j][k] = toEvaluate[j][k] - (0.25 * toEvaluate[k + 1][l]);

                            }
                            if (k - 1 > 0 && toEvaluate[k - 1][l] < 1 && toEvaluate[k - 1][l] > 0) {
                                toEvaluate[j][k] = toEvaluate[j][k] - (0.25 * toEvaluate[k - 1][l]);

                            }
                            if (l + 1 < toEvaluate[0].length && toEvaluate[k][l + 1] < 1 && toEvaluate[k][l + 1] > 0) {
                                toEvaluate[j][k] = toEvaluate[j][k] - (0.25 * toEvaluate[k][l + 1]);

                            }
                            if (l - 1 > 0 && toEvaluate[k][l - 1] < 1 && toEvaluate[k][l - 1] > 0) {
                                toEvaluate[j][k] = toEvaluate[j][k] - 0.25 * toEvaluate[k][l - 1];
                            }
                        }
                    }
                }
                cleanUp(toEvaluate);
            }
        }





        /*This method checks the neighbours of the firstEvaluator and checks the neighbours of what it sets to 0 too
         */


     /*This method cleans up the final Matrix of dirty and clean nodes
     Dirty nodes have value 0, clean have value 1, walls have value -5
      */

        public void cleanUp(double [][] toClean){
            for (int i  = 0; i<toClean.length; i++){
                for (int j = 0; j<toClean[0].length; j++){
                    if(toClean[i][j]>=1){
                        toClean[i][j]=1;
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
}

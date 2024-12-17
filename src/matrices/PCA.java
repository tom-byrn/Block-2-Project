package matrices;

import java.util.Arrays;

public class PCA implements PrintMatrixSuper{
    public static void main(String[] args) {

        //Create Dataset
        System.out.println("Enter Dataset");
        Matrix createFirstMatrix = new CreateFirstMatrix();
        double [][] dataset = createFirstMatrix.createMatrixAndIncludeSize();

        //Find means
        int noOfRows = dataset.length;
        int noOfColoms = dataset[0].length;

        //Array to store means
        double[][] mean = new double[noOfColoms][1];

        //Array to store centred Matrix
        double[][] centredMatrix = new double[noOfRows][noOfColoms];

        //Find Mean
        for(int colomCounter = 0; colomCounter < noOfColoms; colomCounter++){
            double colomMean = 0;
            for (double[] doubles : dataset) {
                colomMean += doubles[colomCounter];
            }
            mean[colomCounter][0] = (colomMean/noOfRows);
        }
        System.out.println("Means: ");
        PrintMatrixSuper.printMatrix(mean);
        System.out.println();


        //Find Centred Matrix
        for(int colomCounter = 0; colomCounter < noOfColoms; colomCounter++){

            //Find Centred Matrix
            for (int rowCounter = 0; rowCounter < noOfRows; rowCounter++){
                centredMatrix[rowCounter][colomCounter] = dataset[rowCounter][colomCounter] - mean[colomCounter][0];
            }
        }

        System.out.println("Centred Matrix: ");
        PrintMatrixSuper.printMatrix(centredMatrix);
        System.out.println();

        //Find Coverience Matrix
        double [][]CoverienceMatrix ;
    }
}

package matrices;

import java.util.Scanner;

import static matrices.MatricesChecker.getNumberRowCounterInMatrix;

public class MatrixCreator {

    private double[][] matrixOne;

    // used to create a matrix
    protected MatrixCreator(int noOfRowsInMatrixA, int noOfColumnsMatrixA) {


        //Create a 2D array or Matrix
        matrixOne = new double[noOfRowsInMatrixA][noOfColumnsMatrixA];

        // using scanner to find the numbers
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first row:\t");

        // iterate through the rows
        for(int rowCounter = 0; rowCounter< noOfRowsInMatrixA; rowCounter++){
            int positionCounter;

            // Input each row
            // uses regex and trim to remove tabs and multiple spaces
            String rowListWithSpace = input.nextLine().replaceAll("\\s+", " ").trim();

            // iterate through the columns in each row and adding them to the matrix
            for(positionCounter = 0; positionCounter< noOfColumnsMatrixA; positionCounter++){

                double numberRowCounterInMatrix = getNumberRowCounterInMatrix(rowListWithSpace, positionCounter);
                matrixOne[rowCounter][positionCounter] = numberRowCounterInMatrix;
            }

            // for printing out next row before you enter each row
            if(noOfRowsInMatrixA != (rowCounter+1)){
                System.out.print("Enter next row:\t\t");
            }
        }
        setMatrixOne(matrixOne);
    }

    protected double[][] getMatrixOne() {
        return matrixOne;
    }

    protected void setMatrixOne(double[][] matrixOne) {
        this.matrixOne = matrixOne;
    }
}

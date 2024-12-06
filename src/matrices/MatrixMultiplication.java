package matrices;

import java.util.Scanner;

import static matrices.MatricesManager.*;
import static matrices.MatricesChecker.intSizeInt;

public class MatrixMultiplication {
    private static int noOfColumnsInMatrixInB;

    protected static void multiplicationStart(){
        //Calls matrixFirstCreator and sets matrixA to answer
        //Initilize matrices
        //creates a 2D matrix
        double[][] matrixA = MatricesManager.matrixFirstCreator();

        //Calls multiplyingMatrixB and sets matrixB to answer
        double[][] matrixB = multiplyingMatrixB();

        double[][]matrixFinal = multiplication(matrixA,matrixB);

        MatricesManager.printMatrix(matrixFinal);
    }

    //Method for multiplying matrixes
    protected static double[][] multiplication(double[][] matrixA, double[][] matrixB){

        //Get the number of rows for matrix
        noOfRowsInMatrixA =  matrixA.length;

        // Get the number of columns
        noOfColumnsMatrixA = matrixA[0].length;
        noOfColumnsInMatrixInB = matrixB[0].length;

        // Create final Matrix
        double[][]matrixFinal = new double[noOfRowsInMatrixA][noOfColumnsInMatrixInB];

        // Perform matrix multiplication
        for (int rowMatrixA = 0; rowMatrixA < noOfRowsInMatrixA; rowMatrixA++) {  // Loop over each row of matrix A

            for (int colMatrixB = 0; colMatrixB < noOfColumnsInMatrixInB; colMatrixB++) {  // Loop over each column of matrix B

                // Multiply corresponding elements from the row of matrix A and the column of matrix B
                // The loop variable "columnMatrixA" refers to the shared dimension (columns of A, rows of B)
                for (int columnMatrixA = 0; columnMatrixA < noOfColumnsMatrixA; columnMatrixA++) { // Iterate over the columns of A (or rows of B)

                    // Multiply the element from matrix A (at rowMatrixA, columnMatrixA)
                    // with the element from matrix B (at columnMatrixA, colMatrixB)
                    // Then add them to the existing value in the matrixFinal
                    matrixFinal[rowMatrixA][colMatrixB] += matrixA[rowMatrixA][columnMatrixA] * matrixB[columnMatrixA][colMatrixB];
                }
            }
        }
        return matrixFinal;
    }



    // used to create a matrix B
    private static double[][] multiplyingMatrixB() {

        //input no of rows
        System.out.print("Enter Number of Rows: ");
        // checks if the number of rows is valid
        int noOfRowsInMatrixInB;
        while (!((noOfRowsInMatrixInB = intSizeInt()) == noOfColumnsMatrixA)){
            System.out.println("Sorry your matrix must have the same number of rows as matrix A has columns: " + noOfColumnsMatrixA);
        }

        //input no of rows
        System.out.print("Enter Number of Columns: ");
        // checks if the number of rows is valid
        while ((noOfColumnsInMatrixInB = intSizeInt()) == 0){
            System.out.print("Error please enter a number between 1-2147483647: ");
        }


        //Create a 2D array or Matrix
        double[][]matrixOneSize = new double[noOfRowsInMatrixInB][noOfColumnsInMatrixInB];

        // using scanner to find the numbers
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first row:\t");

        // itterate through the rows
        for(int rowCounter = 0; rowCounter < noOfRowsInMatrixInB; rowCounter++){
            int positionCounter;

            // Input each row
            // uses regex and trim to remove tabs and multiple spaces
            String rowListWithSpace = input.nextLine().replaceAll("\\s+", " ").trim();

            // itterate through the coloums in each row and adding them to the matrix
            for(positionCounter = 0; positionCounter< noOfColumnsInMatrixInB; positionCounter++){

                double numberRowCounterInMatrix = MatricesChecker.getNumberRowCounterInMatrix(rowListWithSpace, positionCounter);
                matrixOneSize[rowCounter][positionCounter] = numberRowCounterInMatrix;
            }

            // for printing out next row before you enter each row
            if(noOfRowsInMatrixInB > (rowCounter+1)){
                System.out.print("Enter next row:\t\t");
            }
        }

        //Used for ensuring correct matrix
        do {
            checker = MatricesChecker.YorN(matrixOneSize);

            //Checks if matrix is correct
            if(checker == 'N' || checker == 'n') {
                matrixOneSize = multiplyingMatrixB();
            }
        }while (checker == 'N' || checker == 'n');

        return matrixOneSize;
    }
}

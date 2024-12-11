package matrices;

import static matrices.MatricesManager.*;

public class MatrixMultiplication {
    protected static int noOfColumnsInMatrixInB;

    protected static void multiplicationStart(){
        //Calls matrixFirstCreator and sets matrixA to answer
        //Initilize matrices
        //creates a 2D matrix
        MatrixCreator createFirstMatrix = new CreateFirstMatrix();
        double[][] matrixA = createFirstMatrix.createMatrixAndIncludeSize();

        //Calls multiplyingMatrixB and sets matrixB to answer
        MatrixCreator createMultiplyingMatrixB = new CreateMultiplyingMatrixB();
        double[][] matrixB = createMultiplyingMatrixB.createMatrixAndIncludeSize();

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
}

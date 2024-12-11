package matrices;

import static matrices.NumberChecker.intSizeInt;
import static matrices.MatricesManager.*;
import static matrices.MatrixMultiplication.noOfColumnsInMatrixInB;

public class CreateMultiplyingMatrixB extends MatrixCreator{


    @Override
    public double[][] createMatrixAndIncludeSize() {

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

        // Call the super method to create the matrix with the new inputted size
        double[][] matrixFirst = super.createMatrixNoSize(noOfRowsInMatrixInB,noOfColumnsInMatrixInB);


        //Used for ensuring correct matrix
        matrixChecker(matrixFirst);

        return getMatrixOne();
    }

    @Override
    public void matrixChecker(double[][] matrixFirst) {
        do {
            checker = super.YorN(matrixFirst);

            if(checker == 'N' || checker == 'n') {
                createMatrixAndIncludeSize();
            }
        }while (checker == 'N' || checker == 'n');
    }
}

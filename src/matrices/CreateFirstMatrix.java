package matrices;

import static matrices.NumberChecker.intSizeInt;
import static matrices.MatricesManager.*;

public class CreateFirstMatrix extends MatrixCreator{


    @Override
    public double[][] createMatrixAndIncludeSize() {

        //input no of rows
        System.out.print("Enter Number of Rows: ");

        // checks if the number of rows is valid
        while ((noOfRowsInMatrixA = intSizeInt()) == 0){
            System.out.print("Error please enter a number between 1-2147483647: ");
        }

        //input no of columns
        System.out.print("Enter Number of Columns: ");
        // checks if the number of columns is valid
        while ((noOfColumnsMatrixA = intSizeInt()) == 0){
            System.out.print("Error please enter a number between 1-2147483647: ");
        }

        // Call the super method to create the matrix with the new inputted size
        double[][] matrixFirst = super.createMatrixNoSize(noOfRowsInMatrixA,noOfColumnsMatrixA);


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

package matrices;


import static matrices.MatricesManager.*;

public class CreateAddingMatrixB extends MatrixCreator{

    public double[][] createMatrixAndIncludeSize() {

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

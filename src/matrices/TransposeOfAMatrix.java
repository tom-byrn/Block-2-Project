package matrices;

import static matrices.MatricesManager.*;

public class TransposeOfAMatrix {

    protected static void transposeStart(){
        double[][]matrixA = matrixFirstCreator();

        double[][] matrixFinal = transpose(matrixA);

        MatricesManager.printMatrix(matrixFinal);
    }
    protected static double[][] transpose(double[][]matrixA){

        //Get the number of rows for matrix
        noOfRowsInMatrixA = matrixA.length;

        // Get the number of columns
        noOfColumnsMatrixA = matrixA[0].length;

        //Create a matrix of with row = coloms and colums equal to rows from MatrixA
        double[][]matrixFinal = new double[noOfColumnsMatrixA][noOfRowsInMatrixA];

        //Cycle through each row
        for(int matrixRowAdder = 0; matrixRowAdder< noOfRowsInMatrixA; matrixRowAdder++){

            //Cycle through each colum
            for(int matrixColoumAdder = 0; matrixColoumAdder < noOfColumnsMatrixA; matrixColoumAdder++){

                matrixFinal[matrixColoumAdder][matrixRowAdder] = matrixA[matrixRowAdder][matrixColoumAdder];
            }
        }
        return matrixFinal;
    }
}

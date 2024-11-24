package matrices;

import static matrices.Matrices.*;

public class TransposeOfAMatrix {
    protected static double[][] Transpose(){
        double[][]matrixA = matrixFirstCreator();

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

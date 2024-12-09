package matrices;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static matrices.MatricesManager.*;

public class MatrixAdder {

    //Initilize matrices
    //creates a 2D matrix
    private static double[][]matrixA;
    private static double[][]matrixB;
    private static double[][] matrixFinal;


    //Used to add 2 matrixs
    protected static void addition(){

        //Calls AdditionOrSubtraction Method
        additionOrSubtraction();

        //Adder for each row
        for(int matrixRowAdder = 0; matrixRowAdder< noOfRowsInMatrixA; matrixRowAdder++){

            //Adder for each colum
            for(int matrixColoumAdder = 0; matrixColoumAdder < noOfColumnsMatrixA; matrixColoumAdder++){
                //Adds A to B
                BigDecimal finalMatrixNumberBigDecimal = BigDecimal.valueOf((matrixA[matrixRowAdder][matrixColoumAdder] + matrixB[matrixRowAdder][matrixColoumAdder]));
                BigDecimal finalMatrixNumberRounded = finalMatrixNumberBigDecimal.setScale(45, RoundingMode.HALF_UP); // Rounds to 8 decimal places

                matrixFinal[matrixRowAdder][matrixColoumAdder] = finalMatrixNumberRounded.doubleValue();
            }
        }
        MatricesManager.printMatrix(matrixFinal);
    }

    //Used to subtract 2 matrixs
    protected static void subtraction(){

        //Calls AdditionOrSubtraction Method
        additionOrSubtraction();

        //Adder for each row
        for(int matrixRowAdder = 0; matrixRowAdder< noOfRowsInMatrixA; matrixRowAdder++){

            //Adder for each colum
            for(int matrixColoumAdder = 0; matrixColoumAdder < noOfColumnsMatrixA; matrixColoumAdder++){
                //Adds A to B
                BigDecimal finalMatrixNumberBigDecimal = BigDecimal.valueOf((matrixA[matrixRowAdder][matrixColoumAdder] - matrixB[matrixRowAdder][matrixColoumAdder]));
                BigDecimal finalMatrixNumberRounded = finalMatrixNumberBigDecimal.setScale(45, RoundingMode.HALF_UP); // Rounds to 8 decimal places

                matrixFinal[matrixRowAdder][matrixColoumAdder] = finalMatrixNumberRounded.doubleValue();
            }
        }
        MatricesManager.printMatrix(matrixFinal);
    }


    //Used to create the matrices for addition or subtraction
    private static void additionOrSubtraction(){

        //Calls matrixFirstCreator and sets matrixA to answer
        MatrixCreator matrixCreatorMatrixA = new CreateFirstMatrix();
        matrixA = matrixCreatorMatrixA.createMatrixAndIncludeSize();

        // Calls matrixFirstCreator and sets matrixB to answer
        MatrixCreator matrixCreatormatrixB = new CreateAddingMatrixB();
        matrixB = matrixCreatormatrixB.createMatrixAndIncludeSize();

        // Create final Matrix
        matrixFinal = new double[noOfRowsInMatrixA][noOfColumnsMatrixA];
    }
}
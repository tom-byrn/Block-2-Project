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
        matrixA = MatricesManager.matrixFirstCreator();

        //Calls matrixCreatorB and sets matrixB to answer
        matrixB = addingMatrixB();

        // Create final Matrix
        matrixFinal = new double[noOfRowsInMatrixA][noOfColumnsMatrixA];
    }


    // used to create a matrix B
    private static double[][] addingMatrixB() {

        //Calls the matrixCreator class
        MatrixCreator matrixCreator = new MatrixCreator(noOfRowsInMatrixA,noOfColumnsMatrixA);


        //Used for ensuring correct matrix
        do {
            checker = MatricesChecker.YorN(matrixCreator.getMatrixOne());

            if(checker == 'N' || checker == 'n') {
                matrixFirstCreator();
            }
        }while (checker == 'N' || checker == 'n');

        return matrixCreator.getMatrixOne();
    }
}
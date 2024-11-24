package matrices;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

import static matrices.Matrices.*;

public class MatrixAdder {

    //Initilize matrices
    //creates a 2D matrix
    private static double[][]matrixA;
    private static double[][]matrixB;
    private static double[][] matrixFinal;


    //Used to add 2 matrixs
    protected static double[][] addition(){

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
        return matrixFinal;
    }

    //Used to subtract 2 matrixs
    protected static double[][] subtraction(){

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
        return matrixFinal;
    }


    //Used to create the matrices for addition or subtraction
    private static void additionOrSubtraction(){

        //Calls matrixFirstCreator and sets matrixA to answer
        matrixA = Matrices.matrixFirstCreator();

        //Calls matrixCreatorB and sets matrixB to answer
        matrixB = addingMatrixB();

        // Create final Matrix
        matrixFinal = new double[noOfRowsInMatrixA][noOfColumnsMatrixA];
    }


    // used to create a matrix B
    private static double[][] addingMatrixB() {
        //Create a 2D array or Matrix
        double[][]matrixOneSize = new double[noOfRowsInMatrixA][noOfColumnsMatrixA];

        // using scanner to find the numbers
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first row:\t");

        // itterate through the rows
        for(int rowCounter = 0; rowCounter < noOfRowsInMatrixA; rowCounter++){
            int positionCounter;

            // Input each row
            // uses regex and trim to remove tabs and multiple spaces
            String rowListWithSpace = input.nextLine().replaceAll("\\s+", " ").trim();

            // itterate through the coloums in each row and adding them to the matrix
            for(positionCounter = 0; positionCounter< noOfColumnsMatrixA; positionCounter++){

                double numberRowCounterInMatrix = MatricesChecker.getNumberRowCounterInMatrix(rowListWithSpace, positionCounter);
                matrixOneSize[rowCounter][positionCounter] = numberRowCounterInMatrix;
            }

            // for printing out next row before you enter each row
            if(noOfRowsInMatrixA > (rowCounter+1)){
                System.out.print("Enter next row:\t\t");
            }
        }

        //Used for ensuring correct matrix
        do {
            checker = MatricesChecker.YorN(matrixOneSize);

            //Checks if matrix is correct
            if(checker == 'N' || checker == 'n') {
                matrixOneSize = addingMatrixB();
            }
        }while (checker == 'N' || checker == 'n');

        return matrixOneSize;
    }
}

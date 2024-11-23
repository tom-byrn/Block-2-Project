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


    //Used to add 2 matrixs
    protected static double[][] Addition(){

        //Calls AdditionOrSubtraction Method
        AdditionOrSubtraction();

        //Adder for each row
        for(int matrixRowAdder = 0; matrixRowAdder<noOfRowsMatrixOne; matrixRowAdder++){

            //Adder for each colum
            for(int matrixColoumAdder = 0; matrixColoumAdder < noOfColumnsMatrixOne; matrixColoumAdder++){
                //Adds A to B
                BigDecimal finalMatrixNumberBigDecimal = BigDecimal.valueOf((matrixA[matrixRowAdder][matrixColoumAdder] + matrixB[matrixRowAdder][matrixColoumAdder]));
                BigDecimal finalMatrixNumberRounded = finalMatrixNumberBigDecimal.setScale(45, RoundingMode.HALF_UP); // Rounds to 8 decimal places

                matrixFinal[matrixRowAdder][matrixColoumAdder] = finalMatrixNumberRounded.doubleValue();
            }
        }
        return matrixFinal;
    }

    //Used to subtract 2 matrixs
    protected static double[][] Subtraction(){

        //Calls AdditionOrSubtraction Method
        AdditionOrSubtraction();

        //Adder for each row
        for(int matrixRowAdder = 0; matrixRowAdder<noOfRowsMatrixOne; matrixRowAdder++){

            //Adder for each colum
            for(int matrixColoumAdder = 0; matrixColoumAdder < noOfColumnsMatrixOne; matrixColoumAdder++){
                //Adds A to B
                BigDecimal finalMatrixNumberBigDecimal = BigDecimal.valueOf((matrixA[matrixRowAdder][matrixColoumAdder] - matrixB[matrixRowAdder][matrixColoumAdder]));
                BigDecimal finalMatrixNumberRounded = finalMatrixNumberBigDecimal.setScale(45, RoundingMode.HALF_UP); // Rounds to 8 decimal places

                matrixFinal[matrixRowAdder][matrixColoumAdder] = finalMatrixNumberRounded.doubleValue();
            }
        }
        return matrixFinal;
    }


    //Used to create the matrices for addition or subtraction
    private static void AdditionOrSubtraction(){

        matrixA = Matrices.matrixFirstCreator();

        //Used for ensuring correct matrix
        do {
            checker = MatricesChecker.YorN(matrixA);

            if(checker == 'N' || checker == 'n') {
                matrixA = Matrices.matrixFirstCreator();
            }
        }while (checker == 'N' || checker == 'n');


        matrixB = matrixCreatorB();
        //Used for ensuring correct matrix
        do {
            checker = MatricesChecker.YorN(matrixB);

            //Checks if matrix is correct
            if(checker == 'N' || checker == 'n') {
                matrixB = matrixCreatorB();
            }
        }while (checker == 'N' || checker == 'n');


        // Create final Matrix
        matrixFinal = new double[noOfRowsMatrixOne][noOfColumnsMatrixOne];
    }


    // used to create a matrix B
    private static double[][] matrixCreatorB() {
        //Create a 2D array or Matrix
        double[][]matrixOneSize = new double[noOfRowsMatrixOne][noOfColumnsMatrixOne];

        // using scanner to find the numbers
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first row:\t");

        // itterate through the rows
        for(int rowCounter = 0; rowCounter < noOfRowsMatrixOne; rowCounter++){
            int positionCounter;

            // Input each row
            // uses regex and trim to remove tabs and multiple spaces
            String rowListWithSpace = input.nextLine().replaceAll("\\s+", " ").trim();

            // itterate through the coloums in each row and adding them to the matrix
            for(positionCounter = 0; positionCounter< noOfColumnsMatrixOne; positionCounter++){

                double numberRowCounterInMatrix = MatricesChecker.getNumberRowCounterInMatrix(rowListWithSpace, positionCounter);
                matrixOneSize[rowCounter][positionCounter] = numberRowCounterInMatrix;
            }

            // for printing out next row before you enter each row
            if(noOfRowsMatrixOne > (rowCounter+1)){
                System.out.print("Enter next row:\t\t");
            }
        }

        return matrixOneSize;
    }
}

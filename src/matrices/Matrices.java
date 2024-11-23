package matrices;

import java.util.Scanner;

// Class imports
import static matrices.MatricesChecker.*;

public class Matrices {


    //Declaring Variables
    protected static byte noOfRowsMatrixOne;
    protected static byte noOfColumnsMatrixOne;
    protected static char checker;
    protected static double[][]matrixFinal;

    public static double[][] promptForMatrices() {
        // select function using a text block
        System.out.println(
                """
                        Enter (1) for Addition
                        Enter (2) for Subtraction
                        Enter (3) for Multiplication
                        Enter (4) for Transpose
                        Enter (5) for Inverse
                        Enter (6) for LU Factorisation
                        Enter (7) for def(A)
                        Enter (8) for Eigenvalues
                        Enter (9) for Power""");



        // checking is a valid function selected
        byte function;
        while ((function = byteSizeInt()) < 1 || function > 9) {
            System.out.print("Error please enter a number between 1-9: ");
        }


        // Checks which function is selected
        if(function == 1){
            //Calls Addition method
            return MatrixAdder.Addition();
        }

        if(function == 2){
            return MatrixAdder.Subtraction();
        }

        if(function == 4){
            double[][]matrixA = matrixFirstCreator();

            //Used for ensuring correct matrix
            do {
                checker = YorN(matrixA);

                if(checker == 'N' || checker == 'n') {
                    matrixA = matrixFirstCreator();
                }
            }while (checker == 'N' || checker == 'n');


            //Create a matrix of with row = coloms and colums equal to rows from MatrixA
            matrixFinal = new double[noOfColumnsMatrixOne][noOfRowsMatrixOne];

            //Cycle through each row
            for(int matrixRowAdder = 0; matrixRowAdder<noOfRowsMatrixOne; matrixRowAdder++){

                //Cycle through each colum
                for(int matrixColoumAdder = 0; matrixColoumAdder < noOfColumnsMatrixOne; matrixColoumAdder++){

                    matrixFinal[matrixColoumAdder][matrixRowAdder] = matrixA[matrixRowAdder][matrixColoumAdder];
                }
            }
            return matrixFinal;
        }

        return null;
    }

    // used to create a matrix
    public static double[][] matrixFirstCreator() {
        //input no of rows
        System.out.print("Enter Number of Rows: ");
        // checks if the number of rows is valid
        while ((noOfRowsMatrixOne = byteSizeInt()) == 0){
            System.out.print("Error please enter a number between 1-127: ");
        }

        //input no of columns
        System.out.print("Enter Number of Columns: ");
        // checks if the number of coloms is valid
        while ((noOfColumnsMatrixOne = byteSizeInt()) == 0){
            System.out.print("Error please enter a number between 1-127: ");
        }

        //Create a 2D array or Matrix
        double[][]matrixOneSize = new double[noOfRowsMatrixOne][noOfColumnsMatrixOne];

        // using scanner to find the numbers
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first row:\t");

        // itterate through the rows
        for(int rowCounter = 0; rowCounter<noOfRowsMatrixOne; rowCounter++){
            int positionCounter;

            // Input each row
            // uses regex and trim to remove tabs and multiple spaces
            String rowListWithSpace = input.nextLine().replaceAll("\\s+", " ").trim();

            // itterate through the coloums in each row and adding them to the matrix
            for(positionCounter = 0; positionCounter< noOfColumnsMatrixOne; positionCounter++){

                double numberRowCounterInMatrix = getNumberRowCounterInMatrix(rowListWithSpace, positionCounter);
                matrixOneSize[rowCounter][positionCounter] = numberRowCounterInMatrix;
            }

            // for printing out next row before you enter each row
            if(noOfRowsMatrixOne != (rowCounter+1)){
                System.out.print("Enter next row:\t\t");
            }
        }

        return matrixOneSize;
    }
}
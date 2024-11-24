package matrices;

import java.util.Arrays;
import java.util.Scanner;

// Class imports
import static matrices.MatricesChecker.*;

public class Matrices {


    //Declaring Variables
    protected static byte noOfRowsInMatrixA;
    protected static byte noOfColumnsMatrixA;
    protected static char checker;

    public static void start() {

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
            MatrixAdder.addition();
        }

        if(function == 2){
            MatrixAdder.subtraction();
        }

        if(function == 3){
            MatrixMultiplication.multiplication();
        }

        if(function == 4){
            TransposeOfAMatrix.transpose();
        }
        if(function == 5){
            MatrixInverse.inverse();
        }
        if(function == 6){
            LUFactorisation.factoriser();
        }
    }

    // used to create a matrix
    protected static double[][] matrixFirstCreator() {
        //input no of rows
        System.out.print("Enter Number of Rows: ");
        // checks if the number of rows is valid
        while ((noOfRowsInMatrixA = byteSizeInt()) == 0){
            System.out.print("Error please enter a number between 1-127: ");
        }

        //input no of columns
        System.out.print("Enter Number of Columns: ");
        // checks if the number of coloms is valid
        while ((noOfColumnsMatrixA = byteSizeInt()) == 0){
            System.out.print("Error please enter a number between 1-127: ");
        }

        //Create a 2D array or Matrix
        double[][]matrixOneSize = new double[noOfRowsInMatrixA][noOfColumnsMatrixA];

        // using scanner to find the numbers
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first row:\t");

        // itterate through the rows
        for(int rowCounter = 0; rowCounter< noOfRowsInMatrixA; rowCounter++){
            int positionCounter;

            // Input each row
            // uses regex and trim to remove tabs and multiple spaces
            String rowListWithSpace = input.nextLine().replaceAll("\\s+", " ").trim();

            // itterate through the coloums in each row and adding them to the matrix
            for(positionCounter = 0; positionCounter< noOfColumnsMatrixA; positionCounter++){

                double numberRowCounterInMatrix = getNumberRowCounterInMatrix(rowListWithSpace, positionCounter);
                matrixOneSize[rowCounter][positionCounter] = numberRowCounterInMatrix;
            }

            // for printing out next row before you enter each row
            if(noOfRowsInMatrixA != (rowCounter+1)){
                System.out.print("Enter next row:\t\t");
            }
        }


        //Used for ensuring correct matrix
        do {
            checker = MatricesChecker.YorN(matrixOneSize);

            if(checker == 'N' || checker == 'n') {
                matrixOneSize = Matrices.matrixFirstCreator();
            }
        }while (checker == 'N' || checker == 'n');

        return matrixOneSize;
    }

    // used to create a square matrix
    protected static double[][] squareMatrixCreator() {
        //input no of rows
        System.out.print("Enter Number of Rows and columns (n * n): ");
        // checks if the number of rows is valid
        while ((noOfRowsInMatrixA = noOfColumnsMatrixA = byteSizeInt()) == 0){
            System.out.print("Error please enter a number between 1-127: ");
        }

        //Create a 2D array or Matrix
        double[][]matrixOneSize = new double[noOfRowsInMatrixA][noOfColumnsMatrixA];

        // using scanner to find the numbers
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first row:\t");

        // itterate through the rows
        for(int rowCounter = 0; rowCounter< noOfRowsInMatrixA; rowCounter++){
            int positionCounter;

            // Input each row
            // uses regex and trim to remove tabs and multiple spaces
            String rowListWithSpace = input.nextLine().replaceAll("\\s+", " ").trim();

            // itterate through the coloums in each row and adding them to the matrix
            for(positionCounter = 0; positionCounter< noOfColumnsMatrixA; positionCounter++){

                double numberRowCounterInMatrix = getNumberRowCounterInMatrix(rowListWithSpace, positionCounter);
                matrixOneSize[rowCounter][positionCounter] = numberRowCounterInMatrix;
            }

            // for printing out next row before you enter each row
            if(noOfRowsInMatrixA != (rowCounter+1)){
                System.out.print("Enter next row:\t\t");
            }
        }


        //Used for ensuring correct matrix
        do {
            checker = MatricesChecker.YorN(matrixOneSize);

            if(checker == 'N' || checker == 'n') {
                matrixOneSize = squareMatrixCreator() ;
            }
        }while (checker == 'N' || checker == 'n');

        return matrixOneSize;
    }

    protected static void printMatrix(double[][] matrixToPrint) {
        //Print the out the matrix
        for (double[] row : matrixToPrint) {
            System.out.println(Arrays.toString(row));
        }
    }
}
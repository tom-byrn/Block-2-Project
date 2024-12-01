package matrices;

import functions.FunctionGraph;
import functions.Functions;
import menu.MenuManager;

import java.util.Arrays;
import java.util.Scanner;

// Class imports
import static matrices.MatricesChecker.*;

public class MatricesManager {


    //Declaring Variables
    protected static int noOfRowsInMatrixA;
    protected static int noOfColumnsMatrixA;
    protected static char checker;

    public static void start() {

        Scanner scanner = new Scanner(System.in);

        // select function using a text block
        System.out.println(
                """
                        Enter (1) for Addition
                        Enter (2) for Subtraction
                        Enter (3) for Multiplication
                        Enter (4) for Transpose
                        Enter (5) for Power
                        Enter (6) for LU Factorisation
                        Enter (7) for det(A)
                        Enter (8) for Dominant Eigenvalues
                        Enter (9) to  Solve Simultaneous Equations
                        Enter (0) to return to the menu
                        """);
        System.out.print("Enter a choice: ");

        // Scanner object scans for user input
        int selectorNum = scanner.nextInt();
        System.out.println();

        // Switch case for choosing an option
        switch (selectorNum) {
            case 1 -> {
                Functions.singleVariableFunction();
                break;
            }
            case 2 -> {
                Functions.multiVariateFunction();
                break;
            }
            case 3 -> {
                Functions.composeFunctions();
                break;
            }
            case 4 -> {
                Functions.bisectionMethod();
                break;
            }
            case 5 -> {
                Functions.secantMethod();
                break;
            }
            case 6 -> {
                FunctionGraph fg = new FunctionGraph();
                fg.graphFunction();
                break;
            }
            case 0 -> {
                MenuManager.clearScreen();
                MenuManager.callMenu();
                break;
            }
        }
    }

    // used to create a matrix
    protected static double[][] matrixFirstCreator() {
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

        //Create a 2D array or Matrix
        double[][]matrixOneSize = new double[noOfRowsInMatrixA][noOfColumnsMatrixA];

        // using scanner to find the numbers
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first row:\t");

        // iterate through the rows
        for(int rowCounter = 0; rowCounter< noOfRowsInMatrixA; rowCounter++){
            int positionCounter;

            // Input each row
            // uses regex and trim to remove tabs and multiple spaces
            String rowListWithSpace = input.nextLine().replaceAll("\\s+", " ").trim();

            // iterate through the columns in each row and adding them to the matrix
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
                matrixOneSize = MatricesManager.matrixFirstCreator();
            }
        }while (checker == 'N' || checker == 'n');

        return matrixOneSize;
    }

    // used to create a square matrix
    protected static double[][] squareMatrixCreator() {
        //input no of rows
        System.out.print("Enter Number of Rows and columns (n * n): ");
        // checks if the number of rows is valid
        while ((noOfRowsInMatrixA = noOfColumnsMatrixA = intSizeInt()) == 0){
            System.out.print("Error please enter a number between 1-2147483647: ");
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
package matrices;

import java.math.RoundingMode;
import java.util.Scanner;
import java.math.BigDecimal;

public class Matrices {


    //Declaring Variables
    protected static byte noOfRowsMatrixOne;
    protected static byte noOfColumnsMatrixOne;
    private static char checker;
    private static double[][]matrixFinal;

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
        while ((function = MatricesChecker.byteSizeInt()) < 1 || function > 9) {
            System.out.print("Error please enter a number between 1-9: ");
        }
        // Checks which function is selected
        if(function == 1){
            double[][]matrixA = matrixCreator();

            //Used for ensuring correct matrix
            do {
                checker = MatricesChecker.YorN(matrixA);

                if(checker == 'N' || checker == 'n') {
                    matrixA = matrixCreator();
                }
            }while (checker == 'N' || checker == 'n');

            double[][]matrixB = matrixCreatorB();
            //Used for ensuring correct matrix
            do {
                checker = MatricesChecker.YorN(matrixB);

                //Checks if matrix is correct
                if(checker == 'N' || checker == 'n') {
                    matrixB = matrixCreator();
                }
            }while (checker == 'N' || checker == 'n');

            // Create final Matrix
            matrixFinal = new double[noOfRowsMatrixOne][noOfColumnsMatrixOne];

            //Adder for each row
            for(int matrixRowAdder = 0; matrixRowAdder<noOfRowsMatrixOne; matrixRowAdder++){

                //Adder for each colum
                for(int matrixColoumAdder = 0; matrixColoumAdder < noOfColumnsMatrixOne; matrixColoumAdder++){
                    //Adds A to B
                    BigDecimal finalMatrixNumberBigDecimal = BigDecimal.valueOf((matrixA[matrixRowAdder][matrixColoumAdder] + matrixB[matrixRowAdder][matrixColoumAdder]));
                    BigDecimal finalMatrixNumberRounded = finalMatrixNumberBigDecimal.setScale(8, RoundingMode.HALF_UP); // Rounds to 8 decimal places

                    matrixFinal[matrixRowAdder][matrixColoumAdder] = finalMatrixNumberRounded.doubleValue();
                }
            }
            return matrixFinal;
        }

        if(function == 2){
            double[][]matrixA = matrixCreator();

            //Used for ensuring correct matrix
            do {
                checker = MatricesChecker.YorN(matrixA);

                //Checks if matrix is correct
                if(checker == 'N' || checker == 'n') {
                    matrixA = matrixCreator();
                }
            }while (checker == 'N' || checker == 'n');

            double[][]matrixB = matrixCreatorB();

            //Used for ensuring correct matrix
            do {
                checker = MatricesChecker.YorN(matrixB);

                //Checks if matrix is correct
                if(checker == 'N' || checker == 'n') {
                    matrixB = matrixCreator();
                }
            }while (checker == 'N' || checker == 'n');

            // Create final Matrix
            matrixFinal = new double[noOfRowsMatrixOne][noOfColumnsMatrixOne];

            //Adder for each row
            for(int matrixRowAdder = 0; matrixRowAdder<noOfRowsMatrixOne; matrixRowAdder++){

                //Adder for each colum
                for(int matrixColoumAdder = 0; matrixColoumAdder < noOfColumnsMatrixOne; matrixColoumAdder++){
                    //Subtracts A from B
                    BigDecimal finalMatrixNumberBigDecimal = BigDecimal.valueOf((matrixA[matrixRowAdder][matrixColoumAdder] - matrixB[matrixRowAdder][matrixColoumAdder]));
                    BigDecimal finalMatrixNumberRounded = finalMatrixNumberBigDecimal.setScale(8, RoundingMode.HALF_UP); // Rounds to 8 decimal places

                    matrixFinal[matrixRowAdder][matrixColoumAdder] = finalMatrixNumberRounded.doubleValue();
                }
            }
            return matrixFinal;
        }
        if(function == 4){
            double[][]matrixA = matrixCreator();

            //Used for ensuring correct matrix
            do {
                checker = MatricesChecker.YorN(matrixA);

                if(checker == 'N' || checker == 'n') {
                    matrixA = matrixCreator();
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
    public static double[][] matrixCreator() {
        //input no of rows
        System.out.print("Enter Number of Rows: ");
        // checks if the number of rows is valid
        while ((noOfRowsMatrixOne = MatricesChecker.byteSizeInt()) == 0){
            System.out.print("Error please enter a number between 1-127: ");
        }

        //input no of columns
        System.out.print("Enter Number of Columns: ");
        // checks if the number of coloms is valid
        while ((noOfColumnsMatrixOne = MatricesChecker.byteSizeInt()) == 0){
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

                double numberRowCounterInMatrix = MatricesChecker.getNumberRowCounterInMatrix(rowListWithSpace, positionCounter);
                matrixOneSize[rowCounter][positionCounter] = numberRowCounterInMatrix;
            }

            // for printing out next row before you enter each row
            if(noOfRowsMatrixOne != (rowCounter+1)){
                System.out.print("Enter next row:\t\t");
            }
        }

        return matrixOneSize;
    }


    // used to create a matrix
    public static double[][] matrixCreatorB() {
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
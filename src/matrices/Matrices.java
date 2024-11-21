package matrices;

import java.math.RoundingMode;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.math.BigDecimal;

public class Matrices {
    //Declaring Variables
    protected static byte noOfRowsMatrixOne;
    protected static byte noOfColumnsMatrixOne;

    public static void main(String[] args) {
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
            double[][]matrixA = matrixCreator();

            //Used for ensuring correct matrix
            char checker;
            do {

                //Print the out the matrix
                for (double[] row : matrixA) {
                    System.out.println(Arrays.toString(row));
                }
                //Checks if matrix is correct
                System.out.print("Is matrix A correct Y/N: ");
                checker = YorN();

                if(checker == 'N' || checker == 'n') {
                    matrixA = matrixCreator();
                }
            }while (checker == 'N' || checker == 'n');

            double[][]matrixB = matrixCreatorB();
            //Used for ensuring correct matrix
            do {

                //Print the out the matrix
                for (double[] row : matrixB) {
                    System.out.println(Arrays.toString(row));
                }
                System.out.print("Is matcorrectrix B correct Y/N: ");
                checker = YorN();

                //Checks if matrix is correct
                if(checker == 'N' || checker == 'n') {
                    matrixB = matrixCreator();
                }
            }while (checker == 'N' || checker == 'n');

            // Create final Matrix
            double[][]matrixFinal = new double[noOfRowsMatrixOne][noOfColumnsMatrixOne];

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
            //Print the out the matrix
            for (double[] row : matrixFinal) {
                System.out.println(Arrays.toString(row));
            }

        }

        if(function == 2){
            double[][]matrixA = matrixCreator();

            //Used for ensuring correct matrix
            char checker;
            do {

                //Print the out the matrix
                for (double[] row : matrixA) {
                    System.out.println(Arrays.toString(row));
                }
                System.out.print("Is matrix A correct Y/N: ");
                checker = YorN();

                //Checks if matrix is correct
                if(checker == 'N' || checker == 'n') {
                    matrixA = matrixCreator();
                }
            }while (checker == 'N' || checker == 'n');

            double[][]matrixB = matrixCreatorB();
            //Used for ensuring correct matrix
            do {

                //Print the out the matrix
                for (double[] row : matrixB) {
                    System.out.println(Arrays.toString(row));
                }
                System.out.print("Is matrix B correct Y/N: ");
                checker = YorN();

                //Checks if matrix is correct
                if(checker == 'N' || checker == 'n') {
                    matrixB = matrixCreator();
                }
            }while (checker == 'N' || checker == 'n');

            // Create final Matrix
            double[][]matrixFinal = new double[noOfRowsMatrixOne][noOfColumnsMatrixOne];

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
            //Print the out the matrix
            for (double[] row : matrixFinal) {
                System.out.println(Arrays.toString(row));
            }

        }
        if(function == 4){
            double[][]matrixA = matrixCreator();

            //Used for ensuring correct matrix
            char checker;
            do {

                //Print the out the matrix
                for (double[] row : matrixA) {
                    System.out.println(Arrays.toString(row));
                }
                //Checks if matrix is correct
                System.out.print("Is matrix A correct Y/N: ");
                checker = YorN();

                if(checker == 'N' || checker == 'n') {
                    matrixA = matrixCreator();
                }
            }while (checker == 'N' || checker == 'n');


            //Create a matrix of with row = coloms and colums equal to rows from MatrixA
            double[][]matrixFinal = new double[noOfColumnsMatrixOne][noOfRowsMatrixOne];

            //Cycle through each row
            for(int matrixRowAdder = 0; matrixRowAdder<noOfRowsMatrixOne; matrixRowAdder++){

                //Cycle through each colum
                for(int matrixColoumAdder = 0; matrixColoumAdder < noOfColumnsMatrixOne; matrixColoumAdder++){

                    matrixFinal[matrixColoumAdder][matrixRowAdder] = matrixA[matrixRowAdder][matrixColoumAdder];
                }
            }
            //Print the out the matrix
            for (double[] row : matrixFinal) {
                System.out.println(Arrays.toString(row));
            }
        }

    }

    public static char YorN(){
        Scanner input = new Scanner(System.in);

        return input.next().charAt(0);
    }

    // Ensures an int of size byte is inputted
    public static byte byteSizeInt() {
        Scanner input = new Scanner(System.in);
        try {
            return input.nextByte();
        } catch (InputMismatchException e) {
            // This block catches InputMismatchException
            // if patterns don't match e.g. a letter or a number too big
            return 0;
        }
    }

    // used to create a matrix
    public static double[][] matrixCreator() {
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

                double numberRowCounterInMatrix = getNumberRowCounterInMatrix(rowListWithSpace, positionCounter);
                matrixOneSize[rowCounter][positionCounter] = numberRowCounterInMatrix;
            }

            // for printing out next row before you enter each row
            if(noOfRowsMatrixOne > (rowCounter+1)){
                System.out.print("Enter next row:\t\t");
            }
        }

        return matrixOneSize;
    }


    private static double getNumberRowCounterInMatrix(String rowListWithSpace, int positionCounter) {
        double numberRowCounterInMatrix;
        // Ensures there is a number in each coloum or enters zero
        try{
            numberRowCounterInMatrix = Double.parseDouble(rowListWithSpace.split(" ")[positionCounter]);
            //NumberFormatException is an exception that occours when trying to convert a string to a number when string doesn't have an appropriate format
            //Prevents ArrayIndexOutOfBoundsException when trying to add something out of bounds to an array
        }catch(NumberFormatException | ArrayIndexOutOfBoundsException e){
            numberRowCounterInMatrix = 0;
        }
        return numberRowCounterInMatrix;
    }
}
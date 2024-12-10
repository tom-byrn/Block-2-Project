package matrices;


import menu.Start;

import java.util.Arrays;

// Class imports
import static matrices.MatricesChecker.*;

public class MatricesManager implements Start {


    //Declaring Variables
    protected static int noOfRowsInMatrixA;
    protected static int noOfColumnsMatrixA;
    protected static char checker;

    public static void start() {

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
                        """);
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
            MatrixMultiplication.multiplicationStart();
        }

        if(function == 4){
            TransposeOfAMatrix.transposeStart();
        }

        if(function == 5){
            MatrixPower.powerCalculationStart();
        }

        if(function == 6){
            LUFactorisation.factoriser();
        }

        if(function == 7){
            Determinant.getDeterminantOfAMatrix();
        }

        if(function == 8) {
            EigenvalueCalculator.getDominantEigenvalue();
        }
        if(function == 9) {
            SolveSimulationsEquations.solveEquation();
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

    // used to create a square matrix
    protected static double[][] squareMatrixCreator() {
        //input no of rows
        System.out.print("Enter Number of Rows and Columns (n * n): ");
        System.out.println("The Number of Rows and Columns will be identical");

        // checks if the number of rows is valid
        while ((noOfRowsInMatrixA = noOfColumnsMatrixA = intSizeInt()) == 0){
            System.out.print("Error please enter a number between 1-2147483647: ");
        }

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

    protected static void printMatrix(double[][] matrixToPrint) {
        //Print the out the matrix
        for (double[] row : matrixToPrint) {
            System.out.println(Arrays.toString(row));
        }
    }
}
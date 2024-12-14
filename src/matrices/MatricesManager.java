package matrices;


import menu.Start;

// Class imports
import static matrices.NumberChecker.*;

public class MatricesManager implements Start {

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
}
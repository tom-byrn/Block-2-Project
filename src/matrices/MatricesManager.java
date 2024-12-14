package matrices;


import menu.MenuManager;
import menu.MenuText;
import menu.Start;

// Class imports
import java.util.InputMismatchException;
import java.util.Scanner;

import static matrices.NumberChecker.*;
import static menu.Colours.*;

public class MatricesManager implements Start {

    public static void start() {
        MenuManager.clearScreen();
        Scanner scanner = new Scanner(System.in);
        boolean currentlySelecting = false;
        while (currentlySelecting) {

            try {


                MenuText.matricesText();
                // select function using a text block
                System.out.println(CYAN + "╔════════════════════════════════════════════════════════════════╗" + RESET);
                System.out.println(CYAN + "║" + WHITE + "                     Matrix Operations Menu                     " + CYAN + "║" + RESET);
                System.out.println(CYAN + "╠════════════════════════════════════════════════════════════════╣" + RESET);
                System.out.println(CYAN + "║                                                                ║");
                System.out.println(CYAN + "║" + BRIGHT_BLUE + "    Enter (1) for Addition                                     " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_GREEN + "    Enter (2) for Subtraction                                  " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_YELLOW + "    Enter (3) for Multiplication                               " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_CYAN + "    Enter (4) for Transpose                                    " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_PURPLE + "    Enter (5) for Power                                        " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_BLUE + "    Enter (6) for LU Factorisation                             " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_GREEN + "    Enter (7) for det(A)                                       " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_YELLOW + "    Enter (8) for Dominant Eigenvalues                        " + CYAN + "  ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_CYAN + "    Enter (9) to Solve Simultaneous Equations                 " + CYAN + "  ║" + RESET);
                System.out.println(CYAN + "║                                                                ║");
                System.out.println(CYAN + "║" + BRIGHT_RED + "    Enter (0) to return to the menu                            " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║                                                                ║");
                System.out.println(CYAN + "╚════════════════════════════════════════════════════════════════╝" + RESET);
                System.out.print(CYAN + "Enter a choice: " + RESET);


                // checking is a valid function selected
                byte function;
                while ((function = byteSizeInt()) < 1 || function > 9) {
                    System.out.print("Error please enter a number between 1-9: ");
                }


                // Checks which function is selected
                if (function == 1) {
                    //Calls Addition method
                    MatrixAdder.addition();
                }

                if (function == 2) {
                    MatrixAdder.subtraction();
                }

                if (function == 3) {
                    MatrixMultiplication.multiplicationStart();
                }

                if (function == 4) {
                    TransposeOfAMatrix.transposeStart();
                }

                if (function == 5) {
                    MatrixPower.powerCalculationStart();
                }

                if (function == 6) {
                    LUFactorisation.factoriser();
                }

                if (function == 7) {
                    Determinant.getDeterminantOfAMatrix();
                }
                if (function == 8) {
                    EigenvalueCalculator.getDominantEigenvalue();
                }
                if (function == 9) {
                    SolveSimulationsEquations.solveEquation();
                }
            } catch(InputMismatchException e){
                System.out.println("Please enter a valid number!\n");
                scanner.nextLine(); // Clear invalid input from the scanner
            }
        }
    }
}
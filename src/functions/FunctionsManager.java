package functions;
import calculations.Calculations;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class FunctionsManager extends Calculations {

    // Initializing variables
    public static String substitution = "";
    public static String substitutedExpression = "";
    public static List<String> substitutedExpressions = new ArrayList<>();

    public static void start() {
        Scanner input = new Scanner(System.in);

        boolean currentlySelecting = true;
        while (currentlySelecting) {
            try {

                // Prompt user to select a calculator function
                System.out.println("Welcome to Functions 📈\n");
                System.out.println("1. Evaluate a single variable function");
                System.out.println("2. Evaluate a multi-variate function");
                System.out.println("3. Compose functions");
                System.out.println("4. Bisection method");
                System.out.println("5. Secant method");
                System.out.println("6. Graph a function");
                System.out.println("7. Exit\n");
                System.out.print("Enter your choice: ");

                // Scanner object scans for user input
                int selectorNum = input.nextInt();
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
                    case 7 -> {
                        Functions.exit();
                        break;
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!\n");
                input.nextLine(); // Clear invalid input from the scanner
            }
        }
    }

}

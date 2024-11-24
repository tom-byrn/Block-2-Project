package functions;
import algebra.AlgebraManager;
import calculations.Calculations;
import calculations.CalculationsManager;
import com.sun.tools.javac.Main;
import matrices.Matrices;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class FunctionsManager extends Calculations {

    // Initializing variables
    public static String substitution = "";
    public static String substitutedExpression = "";
    public static List<String> substitutedExpressions = new ArrayList<>();

    public static void start() {}

    public static void singleVariableFunction() {
        Scanner input = new Scanner(System.in);

        // Setting parameters for the function
        System.out.println("Enter a function (e.g., x^2 + 3x + 5):");
        String functionInput = input.nextLine();

        // Error handling for start range
        double startRange = getValidDouble(input, "Enter the start of the range:");

        double endRange;
        // Error handling for end range
        while (true) {
            endRange = getValidDouble(input, "Enter the end of the range:");

            if (endRange > startRange) {
                break; // Valid end of range, exit the loop
            } else {
                System.out.printf("End of range must be greater than %.2f. Please enter a valid end of range.\n", startRange);
            }
        }

        double stepSize;
        // Error handling for step size
        while (true) {
            stepSize = getValidDouble(input, "Enter the step size:");

            if (stepSize > 0) {
                break; // valid step size, exit the loop
            } else {
                System.out.println("Step size must be greater than 0. Please enter a valid step size.");
            }
        }

        // Header of display table
        System.out.printf("%-10s%-10s\n", "x", "f(x)");
        System.out.println("--------------------");

        // Creating array of substituted expressions, subbing into the function
        for (double i = startRange; i <= endRange; i += stepSize) {

            // substituting in variables
            substitution = "(" + i + ")";
            substitutedExpression = functionInput.replaceAll("x", substitution);
            substitutedExpressions.add(substitutedExpression);

            // Calculating the result using Calculations
            Calculations calculations = new Calculations(substitutedExpression);
            double result = calculations.getAnswer();
            System.out.printf("%-10.2f%-10.2f\n", i, result);
        }
        System.out.println();
    }

    // Helper method to validate double input
    private static double getValidDouble(Scanner input, String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                return Double.parseDouble(input.nextLine()); // Parse user input as a double
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
            }
        }
    }

    public static void multiVariateFunction() {}

    public static void composeFunctions() {

    }

    public static void bisectionMethod() {}

    public static void secantMethod() {}

    public static void exit() {
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        boolean currentlySelecting = true;

        while (currentlySelecting) {
            try {

                // Prompt user to select a calculator function
                FunctionsManager.start();
                System.out.println("Welcome to Functions 📈\n");
                System.out.println("1. Evaluate a single variable function");
                System.out.println("2. Evaluate a multi-variate function");
                System.out.println("3. Compose functions");
                System.out.println("4. Bisection method");
                System.out.println("5. Secant method");
                System.out.println("6. Exit\n");
                System.out.print("Enter your choice: ");

                // Scanner object scans for user input
                int selectorNum = input.nextInt();
                System.out.println();

                // Switch case for choosing an option
                switch (selectorNum) {
                    case 1 -> {
                        FunctionsManager.singleVariableFunction();
                        break;
                    }
                    case 2 -> {
                        FunctionsManager.multiVariateFunction();
                        break;
                    }
                    case 3 -> {
                        FunctionsManager.composeFunctions();
                        break;
                    }
                    case 4 -> {
                        FunctionsManager.bisectionMethod();
                        break;
                    }
                    case 5 -> {
                        FunctionsManager.secantMethod();
                        break;
                    }
                    case 6 -> {
                        FunctionsManager.exit();
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

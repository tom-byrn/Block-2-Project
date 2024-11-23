package functions;
import calculations.Calculations;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class FunctionsManager extends Calculations {

    public static void start() {
        Scanner input = new Scanner(System.in);
        System.out.println("Function chosen: Functions");

        // Initializing variables
        String substitution = "";
        String substitutedExpression = "";
        List<String> substitutedExpressions = new ArrayList<>();

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

    public static void main(String[] args) {
        FunctionsManager.start();
    }
}

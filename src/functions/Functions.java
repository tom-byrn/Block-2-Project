package functions;

import algebra.Algebra;
import calculations.Calculations;

import java.util.Scanner;

import static functions.FunctionsManager.*;

public class Functions extends Algebra {

    static Scanner scanner = new Scanner(System.in);
    String functionInput;
    double startRange;
    double endRange;
    double stepSize;

    public Functions(String functionInput){ //Constructor that takes initial input
        this.functionInput = functionInput;
    }

    public Functions(String functionInput, double startRange, double endRange){ //Constructor taking input and range
        this.functionInput = functionInput;
        this.startRange = startRange;
    }

    public Functions(){

    }

    public String promptFunctionInput(){
        System.out.print("Enter a function (e.g., x^2 + 3x + 5): ");
        functionInput = scanner.nextLine();
        return functionInput;
    }

    public double promptStartRange(){
        startRange = getValidDouble(scanner, "Enter the start of the range: ");
        return startRange;
    }

    public double promptEndRange(){
        endRange = getValidDouble(scanner, "Enter the end of the range: ");
        return endRange;
    }

    public double promptStepSize(){
        stepSize = getValidDouble(scanner, "Enter the step size: ");
        return stepSize;
    }

    public double getStartRange(){
        return startRange;
    }

    public double getEndRange(){
        return endRange;
    }

    public double getStepSize(){
        return stepSize;
    }


    public static void singleVariableFunction() {
        Scanner input = new Scanner(System.in);

        // Setting parameters for the function
        Functions f = new Functions();
        String functionInput = f.promptFunctionInput();

        // Error handling for start range
        double startRange = getValidDouble(scanner, "Enter the start of the range:");

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

    public static void composeFunctions() {}

    public static void bisectionMethod() {}

    public static void secantMethod() {}

    public static void exit() {
    }

}

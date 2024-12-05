package functions;

import algebra.Algebra;
import calculations.Calculations;

import java.util.*;
import java.util.stream.Collectors;

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

    public Functions(String functionInput, double startRange, double endRange, double stepSize){ //Constructor taking input, range, step size
        this.functionInput = functionInput;
        this.startRange = startRange;
        this.endRange = endRange;
        this.stepSize = stepSize;
    }

    public Functions(){

    }

    public String promptFunctionInput(){
        System.out.print("Enter a function (e.g., x^2 -5x +6): ");
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

    public double promptValidDouble(String message){
        stepSize = getValidDouble(scanner, message);
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

    public String getFunctionInput(){
        return functionInput;
    }


    public static void singleVariableFunction() {
        Functions f = new Functions();
        String functionInput = f.promptFunctionInput();

        // Error handling for start range
        double startRange = f.promptStartRange();

        // Error handling for end range
        double endRange;
        while (true) {
            endRange = f.promptEndRange();
            if (endRange > startRange) {
                break; // Valid end of range, exit the loop
            } else {
                System.out.printf("End of range must be greater than %.2f. Please enter a valid end of range.\n", startRange);
            }
        }

        // Error handling for step size
        double stepSize;
        while (true) {
            stepSize = f.promptValidDouble("Enter the step size:");
            if (stepSize > 0) {
                break; // valid step size, exit the loop
            } else {
                System.out.println("Step size must be greater than 0. Please enter a valid step size.");
            }
        }

        // Header of display table
        System.out.printf("%-10s%-10s\n", "x", "f(x)");
        System.out.println("--------------------");

        // subbing into the function and printing
        for (double i = startRange; i <= endRange; i += stepSize) {
            double result = subIn(functionInput, i);
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

    // method to sub a variable into a function
    private static double subIn(String function, double variable) {
        substitution = "(" + variable + ")";
        substitutedExpression = function.replaceAll("x", substitution);
        Calculations calculations = new Calculations(substitutedExpression);
        return calculations.getAnswer();
    }

    public static void multiVariateFunction() {
        Functions f = new Functions();
        String functionInput = f.promptFunctionInput();

        String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        ArrayList<String> variables = new ArrayList<>(); // a list of all the variables in the function
        for (String letter : alphabet) {
            for (int i=0; i<functionInput.length(); i++) {
                if (letter.equalsIgnoreCase(String.valueOf(functionInput.charAt(i)))) {
                    variables.add(String.valueOf(functionInput.charAt(i)));
                }
            }
        }
        // remove duplicates
        Set<String> uniqueVariables = new HashSet<>();
        for (String variable : variables) {
            uniqueVariables.add(variable);
        }

        // subbing into function
        substitutedExpression = functionInput;
        for (String variable: uniqueVariables) {
            System.out.printf("%s = ", variable);
            double value = scanner.nextDouble();
            substitution = "(" + value + ")";
            substitutedExpression = substitutedExpression.replaceAll(variable, substitution);
        }

        Calculations calculations = new Calculations(substitutedExpression);
        System.out.println(calculations.getAnswer());
    }

    public static void composeFunctions() {
        Scanner input = new Scanner(System.in);
        Functions f = new Functions();
        String fX = f.promptFunctionInput(); // f(x)
        String gX = f.promptFunctionInput(); // g(x)

        System.out.println("Enter a value for x: ");
        double x = input.nextDouble();

        // finding g(variable)
        double gVariable = subIn(gX, x);

        // finding f(g(x))
        double result = subIn(fX, gVariable);

        System.out.print("f(g(x)) = ");
        System.out.println(result);
    }

    public static void bisectionMethod() {
        Functions f = new Functions();
        String functionInput = f.promptFunctionInput(); // gets function

        double a; // a, start of range
        double b; // b, end of range
        double fA; // f(a)
        double fB; // f(b)

        while (true) {
            a = f.promptStartRange();

            while (true) {
                b = f.promptEndRange();
                if (b > a) {
                    break; // Valid end of range, exit the loop
                } else {
                    System.out.printf("b must be greater than %.2f. Please enter a valid value for b.\n", a);
                }
            }

            fA = subIn(functionInput, a);
            fB = subIn(functionInput, b);

            if ((fA * fB) < 0) {
                break;
            } else {
                System.out.println("There is no root in this interval.");
            }
        }

        double tolerance = tolerance();

        double c;
        double fC;
        while ( (Math.abs(b-a)) /2 > tolerance) {
            c = (a+b) /2;
            fC = subIn(functionInput, c);
            if (fC == 0) {
                break;
            }
            fA = subIn(functionInput, a);
            if ((fA * fC) < 0) {
                b=c;
            } else {
                a=c;
            }
        }
        double root = (a+b) /2;
        System.out.printf("The root is approximately %.2f\n", root);
    }

    private static double tolerance() {
        Functions f = new Functions();
        double tolerance;
        while (true) {
            tolerance = f.promptValidDouble("Enter tolerance: ");
            if (tolerance > 0) {
                break; // valid step size, exit the loop
            } else {
                System.out.println("Tolerance must be greater than 0. Please enter a valid value:");
            }
        }
        return tolerance;
    }

    public static void secantMethod() {
        Functions f = new Functions();
        String functionInput = f.promptFunctionInput(); // enter f(x)

        double x0 = f.promptValidDouble("Enter first guess, x0: ");
        double x1 = f.promptValidDouble("Enter second guess, x1: ");
        double x2;
        double tolerance = tolerance();
        double fX0;
        double fX1;
        double fX3;

        while (true) {
            fX0 = subIn(functionInput, x0);
            fX1 = subIn(functionInput, x1);

            x2 = x1 - (fX1 * (x1-x0) / (fX1 - fX0));
            if ( Math.abs((x2 - x1)) < tolerance ) {
                break; // within tolerance, exit loop
            }
            x0 = x1;
            x1 = x2;
        }
        System.out.printf("The root is approximately %.2f\n", x2);
    }

    public static void exit() {
    }

}

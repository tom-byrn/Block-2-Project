package functions;

//import algebra.Algebra;
import calculations.Calculations;
import functions.FunctionsManager.*;
import java.util.*;
import java.util.stream.Collectors;
import static functions.FunctionsManager.substitutedExpression;
import static functions.FunctionsManager.substitution;


public class Functions  {
    static Scanner scanner = new Scanner(System.in);
    String functionInput;
    double startRange;
    double endRange;
    double stepSize;

    public Functions(){}

    // Constructor taking input, range, step size
    public Functions(String functionInput, double startRange, double endRange, double stepSize){
        this.functionInput = functionInput;
        this.startRange = startRange;
        this.endRange = endRange;
        this.stepSize = stepSize;
    }

    // Helper method to validate double input
    private static double getValidDouble(String prompt) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine()); // Parse user input as a double
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
            }
        }
    }

    public String promptFunctionInput(String message){
        System.out.print(message);
        functionInput = scanner.nextLine();
        return functionInput;
    }

    public double promptStartRange(){
        startRange = getValidDouble("Enter the start of the range: ");
        return startRange;
    }

    public double promptEndRange(double startRange){
        double endRange = getValidDouble("Enter the end of the range: ");
        while (endRange < startRange) {
            System.out.printf("End of range must be greater than %.2f.\n", startRange);
            endRange = getValidDouble("Enter the end of the range: ");
        }
        return endRange;
    }

    public double greaterThan0(String message){
        // Error handling for step size
        double greaterThan0;
        while (true) {
            Functions f = new Functions();
            greaterThan0 = getValidDouble(message);
            if (greaterThan0 > 0) {
                break; // valid double, exit the loop
            } else {
                System.out.println("Must be greater than 0. Please enter a valid number.");
            }
        }
        return greaterThan0;
    }

    // method to sub a variable into a function
    private static double subIn(String function, double variable) {
        String substitution = "(" + variable + ")";
        String substitutedExpression = function.replaceAll("x", substitution);
        Calculations calculations = new Calculations(substitutedExpression);
        return calculations.getAnswer();
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

    public void setStartRange(double startRange){
        this.startRange = startRange;
    }

    public void setEndRange(double endRange){
        this.endRange = endRange;
    }

    public void setStepSize(double stepSize){
        this.stepSize = stepSize;
    }

    public static void singleVariableFunction() {
        Functions f = new Functions();
        String functionInput = f.promptFunctionInput("Enter a function (e.g. x^2 + 3x + 4): ");

        double startRange = f.promptStartRange();
        double endRange = f.promptEndRange(startRange);
        double stepSize = f.greaterThan0("Enter step size: ");

        // Header of the table with enhanced style
        System.out.println("╔══════════════════════╦══════════════════════╗");
        System.out.printf("║ %-20s ║ %-20s ║\n", "x", "f(x)");
        System.out.println("╠══════════════════════╬══════════════════════╣");

        // Subbing into the function and printing each row
        for (double i = startRange; i <= endRange; i += stepSize) {
            double result = subIn(functionInput, i);
            System.out.printf("║ %-20.10f ║ %-20.10f ║\n", i, result);
        }

        // Closing the table
        System.out.println("╚══════════════════════╩══════════════════════╝");

        System.out.println();
        scanner.nextLine();
    }


    public static void multiVariateFunction() {
        Functions f = new Functions();
        String functionInput = f.promptFunctionInput("Enter a function (e.g. a^2 + 2b + c): ");

        String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        Set<String> variables = new HashSet<>(); // a list of all the unique variables in the function
        for (String letter : alphabet) {
            for (int i=0; i<functionInput.length(); i++) {
                if (letter.equalsIgnoreCase(String.valueOf(functionInput.charAt(i)))) {
                    variables.add(String.valueOf(functionInput.charAt(i)));
                }
            }
        }

        // subbing into function
        substitutedExpression = functionInput;
        for (String variable: variables) {
            System.out.printf("%s = ", variable);
            double value = scanner.nextDouble();
            substitution = "(" + value + ")";
            substitutedExpression = substitutedExpression.replaceAll(variable, substitution);
        }

        Calculations calculations = new Calculations(substitutedExpression);
        String variablesInputted ="";
        for (String uniqueVariable : variables) {
            variablesInputted += uniqueVariable;
        }
        System.out.println(variablesInputted);
        System.out.printf("f(%s) = %.2f\n", variablesInputted, calculations.getAnswer());
        scanner.nextLine();
        scanner.nextLine();
    }

    public static void composeFunctions() {
        Scanner scanner = new Scanner(System.in);
        Functions f = new Functions();
        String fX = f.promptFunctionInput("\nEnter f(x) (e.g. 2x + 5): "); // f(x)
        String gX = f.promptFunctionInput("\nEnter g(x) (e.g. 3x + 11): "); // g(x)

        double x = getValidDouble("\nEnter a value for x: ");

        // finding g(variable)
        double gVariable = subIn(gX, x);

        // finding f(g(x))
        double result = subIn(fX, gVariable);

        System.out.print("\nf(g(x)) = ");
        System.out.println(result);
        scanner.nextLine();
    }

    public static void bisectionMethod() {
        Functions f = new Functions();
        String functionInput = f.promptFunctionInput("Enter a function (e.g. x^2 -4): "); // gets function

        double a; // a, factorPrompt of range
        double b; // b, end of range
        double fA; // f(a)
        double fB; // f(b)

        while (true) {
            a = f.promptStartRange();
            b = f.promptEndRange(a);

            fA = subIn(functionInput, a);
            fB = subIn(functionInput, b);

            if ((fA * fB) < 0) {
                break;
            } else {
                System.out.println("There is no root in this interval.");
            }
        }

        double tolerance = f.greaterThan0("Enter tolerance: ");

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
        scanner.nextLine();
    }

    public static void secantMethod() {
        Functions f = new Functions();
        String functionInput = f.promptFunctionInput("Enter a function (e.g. x^2 -4): "); // enter f(x)

        double x0 = getValidDouble("Enter first guess, x0: ");
        double x1 = getValidDouble("Enter second guess, x1: ");
        double x2;
        double tolerance = f.greaterThan0("Enter tolerance: ");
        double fX0;
        double fX1;

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
        scanner.nextLine();
    }

    @Override //The toString method can be used for debugging e.g. print(f.toString)
    public String toString(){
        return String.format("Function: %s %nStart Range: %f %nEnd Range: %f %nStep Size: %f %n", getFunctionInput(), getStartRange(), getEndRange(), getStepSize());
    }

}

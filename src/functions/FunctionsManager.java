package functions;
import calculations.Calculations;
import menu.MenuManager;


import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class FunctionsManager {

    // Initializing variables
    public static String substitution = "";
    public static String substitutedExpression = "";
    public static List<String> substitutedExpressions = new ArrayList<>();

    public static void start() {
        Scanner scanner = new Scanner(System.in);

        boolean currentlySelecting = true;
        while (currentlySelecting) {
            try {

                // Prompt user to select a choice for functions
                System.out.println("""
                        Welcome to Functions 📈
                        
                        Enter (1) to evaluate a single-variate function
                        Enter (2) to evaluate a multivariate function
                        Enter (3) to compose a function
                        Enter (4) for the bisection method
                        Enter (5) for the secant method
                        Enter (6) to graph a function
                        Enter (0) to return to the menu
                        
                        """);
                System.out.print("Enter a choice: ");

                // Scanner object scans for user input
                int selectorNum = scanner.nextInt();
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
                    case 0 -> {
                        MenuManager.clearScreen();
                        MenuManager.callMenu();
                        break;
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!\n");
                scanner.nextLine(); // Clear invalid input from the scanner
            }
        }
    }

}

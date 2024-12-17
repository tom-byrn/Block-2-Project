package algebra;

import menu.MenuManager;
import menu.MenuText;
import menu.Start;

import java.util.Scanner;

public class AlgebraManager implements Start, ProcessorAlgebra {

    public void start(){

        MenuManager.clearScreen();
        MenuText.algebraText();

        // Create a scanner to take user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for an algebraic expression
        System.out.print("Enter an algebraic expression to simplify:");

        // Read the algebraic expression entered by the user
        String expression = scanner.nextLine();

        // Simplify the input expression using the simplifyExpression method
        String simplifiedExpression = AlgebraSimplifier.simplifyExpression(expression);

        // Process output
        simplifiedExpression = processingRegex(simplifiedExpression);

        // Output the simplified expression
        System.out.print("\nSimplified expression: " + simplifiedExpression);

        //Wait for user to hit enter before clearing the screen
        scanner.nextLine();
        MenuManager.clearScreen();
        MenuManager.callMenu();
    }

    private static String processingRegex(String expression){
        ProcessorAlgebra.processingRegex(expression);

        //add spaces
        expression = expression.replaceAll("\\+", " + ");
        expression = expression.replaceAll("-", " - ");
        return expression;
    }

}

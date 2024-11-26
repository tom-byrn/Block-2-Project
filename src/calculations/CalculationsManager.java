package calculations;

import java.io.IOException;
import java.util.Scanner;

public class CalculationsManager {

    public static void start() throws IOException {

        String calculationInput; //Initialising calculation input string
        Scanner scanner = new Scanner(System.in); //Creating instance of the scanner class
        Calculations calculations = new Calculations(); //Creating object using default constructor

        //Error handling for incorrect expressions
        boolean validInput = false;
        while(!validInput){
            try {
                System.out.print("Please enter a sum: ");
                calculationInput = scanner.nextLine();
                calculations = new Calculations(calculationInput); //Updating object with the functioning constructor
                System.out.println("\n" + calculations.getOriginalInput() + " = " + calculations.getAnswer());
                validInput = true;
            } catch (ArithmeticException e) {
                System.out.println("\nInvalid sum: Division by zero is not allowed!\n");
            } catch (IllegalArgumentException e) {
                System.out.println("\nInvalid sum: Unsupported operator or function!\n");
            } catch (IllegalStateException e) {
                System.out.println("\nInvalid sum: Improper expression format!\n");
            } catch (Exception e) {
                System.out.println("\nInvalid sum!\n");
            }
        }
    }
}

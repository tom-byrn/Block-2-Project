package calculations;

import java.io.IOException;

public class CalculationsManager {

    public static void start() throws IOException {

        //Create an instance of the Calculations class
        Calculations calculations = new Calculations();

        //Error handling for incorrect expressions
        boolean validInput = false;
        while(!validInput){
            try {
                calculations.promptForCalculation(); //Prompts the user to input a sum
                validInput = true;
            } catch (ArithmeticException e) {
                System.out.println("Invalid sum: Division by zero is not allowed!\n");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid sum: Unsupported operator or function!\n");
            } catch (IllegalStateException e) {
                System.out.println("Invalid sum: Improper expression format!\n");
            } catch (Exception e) {
                System.out.println("Invalid sum!\n");
            }
        }

        System.out.println("\n" + calculations.getOriginalInput() + " = " + calculations.getAnswer());
    }
}

package calculations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Calculations {

    protected String calculationInput;
    protected double answer;

    public Calculations(String calculationInput){
        this.calculationInput = calculationInput;
    }

    public Calculations(){}

    protected String getCalculationInput(){
        return calculationInput;
    }

    // Method to prompt the user for a calculation and store the input
    public void promptForCalculation() {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object
        System.out.println("Please enter a calculation:");
        calculationInput = scanner.nextLine(); // Store the user input
        System.out.println("You entered: " + calculationInput); // Echo the input back to the user
        evaluate(calculationInput);
    }

    protected double evaluate(String calculationInput){
        this.calculationInput = calculationInput;

        // Shunting Yard Algorithm implementation (https://brilliant.org/wiki/shunting-yard-algorithm/)

        //Tokenize the expression



        return answer;
    }

}

import algebra.AlgebraManager;
import calculations.CalculationsManager;
import functions.FunctionsManager;
import matrices.MatricesManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Initialising objects
        Scanner scanner = new Scanner(System.in);

        //Boolean variable is true for when user in choosing an option
        boolean currentlySelecting = true;

        while(currentlySelecting) {

            //Prompt user to select a calculator function
            System.out.println("""
                    Please enter a number
                    
                    Enter (1) for calculations 🧮
                    Enter (2) for functions 📈
                    Enter (3) for matrices ⏹️
                    Enter (4) for algebra & complex numbers ℹ️
                    Enter (5) for ... not sure what else to have here 🏞️
                    
                    Enter (0) to exit the application 👋
                    
                    """);

            //Scanner object scans for user inpput
            int selectorNum = scanner.nextInt();
            //Switch case for choosing a calculator function to use
            switch(selectorNum){
                case 1 -> {
                    currentlySelecting = false;
                    CalculationsManager.start();
                }
                case 2 -> {
                    currentlySelecting = false;
                    FunctionsManager.start();
                }
                case 3 -> {
                    currentlySelecting = false;
                    MatricesManager.start();
                }
                case 4 -> {
                    currentlySelecting = false;
                    AlgebraManager.start();
                }
                case 5 -> {
                    currentlySelecting = false;
                    System.out.println("Option 5 chosen");
                }
                case 0 -> {
                    currentlySelecting = false;
                    System.out.println("Goodbye!");
                }
                default -> {
                    currentlySelecting = true;
                }
            }
        }
    }
}
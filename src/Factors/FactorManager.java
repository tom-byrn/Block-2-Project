package Factors;

import functions.Functions;
import menu.MenuManager;
import menu.Start;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FactorManager implements Start {

    public static Scanner scanner = new Scanner(System.in);

    public static void start() {

        boolean currentlySelecting = true;
        while (currentlySelecting) {
            try {

                // Prompt user to select a choice for functions
                System.out.println("""
                        Welcome to Functions 📈
                        
                        Enter (1) to find the factors of a number
                        Enter (2) to find prime numbers
                        
                        """);
                System.out.print("Enter a choice: ");

                // Scanner object scans for user input
                int selectorNum = scanner.nextInt();
                scanner.nextLine();
                System.out.println();

                // Switch case for choosing an option
                switch (selectorNum) {
                    case 1 -> {
                        currentlySelecting = false;
                        FactorManager.factorPrompt();
                        break;
                    }
                    case 2 -> {
                        currentlySelecting = false;
                        FactorManager.primePrompt();
                        break;
                    }

                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!\n");
                scanner.nextLine(); // Clear invalid input from the scanner
            }
        }
    }

    public static void primePrompt(){

        System.out.print("Please enter the nth prime number you would like to find: ");
        int n = scanner.nextInt();

        PrimeFinder primeFinder = new PrimeFinder(n);
        primeFinder.findNthPrime();
        System.out.println("The nth prime number is: " + primeFinder.getNthPrime());
    }


    public static void factorPrompt() {

        Factors f = new Factors();
        long n = 0;

        boolean validInput = false;
        while(!validInput) {
            try {
                System.out.print("Please enter a number: ");
                String num = scanner.nextLine();
                num = num.replaceAll("\\D", ""); // Remove non-digit characters
                n = Long.parseLong(num);
                f = new Factors(n);
                validInput = true;

            } catch (Exception e) {
                System.out.println("\nPlease enter a number between 1 and 9,223,372,036,854,775,807\n");
                scanner.nextLine();
            }
        }

        if(f.factorNum() == 0){
            System.out.println(n + " is a prime number!");
        }
        else{System.out.println(f.getFactors());}

        //Call the menu after user hits enter
        MenuManager.clearScreen();
        MenuManager.callMenu();

    }

}

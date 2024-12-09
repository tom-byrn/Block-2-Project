package Factors;

import com.sun.tools.javac.Main;
import menu.MenuManager;
import menu.Start;

import java.io.IOException;
import java.util.Scanner;

public class FactorManager implements Start {

    public static Scanner scanner = new Scanner(System.in);
    public static long n = 0;
    public static Factors f = new Factors();

    public static void start() {

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

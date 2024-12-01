package complex;

import functions.FunctionGraph;
import functions.Functions;
import menu.MenuManager;

import java.util.Scanner;

public class ComplexManager {

    public static void start() {
        Scanner scanner = new Scanner(System.in);

        boolean currentlySelecting = true;
        while (currentlySelecting) {

                // Prompt user to select a choice for functions
                System.out.println("""
                        Welcome to Functions 📈
                        
                        Enter (1) to add complex numbers
                        Enter (2) to multiply complex numbers
                        Enter (3) to graph complex numbers
                        Enter (0) to return to the menu
                        """);
                System.out.print("Enter a choice: ");

                // Scanner object scans for user input
                int selectorNum = scanner.nextInt();
                System.out.println();

                // Switch case for choosing an option
                switch (selectorNum) {
                    case 1 -> {
                        //Addition
                    }
                    case 2 -> {
                        //multiplication
                    }
                    case 3 -> {
                        //graphing
                    }
                    case 0 -> {
                        MenuManager.clearScreen();
                        MenuManager.callMenu();
                        break;
                    }
            }
        }
    }
}


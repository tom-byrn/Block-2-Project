package complex;

import functions.Functions;
import menu.MenuManager;
import menu.MenuText;
import menu.Start;

import java.util.Scanner;

import static menu.Colours.*;

public class ComplexManager implements Start {
        public static void start() {

            MenuManager.clearScreen();
            MenuText.complexText();
            Scanner scanner = new Scanner(System.in);

            System.out.print(CYAN + "Enter the real and imaginary parts of the first complex number:");
            double real1 = scanner.nextDouble();
            double imaginary1 = scanner.nextDouble();
            Complex num1 = new Complex(real1, imaginary1);

            System.out.print(CYAN + "Enter the real and imaginary parts of the second complex number:");
            double real2 = scanner.nextDouble();
            double imaginary2 = scanner.nextDouble();
            Complex num2 = new Complex(real2, imaginary2);

            System.out.println(CYAN + "╔════════════════════════════════════════════════════════════════╗" + RESET);
            System.out.println(CYAN + "║" + WHITE + "             Welcome to Complex Number Operations               " + CYAN + "║" + RESET);
            System.out.println(CYAN + "╠════════════════════════════════════════════════════════════════╣" + RESET);
            System.out.println(CYAN + "║                                                                ║");
            System.out.println(CYAN + "║" + BRIGHT_BLUE + "    Enter (1) to Add                                            " + CYAN + "║" + RESET);
            System.out.println(CYAN + "║" + BRIGHT_GREEN + "    Enter (2) to Subtract                                       " + CYAN + "║" + RESET);
            System.out.println(CYAN + "║" + BRIGHT_YELLOW + "    Enter (3) to Multiply                                       " + CYAN + "║" + RESET);
            System.out.println(CYAN + "║" + BRIGHT_CYAN + "    Enter (4) to Divide                                         " + CYAN + "║" + RESET);
            System.out.println(CYAN + "║" + BRIGHT_PURPLE + "    Enter (5) for Polar Form of First Complex Number            " + CYAN + "║" + RESET);
            System.out.println(CYAN + "║" + BRIGHT_BLUE + "    Enter (6) for Polar Form of Second Complex Number           " + CYAN + "║" + RESET);
            System.out.println(CYAN + "║                                                                ║");
            System.out.println(CYAN + "║" + BRIGHT_RED + "    Enter (0) to return to the menu                            " + CYAN + " ║" + RESET);
            System.out.println(CYAN + "║                                                                ║");
            System.out.println(CYAN + "╚════════════════════════════════════════════════════════════════╝" + RESET);
            System.out.print(CYAN + "Enter a choice: " + RESET);

            int choice = scanner.nextInt();

            Complex result;
            switch (choice) {
                case 1: // Addition
                    result = num1.add(num2);
                    System.out.println("Result: " + result);
                    break;
                case 2: // Subtraction
                    result = num1.subtract(num2);
                    System.out.println("Result: " + result);
                    break;
                case 3: // Multiplication
                    result = num1.multiply(num2);
                    System.out.println("Result: " + result);
                    break;
                case 4: // Division
                    try {
                        result = num1.divide(num2);
                        System.out.println("Result: " + result);
                    } catch (ArithmeticException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 5: // Polar form of the first complex number
                    System.out.println(num1.toPolarForm());
                    break;
                case 6: // Polar form of the second complex number
                    System.out.println(num2.toPolarForm());
                    break;
                case 0: //Return to the menu
                    MenuManager.clearScreen();
                    MenuManager.callMenu();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            scanner.nextLine();
            MenuManager.clearScreen();
            ComplexManager.start();
        }
    }


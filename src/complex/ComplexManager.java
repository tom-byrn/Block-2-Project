package complex;

import functions.FunctionGraph;
import functions.Functions;
import menu.MenuManager;

import java.util.Scanner;

public class ComplexManager {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter the real and imaginary parts of the first complex number:");
            double real1 = scanner.nextDouble();
            double imaginary1 = scanner.nextDouble();
            Complex num1 = new Complex(real1, imaginary1);

            System.out.println("Enter the real and imaginary parts of the second complex number:");
            double real2 = scanner.nextDouble();
            double imaginary2 = scanner.nextDouble();
            Complex num2 = new Complex(real2, imaginary2);

            System.out.println("Choose an operation: ");
            System.out.println("1. Add");
            System.out.println("2. Subtract");
            System.out.println("3. Multiply");
            System.out.println("4. Divide");
            System.out.println("5. Polar Form of First Complex Number");
            System.out.println("6. Polar Form of Second Complex Number");
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
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            scanner.close();
        }
    }


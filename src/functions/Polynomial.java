package functions;

import algebra.AlgebraManager;
import calculations.CalculationsManager;
import matrices.MatricesManager;
import menu.MenuManager;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Polynomial {

    protected static void promptDegree() {
        Scanner scanner = new Scanner(System.in);
        boolean currentlySelecting = true;
        while (currentlySelecting) {
            System.out.println("""
                    Enter (1) to solve a first degree polynomial
                    Enter (2) to solve a second degree polynomial
                    Enter (3) to solve a third degree polynomial
                    Enter (4) to solve a fourth degree polynomial
                    
                    """);
            System.out.print("Enter a choice: ");


            int selectorNum = scanner.nextInt();
            System.out.println();

            // Switch case for choosing a calculator function to use
            switch (selectorNum) {
                case 1 -> {
                    try {
                        System.out.print("For ax + b = 0, please enter a values for a and b separated by spaces (e.g. 10 5):  ");
                        double a = scanner.nextDouble();
                        double b = scanner.nextDouble();
                        firstDegreePolynomial(a, b);
                        currentlySelecting = false;
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter valid double values!");
                        scanner.nextLine();
                    }
                    break;
                }
                case 2 -> {
                    try {
                        System.out.print("For ax^2 + bx + c = 0, please enter a values for a, b and c separated by spaces (e.g. 2 10 5):  ");
                        double a = scanner.nextDouble();
                        double b = scanner.nextDouble();
                        double c = scanner.nextDouble();
                        secondDegreePolynomial(a, b, c);
                        currentlySelecting = false;
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter valid double values!");
                        scanner.nextLine();
                    }
                    break;
                }
                case 3 -> {
                    try {
                        System.out.print("For ax^3 + bx^2 + cx + d = 0, please enter a values for a, b, c and d separated by spaces (e.g. 1 3 -10 5):  ");
                        double a = scanner.nextDouble();
                        double b = scanner.nextDouble();
                        double c = scanner.nextDouble();
                        double d = scanner.nextDouble();
                        thirdDegreePolynomial(a, b, c, d);
                        currentlySelecting = false;
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter valid double values!");
                        scanner.nextLine();
                    }
                    break;
                }
                case 4 -> {
                    try {
                        System.out.print("For ax^4 + bx^3 +cx^2 + dx + e = 0, please enter a values for a, b, c, d and e separated by spaces (e.g. -2 4 0 10 5):  ");
                        double a = scanner.nextDouble();
                        double b = scanner.nextDouble();
                        double c = scanner.nextDouble();
                        double d = scanner.nextDouble();
                        double e = scanner.nextDouble();
                        thirdDegreePolynomial(a, b, c, d);
                        currentlySelecting = false;
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter valid double values!");
                        scanner.nextLine();
                    }
                    break;
                }
            }
        }
    }

    protected static void firstDegreePolynomial(double a, double b){
        double root = - b / a;
        System.out.printf("%nRoot: %f", root);
        MenuManager.clearScreen();
        MenuManager.callMenu();
    }

    protected static void secondDegreePolynomial(double a, double b, double c){
        double discriminant = (b*b - 4*a*c);
        if(discriminant < 0){
            boolean complex = true;
            discriminant = -discriminant;
            String complexRoot = String.format("%f ± %fi", -b, Math.sqrt(discriminant));
            System.out.printf("%nRoots: %s", complexRoot);
        } else {
            boolean complex = false;
            double root1 = ((-b + Math.sqrt(discriminant)) / 2*a);
            double root2 = ((-b - Math.sqrt(discriminant)) / 2*a);
            System.out.printf("%nRoots: %f, %f", root1, root2);
        }
        MenuManager.clearScreen();
        MenuManager.callMenu();
    }

    protected static void thirdDegreePolynomial(double a, double b, double c, double d){

    }

    protected static void fourthDegreePolynomial(double a, double b, double c, double d, double e){

    }

}

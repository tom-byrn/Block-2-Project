package functions;

import algebra.AlgebraManager;
import calculations.CalculationsManager;
import matrices.MatricesManager;
import menu.MenuManager;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import static menu.Colours.*;

public class Polynomial {

    protected static void promptDegree() {
        Scanner scanner = new Scanner(System.in);
        boolean currentlySelecting = true;
        while (currentlySelecting) {
            System.out.println(CYAN + "╔════════════════════════════════════════════════════════════════╗" + RESET);
            System.out.println(CYAN + "║" + WHITE + "                  Polynomial Solver Menu                        " + CYAN + "║" + RESET);
            System.out.println(CYAN + "╠════════════════════════════════════════════════════════════════╣" + RESET);
            System.out.println(CYAN + "║                                                                ║");
            System.out.println(CYAN + "║" + BRIGHT_BLUE + "    Enter (1) to solve a first degree polynomial               " + CYAN + " ║" + RESET);
            System.out.println(CYAN + "║" + BRIGHT_GREEN + "    Enter (2) to solve a second degree polynomial              " + CYAN + " ║" + RESET);
            System.out.println(CYAN + "║" + BRIGHT_YELLOW + "    Enter (3) to solve a third degree polynomial               " + CYAN + " ║" + RESET);
            //System.out.println(CYAN + "║" + BRIGHT_CYAN + "    Enter (4) to solve a fourth degree polynomial              " + CYAN + " ║" + RESET);
            System.out.println(CYAN + "║                                                                ║");
            System.out.println(CYAN + "╚════════════════════════════════════════════════════════════════╝" + RESET);
            System.out.print(CYAN + "Enter a choice: " + RESET);



            int selectorNum = scanner.nextInt();
            System.out.println();

            // Switch case for choosing a calculator function to use
            switch (selectorNum) {
                case 1 -> {
                    try {
                        System.out.print("For ax + b = 0, please enter a values for a and b separated by spaces (e.g. 10 5):  ");
                        double a = scanner.nextDouble();
                        double b = scanner.nextDouble();
                        System.out.printf("%nRoot: %f", firstDegreePolynomial(a, b));
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
                        if(a == 0){firstDegreePolynomial(b, c);}
                        else{secondDegreePolynomial(a, b, c);}
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
                        if(a == 0){secondDegreePolynomial(b, c, d);}
                        else{thirdDegreePolynomial(a, b, c, d);}
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

    //Basic formula to find a single root
    protected static double firstDegreePolynomial(double a, double b){
        double root = - b / a;
        System.out.printf("%nRoot: %f", root);
        return root;
    }

    //The minus b formula to find roots of a quadratic
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

    //Cardano's method for finding the roots of a cubic equation https://brilliant.org/wiki/cardano-method/
    protected static void thirdDegreePolynomial(double a, double b, double c, double d) {
        // Normalize the polynomial
        if (a != 1) {
            b /= a;
            c /= a;
            d /= a;
            a = 1;
        }

        // Compute p and q
        double p = (3 * a * c - b * b) / (3 * a * a);
        double q = (2 * b * b * b - 9 * a * b * c + 27 * a * a * d) / (27 * a * a * a);

        // Compute the discriminant
        double discriminant = Math.pow(q / 2, 2) + Math.pow(p / 3, 3);

        // Shift for depressed cubic
        double shift = -b / (3 * a);

        if (discriminant > 0) {
            // One real root, two complex conjugate roots
            double u = Math.cbrt(-q / 2 + Math.sqrt(discriminant));
            double v = Math.cbrt(-q / 2 - Math.sqrt(discriminant));

            double realRoot = u + v + shift;
            System.out.printf("%nOne Real Root: %f%n", realRoot);
        } else if (Math.abs(discriminant) < 1e-6) {
            // All roots real, at least two are equal
            double u = Math.cbrt(-q / 2);
            double doubleRoot = 2 * u + shift;
            double singleRoot = -u + shift;
            System.out.printf("%nDouble Root: %f%nSingle Root: %f%n", doubleRoot, singleRoot);
        } else {
            // All roots real and distinct
            double r = Math.sqrt(-Math.pow(p / 3, 3));
            double phi = Math.acos(-q / (2 * r));
            double root1 = 2 * Math.cbrt(r) * Math.cos(phi / 3) + shift;
            double root2 = 2 * Math.cbrt(r) * Math.cos((phi + 2 * Math.PI) / 3) + shift;
            double root3 = 2 * Math.cbrt(r) * Math.cos((phi + 4 * Math.PI) / 3) + shift;

            System.out.printf("%nRoots: %f, %f, %f%n", root1, root2, root3);
        }

        MenuManager.clearScreen();
        MenuManager.callMenu();
    }

    //Ferrari's method for solving fourth degree polynomials https://encyclopediaofmath.org/wiki/Ferrari_method
    protected static void fourthDegreePolynomial(double a, double b, double c, double d, double e) {

    }





}

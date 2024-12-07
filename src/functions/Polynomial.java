package functions;

import algebra.AlgebraManager;
import calculations.CalculationsManager;
import matrices.MatricesManager;

public class Polynomial extends Functions{

    public Polynomial(String function){}

    public Polynomial(){}

    protected static void promptDegree() {
        boolean currentlySelecting = true;
        while (currentlySelecting) {
            System.out.println("""
                    Enter (1) to solve a first degree polynomial
                    Enter (2) to solve a second degree polynomial
                    Enter (3) to solve a third degree polynomial
                    Enter (4) to solve a fourth degree polynomial
                    """);
        }

        int selectorNum = scanner.nextInt();
        System.out.println();

        // Switch case for choosing a calculator function to use
        switch (selectorNum) {
            case 1 -> {
                currentlySelecting = false;

                break;
            }
            case 2 -> {
                currentlySelecting = false;

                break;
            }
            case 3 -> {
                currentlySelecting = false;

                break;
            }
            case 4 -> {
                currentlySelecting = false;

                break;
            }
        }
    }

    protected void firstDegreePolynomial(double a, double b){

    }

    protected void secondDegreePolynomial(double a, double b, double c){

    }

    protected void thirdDegreePolynomial(double a, double b, double c, double d){

    }

    protected void fourthDegreePolynomial(double a, double b, double c, double d, double e){

    }

}

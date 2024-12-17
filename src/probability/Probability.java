package probability;

import java.util.Scanner;
import calculations.Calculations;

public class Probability {
    public static void main(String[] args) {
        probabilityValidator("Enter P(A): ");
    }

    // validates probability is a number between 0 and 1
    public static double probabilityValidator(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        String input = scanner.nextLine();
        Calculations calculations = new Calculations(input);
        double probability = calculations.getAnswer();
        System.out.println(probability);
        return probability;


//        double probability;
//        System.out.print(message);
//        while (true) {
//            if (scanner.hasNextDouble()) {
//                probability = scanner.nextDouble();
//                scanner.nextLine(); // Clear buffer
//                if (probability >= 0 && probability <= 1) {
//                    System.out.println("\n");
//                    return probability; // Valid probability
//                } else {
//                    System.out.print("Invalid choice. Please enter a valid probability: ");
//                }
//            } else {
//                System.out.print("Invalid choice. Please enter a valid probability: ");
//                scanner.nextLine(); // Clear buffer of invalid input
//            }
//        }
    }

    public static void bayesTheorem(){
        double pA = probabilityValidator("Enter P(A): ");
        double pNotA = 1 - pA;
        double pBGivenA = probabilityValidator("Enter P(B|A): ");
        double pBGivenNotA = probabilityValidator("Enter P(B|not A): ");

        // (P(B|A)*P(A)) / (P(B|A)*P(A)+P(B|not A)P(not A))
        double result = (pBGivenA*pA) / (pBGivenA*pA + pBGivenNotA*pNotA);
        System.out.printf("P(A|B)= %.4f", result);
    }

    public static void independenceChecker(){
        double pA = probabilityValidator("Enter P(A): ");
        double pB = probabilityValidator("Enter P(B): ");
        double pAIntersectionB = probabilityValidator("Enter P(A intersection B): ");

        if (pA*pB == pAIntersectionB) {
            System.out.println("These events are independent.");
        }
        else {
            System.out.printf("These events are not independent as P(A)xP(B) = %.4f which does not equal P(A intersection B).\n", pA*pB);
        }
    }

    public static void aGivenB() {
        double aIntersectionB = probabilityValidator("Enter P(A intersection B): ");
        double pB = probabilityValidator("Enter P(B): ");

        double result = aIntersectionB / pB;
        System.out.printf("P(A|B)= %.4f", result);
    }
}


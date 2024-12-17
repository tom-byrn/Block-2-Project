package matrices;

import menu.MenuManager;

import java.util.Scanner;

public interface PrintMatrixFinal extends PrintMatrixSuper {
    public static void printFinal(double[][] matrixToPrint){
        PrintMatrixSuper.printMatrix(matrixToPrint);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        MenuManager.clearScreen();
        MatricesManager.start();
    }
}

import menu.MenuManager;

import java.util.Scanner;


public class Main {

    public static void main(String[] args){
        MenuManager.clearScreen();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        MenuManager.callMenu();
    }

}
package matrices;

import java.util.InputMismatchException;
import java.util.Scanner;

//This class is used only to prevent errors
public class NumberChecker {

    // Ensures an int of size byte is inputted
    protected static byte byteSizeInt() {
        Scanner input = new Scanner(System.in);
        try {
            return input.nextByte();
        } catch (InputMismatchException e) {
            // This block catches InputMismatchException
            // if patterns don't match e.g. a letter or a number too big
            return 0;
        }
    }

    // Ensures an int of size byte is inputted
    protected static int intSizeInt() {
        Scanner input = new Scanner(System.in);
        try {
            return input.nextInt();
        } catch (InputMismatchException e) {
            // This block catches InputMismatchException
            // if patterns don't match e.g. a letter or a number too big
            return 0;
        }
    }
}

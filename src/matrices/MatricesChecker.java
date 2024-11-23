package matrices;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

//This class is used only to prevent errors
public class MatricesChecker {

    //Used to ensure Matrix entered is correct
    protected static char YorN(double[][]matrixToBeChecked) {
        //Print the out the matrix
        for (double[] row : matrixToBeChecked) {
            System.out.println(Arrays.toString(row));
        }
        System.out.print("Is matrix correct Y/N: ");

        Scanner input = new Scanner(System.in);

        return input.next().charAt(0);
    }

    //Used to prevent errors e.g. someone entering 2 numbers in a row in a 3*3 matrix
    protected static double getNumberRowCounterInMatrix(String rowListWithSpace, int positionCounter) {
        double numberRowCounterInMatrix;
        // Ensures there is a number in each coloum or enters zero
        try{
            numberRowCounterInMatrix = Double.parseDouble(rowListWithSpace.split(" ")[positionCounter]);
            //NumberFormatException is an exception that occours when trying to convert a string to a number when string doesn't have an appropriate format
            //Prevents ArrayIndexOutOfBoundsException when trying to add something out of bounds to an array
        }catch(NumberFormatException | ArrayIndexOutOfBoundsException e){
            numberRowCounterInMatrix = 0;
        }
        return numberRowCounterInMatrix;
    }

    // Ensures an int of size byte is inputted
    public static byte byteSizeInt() {
        Scanner input = new Scanner(System.in);
        try {
            return input.nextByte();
        } catch (InputMismatchException e) {
            // This block catches InputMismatchException
            // if patterns don't match e.g. a letter or a number too big
            return 0;
        }
    }
}

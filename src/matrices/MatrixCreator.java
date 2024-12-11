package matrices;

import java.util.Scanner;


public abstract class MatrixCreator implements CreatingMatrixInterface {

    protected static char checker;
    private double[][] matrixOne;
    
    @Override
    // input the size of the matrix (no of rows and coloms)
    public abstract double[][] createMatrixAndIncludeSize();

    @Override
    //Ensures the Matrix is correct
    public abstract void matrixChecker(double[][] matrix);

    @Override
    //Used to prevent errors e.g. someone entering 2 numbers in a row in a 3*3 matrix
    public double getNumberRowCounterInMatrix(String rowListWithSpace, int positionCounter) {
        double numberRowCounterInMatrix;
        // Ensures there is a number in each coloum or enters zero
        try{
            numberRowCounterInMatrix = Double.parseDouble(rowListWithSpace.split(" ")[positionCounter]);
            //NumberFormatException is an exception that occours when trying to convert a string to a number when string doesn't have an appropriate format
            //Prevents ArrayIndexOutOfBoundsException when trying to add something out of bounds to an array
        }catch(NumberFormatException | ArrayIndexOutOfBoundsException e){
            //If no number detected it inputs 0
            numberRowCounterInMatrix = 0;
        }
        return numberRowCounterInMatrix;
    }

    @Override
    //Used to ensure Matrix entered is correct
    public char YorN(double[][]matrixToBeChecked){
        MatricesManager.printMatrix(matrixToBeChecked);

        System.out.print("Is matrix correct Y/N: ");

        Scanner input = new Scanner(System.in);

        return input.next().charAt(0);
    }


    @Override
    // used to create a matrix
    public double[][] createMatrixNoSize(int noOfRowsInMatrixA, int noOfColumnsMatrixA) {

        //Create a 2D array or Matrix
        matrixOne = new double[noOfRowsInMatrixA][noOfColumnsMatrixA];

        // using scanner to find the numbers
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first row:\t");

        // iterate through the rows
        for(int rowCounter = 0; rowCounter< noOfRowsInMatrixA; rowCounter++){
            int positionCounter;

            // Input each row
            // uses regex and trim to remove tabs and multiple spaces
            String rowListWithSpace = input.nextLine().replaceAll("\\s+", " ").trim();

            // iterate through the columns in each row and adding them to the matrix
            for(positionCounter = 0; positionCounter< noOfColumnsMatrixA; positionCounter++){

                double numberRowCounterInMatrix = getNumberRowCounterInMatrix(rowListWithSpace, positionCounter);
                matrixOne[rowCounter][positionCounter] = numberRowCounterInMatrix;
            }

            // for printing out next row before you enter each row
            if(noOfRowsInMatrixA != (rowCounter+1)){
                System.out.print("Enter next row:\t\t");
            }
        }
        setMatrixOne(matrixOne);
        return getMatrixOne();
    }


    // getters and setters for the matrix
    protected double[][] getMatrixOne() {
        return matrixOne;
    }

    protected void setMatrixOne(double[][] matrixOne) {
        this.matrixOne = matrixOne;
    }
}

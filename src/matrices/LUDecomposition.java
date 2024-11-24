package matrices;

public class LUDecomposition {

    // Function to compute the determinant using LU decomposition
    public static double computeDeterminant(double[][] inputMatrix) {
        int matrixSize = inputMatrix.length;  // Get the size of the matrix (n x n)

        // Create the lower and upper triangular matrices to store the LU decomposition result
        double[][] lowerMatrix = new double[matrixSize][matrixSize];
        double[][] upperMatrix = new double[matrixSize][matrixSize];

        // Perform LU factorisation
        if (LUFactorisation.luFactorisation(inputMatrix, lowerMatrix, upperMatrix)) {

            // The total determinant is the product of the diagonal elements of the upper matrix
            double determinant = 1.0;

            // Cycle through the diagonal elements of the matrix
            for (int diagonalIndex = 0; diagonalIndex < matrixSize; diagonalIndex++) {

                determinant *= upperMatrix[diagonalIndex][diagonalIndex];  // Multiply diagonal elements of the upper triangular matrix
            }
            return determinant;  // Return the computed determinant
        }

        // If LU decomposition fails, return 0 (determinant of singular matrix)
        return 0.0;
    }


    public static void main(String[] args) {
        // Test matrix (3x3 example matrix)
        double[][] inputMatrix = Matrices.squareMatrixCreator();

        // Compute and print the determinant using LU decomposition
        double determinant = computeDeterminant(inputMatrix);
        System.out.println("Determinant of the matrix: " + determinant);
    }
}
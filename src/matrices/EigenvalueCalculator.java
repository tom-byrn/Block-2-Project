package matrices;

import static matrices.MatrixMultiplication.multiplication;

public class EigenvalueCalculator {
    private static void powerIteration(double[][] origialMatrix, int noOfIterations) {

        short noOfRowsinVector = (short) origialMatrix.length; // Size of the matrix (assuming a square matrix)

        // Initial vector (randomly chosen as all ones) (Can't be 0)
        double[][] eigenVectorMatrix = new double[noOfRowsinVector][1];   // Vector to hold the result of matrix-vector multiplication

        // Fill the first column of each row with 1
        for (short defaultEveryElementTo1 = 0; defaultEveryElementTo1 < noOfRowsinVector; defaultEveryElementTo1++) {
            eigenVectorMatrix[defaultEveryElementTo1][0] = 1;  //fills every element with 1
        }

        // Power iteration loop
        for (int iteration = 1; iteration <= noOfIterations; iteration++) {

            // Step 1: Compute the norm of the resulting vector (for normalization)
            double sumOfSquaresToFindNorm = 0;
            for (int matrixRow = 0; matrixRow < noOfRowsinVector; matrixRow++) {
                sumOfSquaresToFindNorm += eigenVectorMatrix[matrixRow][0] * eigenVectorMatrix[matrixRow][0]; // Sum of squares
            }
            double norm = Math.sqrt(sumOfSquaresToFindNorm); // Take square root to get the norm

            // Step 2: Normalize xn (make it a unit vector)(vn)
            for (int matrixRow = 0; matrixRow < noOfRowsinVector; matrixRow++) {
                eigenVectorMatrix[matrixRow][0] /= norm;
            }

            // Step 3: Multiply origial Matrix by the eigenvector to get x(n+1)
            eigenVectorMatrix = multiplication(origialMatrix, eigenVectorMatrix);
        }

        // Step 4: Compute the eigenvalue using the formula v^T * A * v
        // 1. Find v^T
        // 2. v^T * A
        // 3. multiply by v ( * v)
        double[][] currentEigenvalueArray = multiplication(multiplication(TransposeOfAMatrix.transpose(eigenVectorMatrix), origialMatrix), eigenVectorMatrix);
        double currentEigenvalue = currentEigenvalueArray[0][0];

        // Print statements
        System.out.println("The Dominant Eigenvalue is: " + currentEigenvalue + "\n\nAnd it's corresponding Eigenvectors are: ");
        MatricesManager.printMatrix(eigenVectorMatrix);
    }

    // Main function to run the program
    protected static void getDominantEigenvalue(){

        // Define a sample matrix A (e.g., 3x3 matrix)
        double[][] matrix = MatricesManager.squareMatrixCreator();

        // Parameters for power iteration: max iterations
        int maxIterations = 100;

        // Call the power iteration method to find the largest eigenvalue and eigenvector
        EigenvalueCalculator.powerIteration(matrix, maxIterations);
    }
}

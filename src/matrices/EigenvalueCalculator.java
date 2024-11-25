/*package matrices;

import java.util.Arrays;

import static matrices.MatrixMultiplication.multiplication;

public class EigenvalueCalculator {
    public static void powerIteration(double[][] eigenValueMatrix, double tolerance) {
        short noOfColomns = (short) eigenValueMatrix.length; // Size of the matrix (assuming a square matrix)

        // Initial vector (randomly chosen as all ones)
        double[] eigenvectorBeforeNormilized = new double[noOfColomns];
        Arrays.fill(eigenvectorBeforeNormilized, 1);     //fills every element with 1

        // Vector to hold the result of matrix-vector multiplication
        double[] eigenVectorMatrix = new double[noOfColomns];

        // Variable to store the previous eigenvalue approximation
        double previousEigenvalue = 0.0;

        // Power iteration loop
        for (int iteration = 0; iteration <= 20; iteration++) {

            // Step 1: Compute the norm of the resulting vector (for normalization)
            double sumOfSquaresToFindNorm = 0;
            for (int matrixRow = 0; matrixRow < noOfColomns; matrixRow++) {
                sumOfSquaresToFindNorm += eigenVectorMatrix[matrixRow] * eigenVectorMatrix[matrixRow]; // Sum of squares
            }
            double norm = Math.sqrt(sumOfSquaresToFindNorm); // Take square root to get the norm

            // Step 2: Normalize xn (make it a unit vector)(vn)
            for (int matrixRow = 0; matrixRow < noOfColomns; matrixRow++) {
                eigenVectorMatrix[matrixRow] /= norm;
            }

            // Step 3: Multiply matrix A by the eigenvector to get x(n+1)
            eigenvectorBeforeNormilized = multiplication(eigenValueMatrix, eigenVectorMatrix);


            // Step 4: Compute the Rayleigh quotient to estimate the eigenvalue
            double currentEigenvalue = computeRayleighQuotient(matrix, eigenVectorMatrix);

            // Step 5: Check for convergence (if the change in eigenvalue is small)
            if (Math.abs(currentEigenvalue - previousEigenvalue) < tolerance) {
                System.out.println("Eigenvalue converged: " + currentEigenvalue);
                System.out.println("Eigenvector: " + Arrays.toString(eigenVectorMatrix));
                return; // Converged, exit the loop
            }

            // Update the eigenvalue for the next iteration
            previousEigenvalue = currentEigenvalue;

            // Step 6: Update the eigenvector for the next iteration
            System.arraycopy(eigenVectorMatrix, 0, eigenvectorBeforeNormilized, 0, noOfColomns); // Copy new vector to eigenvector
        }




    }
}


class PowerIteration {

    public static void powerIteration(double[][] matrix, int maxIterations, double tolerance) {
        int size = matrix.length; // Size of the matrix (assuming a square matrix)

        // Initial vector (randomly chosen as all ones)
        double[] eigenvector = new double[size];
        Arrays.fill(eigenvector, 1);

        // Vector to hold the result of matrix-vector multiplication
        double[] matrixProduct = new double[size];

        // Variable to store the previous eigenvalue approximation
        double previousEigenvalue = 0.0;

        // Power iteration loop
        for (int iteration = 0; iteration < maxIterations; iteration++) {
            // Step 1: Multiply matrix A by the eigenvector to get a new vector
            multiplyMatrixByVector(matrix, eigenvector, matrixProduct);

            // Step 2: Compute the norm of the resulting vector (for normalization)
            double norm = 0;
            for (int matrixRow = 0; matrixRow < size; matrixRow++) {
                norm += matrixProduct[matrixRow] * matrixProduct[matrixRow]; // Sum of squares
            }
            norm = Math.sqrt(norm); // Take square root to get the norm

            // Step 3: Normalize the resulting vector (make it a unit vector)
            for (int matrixRow = 0; matrixRow < size; matrixRow++) {
                matrixProduct[matrixRow] /= norm;
            }

            // Step 4: Compute the Rayleigh quotient to estimate the eigenvalue
            double currentEigenvalue = computeRayleighQuotient(matrix, matrixProduct);

            // Step 5: Check for convergence (if the change in eigenvalue is small)
            if (Math.abs(currentEigenvalue - previousEigenvalue) < tolerance) {
                System.out.println("Eigenvalue converged: " + currentEigenvalue);
                System.out.println("Eigenvector: " + Arrays.toString(matrixProduct));
                return; // Converged, exit the loop
            }

            // Update the eigenvalue for the next iteration
            previousEigenvalue = currentEigenvalue;

            // Step 6: Update the eigenvector for the next iteration
            System.arraycopy(matrixProduct, 0, eigenvector, 0, size); // Copy new vector to eigenvector
        }

        // If maximum iterations are reached without convergence, output the result
        System.out.println("Max iterations reached. Approximate eigenvalue: " + previousEigenvalue);
    }

    public static void multiplyMatrixByVector(double[][] matrix, double[] vector, double[] result) {
        int size = matrix.length;
        Arrays.fill(result, 0); // Initialize the result array with zeros

        // Perform matrix-vector multiplication
        for (int matrixRow = 0; matrixRow < size; matrixRow++) {
            for (int matrixColumn = 0; matrixColumn < size; matrixColumn++) {
                result[matrixRow] += matrix[matrixRow][matrixColumn] * vector[matrixColumn]; // Sum of the products of matrix and vector elements
            }
        }
    }

    public static double computeRayleighQuotient(double[][] matrix, double[] eigenvector) {
        double numerator = 0.0; // Numerator for Rayleigh quotient (v^T * A * v)
        double denominator = 0.0; // Denominator for Rayleigh quotient (v^T * v)
        int size = matrix.length;

        // Calculate the numerator and denominator for the Rayleigh quotient
        for (int matrixRow = 0; matrixRow < size; matrixRow++) {
            for (int matrixColumn = 0; matrixColumn < size; matrixColumn++) {
                numerator += eigenvector[matrixRow] * matrix[matrixRow][matrixColumn] * eigenvector[matrixColumn];
            }
            denominator += eigenvector[matrixRow] * eigenvector[matrixRow]; // v^T * v (dot product of eigenvector with itself)
        }

        // Return the Rayleigh quotient, which is the estimated eigenvalue
        return numerator / denominator;
    }

    public static void main(String[] args) {
        // Define a sample matrix A (e.g., 3x3 matrix)
        double[][] matrix = {
                {4, 1, 2},
                {1, 3, 0},
                {2, 0, 1}
        };

        // Parameters for power iteration: max iterations and convergence tolerance
        int maxIterations = 1000;
        double tolerance = 1e-6;

        // Call the power iteration method to find the largest eigenvalue and eigenvector
        powerIteration(matrix, maxIterations, tolerance);
    }
}*/

package matrices;

import java.util.Arrays;
import java.util.Objects;

public class MatricesManager {

    public static void start() {

        //Print the out the matrix
        for (double[] row : Objects.requireNonNull(Matrices.promptForMatrices())) {
            System.out.println(Arrays.toString(row));
        }
    }
}

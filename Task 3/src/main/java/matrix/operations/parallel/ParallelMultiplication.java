package matrix.operations.parallel;

import matrix.matrices.Matrix;

public class ParallelMultiplication {

    public static void multiplyRow(Matrix a, Matrix b, Matrix c, int n, int i){
        for (int j = 0; j < n; j++) {
            double aux = 0;
            for (int k = 0; k < n; k++) {
                aux += a.getValue(i,k) * b.getValue(k,j);
            }
            c.setValue(i,j,aux);
        }
    }
}

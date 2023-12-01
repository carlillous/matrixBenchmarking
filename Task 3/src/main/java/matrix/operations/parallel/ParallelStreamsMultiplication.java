package matrix.operations.parallel;

import matrix.matrices.DoubleMatrix;
import matrix.matrices.Matrix;

import java.util.stream.IntStream;

public class ParallelStreamsMultiplication {

    public static Matrix execute(Matrix a, Matrix b ){

        Matrix c = new DoubleMatrix();
        c.startMatrix(a.getRowsLen(),b.getColsLen());
        int n = a.getRowsLen();

        IntStream.range(0,n).parallel().forEach(i->{
            for (int ii = 0; ii < n; ii++) {
                for (int j = 0; j < n; j++) {
                    double aux = 0;
                    for (int k = 0; k < n; k++) {
                        aux += a.getValue(ii,k) * b.getValue(k,j);
                    }
                    c.setValue(ii,j,aux);
                }
            }
        });

        return c;


    }

}

package matrix.operations.parallel;

import matrix.matrices.DoubleMatrix;
import matrix.matrices.Matrix;

import java.util.stream.IntStream;

public class StreamsMultiplication {

    public static Matrix execute(Matrix a, Matrix b) {
        int n = a.getRowsLen();
        Matrix c = new DoubleMatrix();
        c.startMatrix(a.getRowsLen(), b.getColsLen());

        IntStream.range(0, n).parallel().forEach(i ->
                IntStream.range(0, n).parallel().forEach(j -> {
                    double aux = IntStream.range(0, n)
                            .mapToDouble(k -> a.getValue(i, k) * b.getValue(k, j))
                            .sum();
                    c.setValue(i, j, aux);
                })
        );

        return c;
    }
}

package matrix.operations.parallel;

import matrix.matrices.DoubleMatrix;
import matrix.matrices.Matrix;
import matrix.operations.sequential.ConventionalMultiplication;

import javax.swing.plaf.multi.MultiOptionPaneUI;
import java.util.stream.IntStream;

public class ParallelStreamsMultiplication {

    public static Matrix execute(Matrix a, Matrix b ){

        Matrix c = new DoubleMatrix();
        c.startMatrix(a.getRowsLen(),b.getColsLen());
        int n = a.getRowsLen();

        IntStream.range(0,n).parallel().forEach(i->{
            ParallelMultiplication.multiplyRow(a,b,c,n,i);
        });

        return c;


    }

}

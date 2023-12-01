package matrix.operations;

import matrix.compresor.CompressedColumnStorage;
import matrix.compresor.CompressedRowStorage;
import matrix.matrices.DoubleMatrix;
import matrix.matrices.Matrix;
import matrix.operations.parallel.ParallelStreamsMultiplication;
import matrix.operations.parallel.ParallelThreadsMultiplication;
import matrix.operations.sequential.ConventionalMultiplication;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.Random;

public class Testing {

    public static void main(String[] args) {
        int n = 5;
        Matrix a = new DoubleMatrix();
        Matrix b = new DoubleMatrix();
        a.startMatrix(n,n); b.startMatrix(n,n);
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (random.nextBoolean()) {
                    a.setValue(i,j,random.nextDouble() * 100);
                    b.setValue(i,j,random.nextDouble() * 100);
                } else {
                    a.setValue(i,j,0);
                    b.setValue(i,j,0);
                }
            }
        }

        Matrix c1 = ConventionalMultiplication.execute(a,b);
        //Matrix c2 = ParallelStreamsMultiplication.execute(a,b);
        Matrix c3 = ParallelThreadsMultiplication.execute(a,b);
        c1.printMatrix();
        //c2.printMatrix();
        c3.printMatrix();


    }

}

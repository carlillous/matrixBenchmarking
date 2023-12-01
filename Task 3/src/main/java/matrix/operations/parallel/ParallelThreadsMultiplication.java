package matrix.operations.parallel;

import matrix.matrices.DoubleMatrix;
import matrix.matrices.Matrix;
import matrix.operations.sequential.ConventionalMultiplication;


public class ParallelThreadsMultiplication {

    public static Matrix execute(Matrix a, Matrix b) {

        Matrix c = new DoubleMatrix();
        c.startMatrix(a.getRowsLen(),b.getColsLen());
        int n = a.getRowsLen();

        Thread[] threads = new Thread[n];

        for (int i = 0;i<n;i++) {
            final int row = i;
            threads[i] = new Thread(() -> {
                        ParallelMultiplication.multiplyRow(a,b,c,n,row);
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return c;
    }
}

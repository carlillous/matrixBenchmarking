package matrix.operations.parallel;

import matrix.matrices.DoubleMatrix;
import matrix.matrices.Matrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParalllelExecutorsMultiplication {

    public static Matrix execute(Matrix a, Matrix b ){

        ExecutorService executorService = Executors.newFixedThreadPool(16);
        int size = a.getRowsLen();
        Matrix c = new DoubleMatrix();
        c.startMatrix(a.getRowsLen(),b.getColsLen());

        submit(a, b, size, executorService, c);

        try {
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }
        return c;
    }

    private static void submit(Matrix a, Matrix b, int size, ExecutorService executorService, Matrix c) {
        for(int i = 0; i< size; i++){
            final int row = i;
            executorService.submit(()->{
                ParallelMultiplication.multiplyRow(a, b, c, size,row);
            });
        }
    }
}

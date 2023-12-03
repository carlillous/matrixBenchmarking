package matrix.operations;

import matrix.matrices.DoubleMatrix;
import matrix.matrices.Matrix;
import matrix.operations.parallel.*;
import matrix.operations.parallel.tiled.TiledThreadsMultiplication;
import matrix.operations.sequential.ConventionalMultiplication;
import java.util.Random;

public class Testing {

    public static void main(String[] args) {
        int n = 32;
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
        //Matrix c3 = ParallelThreadsMultiplication.execute(a,b);
        //Matrix c4 = ParalllelExecutorsMultiplication.execute(a,b);
        Matrix c5 = TiledThreadsMultiplication.execute(a,b);
        Matrix c7 = StreamsMultiplication.execute(a,b);
        c1.printMatrix();
        System.out.println("..................");
        //c2.printMatrix();
        System.out.println("..................");
        //c3.printMatrix();
        System.out.println("..................");
        //c4.printMatrix();
        System.out.println("..................");
        //c5.printMatrix();
        System.out.println("..................");
        c7.printMatrix();

    }

}

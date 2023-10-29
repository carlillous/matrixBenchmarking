package matrix.operations;
import matrix.compresor.CompressedColumnStorage;
import matrix.compresor.CompressedRowStorage;
import matrix.matrices.DoubleMatrix;
import matrix.matrices.Matrix;
import org.openjdk.jmh.annotations.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class CompressedMultiplicationBenchmarking {

    @State(Scope.Thread)
    public static class Operands{
        private final int n = 4096;
        private Matrix a = new DoubleMatrix();
        private Matrix b = new DoubleMatrix();
        private CompressedRowStorage a2;
        private CompressedColumnStorage b2;

        @Setup
        public void setup() {
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

            a2 = new CompressedRowStorage(a);
            b2 = new CompressedColumnStorage(b);
        }

    }

    @Benchmark
    @Fork(value=1)
    @Measurement(iterations = 1)
    @Warmup(iterations = 1)
    public void compressedMultiplication(Operands operands){
        new Multiplication().compressedMultiplication(operands.a2, operands.b2);
    }
}


package matrix.operations;

import matrix.compresor.CompressedColumnStorage;
import matrix.compresor.CompressedRowStorage;
import matrix.matrices.DoubleMatrix;
import matrix.matrices.Matrix;
import matrix.operations.parallel.*;
import matrix.operations.parallel.tiled.TiledThreadsMultiplication;
import org.openjdk.jmh.annotations.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class ParallelMultiplicationBenchmarking {

    @State(Scope.Thread)
    public static class Operands{
        private final int n = 4096;
        private Matrix a = new DoubleMatrix();
        private Matrix b = new DoubleMatrix();

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
        }

    }

    @Benchmark
    @Fork(value=2)
    @Measurement(iterations = 4)
    @Warmup(iterations = 2)
    public void streamsMultiplication(Operands operands){
        StreamsMultiplication.execute(operands.a, operands.b);
    }

    @Benchmark
    @Fork(value=2)
    @Measurement(iterations = 4)
    @Warmup(iterations = 2)
    public void streamsParallelMultiplication(Operands operands){
       ParallelStreamsMultiplication.execute(operands.a, operands.b);
    }

    @Benchmark
    @Fork(value=2)
    @Measurement(iterations = 4)
    @Warmup(iterations = 2)
    public void threadsParallelMultiplication(Operands operands){
        ParallelThreadsMultiplication.execute(operands.a, operands.b);
    }

    @Benchmark
    @Fork(value=2)
    @Measurement(iterations = 4)
    @Warmup(iterations = 2)
    public void executorsParallelMultiplication(Operands operands){
        ParalllelExecutorsMultiplication.execute(operands.a, operands.b);
    }

    @Benchmark
    @Fork(value=2)
    @Measurement(iterations = 4)
    @Warmup(iterations = 2)
    public void tiledMultiplication(Operands operands){
        TiledThreadsMultiplication.execute(operands.a, operands.b);
    }


}

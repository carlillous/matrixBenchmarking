package matrix.operations.parallel.tiled;

import matrix.matrices.DoubleMatrix;
import matrix.matrices.Matrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TiledThreadsMultiplication {


    public static Matrix execute(Matrix a, Matrix b) {
        Matrix c = new DoubleMatrix();
        c.startMatrix(a.getRowsLen(), b.getColsLen());

        int numThreads = Runtime.getRuntime().availableProcessors();
        int TILE_SIZE = TiledMultiplication.determineTileSize(a.getRowsLen());

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < a.getRowsLen(); i += TILE_SIZE) {
            for (int j = 0; j < b.getColsLen(); j += TILE_SIZE) {
                for (int k = 0; k < b.getRowsLen(); k += TILE_SIZE) {
                    int finalI = i;
                    int finalJ = j;
                    int finalK = k;
                    executor.submit(() -> {
                        TiledMultiplication.execute(a, b, c, finalI, finalJ, finalK, TILE_SIZE);
                    });
                }
            }
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        return c;
    }

}

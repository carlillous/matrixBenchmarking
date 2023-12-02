package matrix.operations.parallel.tiled;

import matrix.matrices.Matrix;

public class TiledMultiplication {

    public static void execute(Matrix a, Matrix b, Matrix c, int row, int col, int comm, int TILE_SIZE) {
        for (int i = row; i < Math.min(row + TILE_SIZE, a.getRowsLen()); i++) {
            for (int j = col; j < Math.min(col + TILE_SIZE, b.getColsLen()); j++) {
                double aux = c.getValue(i, j); // Accumulate in the existing value
                for (int k = comm; k < Math.min(comm + TILE_SIZE, b.getRowsLen()); k++) {
                    aux += a.getValue(i, k) * b.getValue(k, j);
                }
                synchronized (c) { // Synchronize on matrix c
                    c.setValue(i, j, aux);
                }
            }
        }
    }



    public static int determineTileSize(int matrixSize) {
        int TILE_SIZE = 4;

        if (matrixSize > 1024) {
            TILE_SIZE = 128;
        } else if (matrixSize > 512) {
            TILE_SIZE = 64;
        } else if (matrixSize > 256){
            TILE_SIZE = 32;
        }else if (matrixSize > 64 ){
            TILE_SIZE = 16;
        }

        return TILE_SIZE;
    }

    public static int[] decideTileSize(int TILE_SIZE, int rowSize,int colSize, int i,int j, int k){

        int tileSizeI = Math.min(TILE_SIZE, rowSize - i);
        int tileSizeJ = Math.min(TILE_SIZE, colSize - j);
        int tileSizeK = Math.min(TILE_SIZE, rowSize - k);

        return new int[]{tileSizeI,tileSizeJ, tileSizeK};

    }

}

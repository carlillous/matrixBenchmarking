package matrix.operations;

import matrix.compresor.CompressedColumnStorage;
import matrix.compresor.CompressedRowStorage;
import matrix.compresor.Coordinate;
import matrix.matrices.Matrix;
import matrix.reader.MatrixReader;

public class ReadMatrixMultiplication {

    public static void main(String[] args) {
        String filePath = "C:/Users/Carlos/Desktop/mc2depi.mtx";

        MatrixReader reader = new MatrixReader();
        Coordinate coords = reader.readerToCoordinate(filePath);

        CompressedRowStorage a = new CompressedRowStorage(coords.coordsToMatrix());
        CompressedColumnStorage b = new CompressedColumnStorage(coords.coordsToMatrix());

        long start = System.currentTimeMillis();
        new Multiplication().compressedMultiplication(a,b);
        long stop = System.currentTimeMillis();
        System.out.println("Tiempo = "+(stop-start) * 1e-3);

    }

}

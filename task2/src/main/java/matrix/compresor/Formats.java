package matrix.compresor;
import matrix.matrices.Matrix;

public interface Formats {

    void compress(Matrix matrix);
    Matrix decompress();
    void getValues();

}

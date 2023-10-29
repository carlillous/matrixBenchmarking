package matrix.matrices;

public interface Matrix {

    int getRowsLen();
    int getColsLen();
    void startMatrix(int rows, int cols);
    void setValue(int row, int col, double val);
    double getValue(int row, int col);
    void printMatrix();
}

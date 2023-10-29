package matrix.matrices;

public class DoubleMatrix implements Matrix {
    public double [][] matrix;

    @Override
    public int getRowsLen() {
        return matrix.length;
    }

    @Override
    public int getColsLen() {
        return matrix[0].length;
    }

    @Override
    public void startMatrix(int rows, int cols) {
        this.matrix = new double[rows][cols];
    }

    @Override
    public void setValue(int row, int col, double val) {
        matrix[row][col] =  val;
    }

    public void setMatrix(double[][] newMatrix) {
        this.matrix = newMatrix;
    }

    @Override
    public void printMatrix() {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public double getValue(int row, int col) {
        return matrix[row][col];
    }
}

package matrix.compresor;
import matrix.matrices.DoubleMatrix;
import matrix.matrices.IntMatrix;
import matrix.matrices.Matrix;

import java.util.ArrayList;

public class CompressedColumnStorage implements Formats {

    public ArrayList<Integer> col_ptr;
    public ArrayList<Integer> row;
    public ArrayList<Integer> val;
    public int size;
    private String type;

    public CompressedColumnStorage(Matrix matrix) {

        this.col_ptr = new ArrayList<>();
        this.row = new ArrayList<>();
        this.val = new ArrayList<>();
        this.size = matrix.getRowsLen();
        this.checkType(matrix);
        this.compress(matrix);
    }

    @Override
    public void compress(Matrix matrix) {
        col_ptr.add(0);
        int col_count = 0;
        for(int i = 0;i<matrix.getColsLen();i++){
            for(int j=0; j<matrix.getRowsLen();j++){
                if (matrix.getValue(j,i) != 0) {
                    val.add((int) matrix.getValue(j,i));
                    row.add(j);
                    col_count += 1;
                }

                matrix.getValue(j,i);
            }
            col_ptr.add(col_count);
        }
    }

    @Override
    public Matrix decompress() {

        int numCols = col_ptr.size() - 1;
        int numRows = 0;
        for (int value : row) {
            numRows = Math.max(numRows, value+1);
        }

        Matrix matrix;
        if (this.type.equals("double")) {
            matrix = new DoubleMatrix();
        } else {
            matrix = new IntMatrix();
        }
        matrix.startMatrix(numRows, numCols);

        for (int j = 0; j < numCols; j++) {
            int colStart = col_ptr.get(j);
            int colEnd = col_ptr.get(j + 1);

            for (int i = colStart; i < colEnd; i++) {
                int rowIdx = row.get(i);
                double value = val.get(i);
                matrix.setValue(rowIdx,j,value);
            }
        }

        return matrix;
    }

    @Override
    public void getValues(){
        System.out.println(col_ptr);
        System.out.println(row);
        System.out.println(val);
    }

    private void checkType(Matrix matrix){
        if (matrix instanceof DoubleMatrix) {
            this.type = "double";
        } else {
            this.type = "int";
        }

    }

}

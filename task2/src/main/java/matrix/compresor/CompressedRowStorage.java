package matrix.compresor;
import matrix.matrices.DoubleMatrix;
import matrix.matrices.IntMatrix;
import matrix.matrices.Matrix;

import java.util.ArrayList;

public class CompressedRowStorage implements Formats {

    public ArrayList<Integer> row_ptr;
    public ArrayList<Integer> col;
    public ArrayList<Integer> val;
    public int size;
    private String type;

    public CompressedRowStorage(Matrix matrix) {

        row_ptr = new ArrayList<>();
        col = new ArrayList<>();
        val = new ArrayList<>();
        this.size = matrix.getRowsLen();
        this.checkType(matrix);
        this.compress(matrix);

    }

    @Override
    public void compress(Matrix matrix) {
        row_ptr.add(0);

        int row_count = 0;
        for (int i = 0; i < matrix.getRowsLen(); i++){
            for (int j = 0; j<matrix.getColsLen();j++){
                if (matrix.getValue(i,j) != 0){
                    col.add(j);
                    val.add((int) matrix.getValue(i,j));
                    row_count +=1;
                }
            }
            row_ptr.add(row_count);
        }

    }

    @Override
    public Matrix decompress() {
        int numRows = row_ptr.size() - 1;
        int numCols = 0;
        for (int value : col) {
            numCols = Math.max(numCols, value + 1);
        }

        Matrix matrix;
        if (this.type.equals("double")) {
            matrix = new DoubleMatrix();
        } else {
            matrix = new IntMatrix();
        }
        matrix.startMatrix(numRows, numCols);

        for (int i = 0; i < numRows; i++) {
            int rowStart = row_ptr.get(i);
            int rowEnd = row_ptr.get(i + 1);

            for (int j = rowStart; j < rowEnd; j++) {
                int column = col.get(j);
                int value = val.get(j);
                matrix.setValue(i,column,value);
            }
        }

        return matrix;
    }

    @Override
    public void getValues(){
        System.out.println(row_ptr);
        System.out.println(col);
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






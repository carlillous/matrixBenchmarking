package matrix.compresor;

import matrix.matrices.DoubleMatrix;
import matrix.matrices.Matrix;
import java.util.ArrayList;
import java.util.Collections;

public class Coordinate {

    public ArrayList<Integer> row;
    public ArrayList<Integer> col;
    private ArrayList<Integer> val;

    public Coordinate(){
        this.col= new ArrayList<>();
        this.row = new ArrayList<>();
        this.val = new ArrayList<>();
    }

    public void add(int rows, int column, double value){
        this.col.add(column);
        this.row.add(rows);
        this.val.add((int)value);
    }

    public Matrix coordsToMatrix(){
        Matrix matrix = new DoubleMatrix();
        matrix.startMatrix(Collections.max(row)+1,Collections.max(col)+1);

        for(int i =0;i<row.size();i++){
            matrix.setValue(this.row.get(i),this.col.get(i),this.val.get(i));
        }
        return matrix;
    }

}

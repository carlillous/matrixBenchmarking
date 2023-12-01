package matrix.operations.sequential;


import matrix.matrices.DoubleMatrix;
import matrix.matrices.Matrix;

public class ConventionalMultiplication {

	public static Matrix execute(Matrix a, Matrix b) {
		assert a.getRowsLen() == b.getRowsLen();
		int n = a.getRowsLen();
		Matrix c = new DoubleMatrix();
		c.startMatrix(a.getRowsLen(),b.getColsLen());
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				double aux = 0;
				for (int k = 0; k < n; k++) {
					aux += a.getValue(i,k) * b.getValue(k,j);
				}
				c.setValue(i,j,aux);
			}
		}
		return c;
	}
}


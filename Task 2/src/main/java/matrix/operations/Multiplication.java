package matrix.operations;


import matrix.compresor.CompressedColumnStorage;
import matrix.compresor.CompressedRowStorage;
import matrix.matrices.DoubleMatrix;
import matrix.matrices.Matrix;

public class Multiplication {

	public Matrix conventionalMultiplication(Matrix a, Matrix b) {
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

	public Matrix compressedMultiplication(CompressedRowStorage a, CompressedColumnStorage b){
		Matrix c = new DoubleMatrix();
		c.startMatrix(a.size,a.size);

		for (int i=0;i<a.size;i++){
			for (int j=0;j<b.size;j++){
				int ii = a.row_ptr.get(i);
				int iEnd = a.row_ptr.get(i+1);
				int jj = b.col_ptr.get(j);
				int jEnd = b.col_ptr.get(j+1);
				double s = 0;
				while(ii<iEnd && jj < jEnd) {
					int aa = a.col.get(ii);
					int bb = b.row.get(jj);
					if (aa == bb) {
						s+= a.val.get(ii) * b.val.get(jj);
						ii++;
						jj++;
					}
					else if (aa < bb) ii++;
					else jj++;
				}
				if (s != 0) c.setValue(i,j,s);
			}
		}

		return c;
	}
}


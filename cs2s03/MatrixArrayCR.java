package cs2s03;

public class MatrixArrayCR {

	private long[][] mat;
	
	// First Constructor - Takes only a 9 element array
	MatrixArrayCR(final long[] array) throws WrongLength {
		mat = new long[3][3];
		int len = array.length;
		
		if (len != 9) throw new WrongLength(len, "MatrixArrayFlat");
		
		/* Array		Mat
		 * A0 A1 A2		M0 M1 M2
		 * A3 A4 A5		M3 M4 M5
		 * A6 A7 A8		M6 M7 M8
		 * 
		 * Where mat = [[M0, M3, M6],[M1, M4, M7], [M2, M5, M8]]
		 * */
		//Iterate over elements in array and assign to mat
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++) {
				mat[j][i] = array[i + j*3];
			}
		}
	}

	// Second Constructor - Takes an n x n array
	MatrixArrayCR(final long[] array, int n) throws WrongLength {
		mat = new long[n][n];
		int len = array.length;
		
		if(len != (n*n)) throw new WrongLength(len, "MatrixArrayFlat");
		
		for(int i = 0; i < mat.length; i++){
			for(int j = 0; j < mat.length; j++){
				mat[j][i] = array[i + j*mat.length];
			}
		}
	}

	// Create a copy of a matrix
	MatrixArrayCR copy(final MatrixArrayCR orgMat) {
		MatrixArrayCR newMat = orgMat;
		return newMat;
	}
	
	// Create an identity matrix
	MatrixArrayCR id(int n) throws WrongLength {
		int size = n * n;
		long[] identity = new long[size];
		int place = 0; 
		
		for (int i = 0; i < n*n; i++) {
			if (i%n == 0) {
				identity[i + place] = 1;
				place += 1;
			}
		}
		
		return new MatrixArrayCR(identity, n);
	}
	
	long[] toArray() {
		long[] flat = new long[mat.length * mat.length];
		
		for (int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat.length; j++) {
				flat[i*mat.length + j] = mat[i][j];
			}
		}
		return flat;
	}

	// Computes the ith power of a matrix
	MatrixArrayCR matrixpower(int i) throws WrongLength, WrongPower {
		if (i < 0) throw new WrongPower(i);
		
		else if (i == 0) return id(mat.length);
		
		else if (i == 1) return this;
		
		else {
			long[][] org = mat;
			long[][] copy = mat;
			
			int dim = mat.length;
			
			long[] result = new long[dim*dim];
			
			//iterate over each column and row in array
			for(int l = 1; l < i; l++) {
				for(int row = 0; row < dim; row++) {
					for(int col = 0; col < dim; col++) {
						for(int j = 0; j < dim; j++) {
							result[dim*row + col] += 
									org[row][j] * copy[j][col];
						}
					}
				}
				copy = (new MatrixArrayCR(result, dim)).mat;
				if(l != i - 1) result = new long[dim*dim];
			}
			return new MatrixArrayCR(result, dim);
		}
			
	}
}

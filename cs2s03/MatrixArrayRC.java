package cs2s03;

public class MatrixArrayRC {

	private long[][] mat;
	
	// First Constructor - Takes only a 9 element array
	MatrixArrayRC(final long[] array) throws WrongLength {
		mat = new long[3][3];
		int len = array.length;
		
		if (len != 9) throw new WrongLength(len, "MatrixArrayFlat");
		
		/* Array
		 * A0 A1 A2
		 * A3 A4 A5
		 * A6 A7 A8
		 * 
		 * Where mat = [[A0, A1, A2],[A3, A4, A5], [A6, A7, A8]]
		 * */
		//Iterate over elements in array and assign to mat
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++) {
				mat[i][j] = array[i*3 + j];
			}
		}
	}

	// Second Constructor - Takes an n x n array
	MatrixArrayRC(final long[] array, int n) throws WrongLength {
		mat = new long[n][n];
		int len = array.length;
		
		if(len != (n*n)) throw new WrongLength(len, "MatrixArrayRC");
		
		for(int i = 0; i < mat.length; i++){
			for(int j = 0; j < mat.length; j++){
				mat[i][j] = array[i*mat.length + j];
			}
		}
	}

	// Create a copy of a matrix
	MatrixArrayRC copy(final MatrixArrayRC orgMat) {
		MatrixArrayRC newMat = orgMat;
		return newMat;
	}
	
	// Create an identity matrix
	MatrixArrayRC id(int n) throws WrongLength {
		int size = n * n;
		long[] identity = new long[size];
		int place = 0; 
		
		for (int i = 0; i < n*n; i++) {
			if (i%n == 0) {
				identity[i + place] = 1;
				place += 1;
			}
		}
		
		return new MatrixArrayRC(identity, n);
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
	MatrixArrayRC matrixpower(int i) throws WrongLength, WrongPower {
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
				copy = (new MatrixArrayRC(result, dim)).mat;
				if(l != i - 1) result = new long[dim*dim];
			}
			return new MatrixArrayRC(result, dim);
		}
			
	}
}

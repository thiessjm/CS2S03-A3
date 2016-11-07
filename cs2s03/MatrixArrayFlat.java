package cs2s03;

class WrongLenN extends Throwable {
	private int len;
	private int dim;
	private String name;
	
	public WrongLenN (int len, int dim, String name) {
		this.len = len;
		this.name = name;
		this.dim = dim;
	}
	
	public String FormatError() {
		return "Incorrect array size: " + this.len + 
				" (expected " + dim + ") at " + this.name;
	}
}

public class MatrixArrayFlat {

	private long[] mat;
	
	// First Constructor - Takes only a 9 element array
	MatrixArrayFlat(final long[] array) throws WrongLength {
		mat = new long[9];
		int len = array.length;
		
		if (len != 9) throw new WrongLength(len, "MatrixArrayFlat");
		
		//Iterate over elements in array and assign to mat
		for(int i = 0; i < 9; i++){
			mat[i] = array[i];
		}
		
	}

	// Second Constructor - Takes an n x n array
	MatrixArrayFlat(final long[] array, int n) throws WrongLenN {
		mat = new long[n*n];
		int len = array.length;
		
		if(len != (n*n)) throw new WrongLenN(len, n*n, "MatrixArrayFlat");
		
		for(int i = 0; i < len; i++){
			mat[i] = array[i];
		}

	}

	// Create a copy of a matrix
	MatrixArrayFlat copy(final MatrixArrayFlat orgMat) {
		MatrixArrayFlat newMat = orgMat;
		return newMat;
	}
	
	// Create an identity matrix
	MatrixArrayFlat id(int n) throws WrongLenN {
		int size = n * n;
		long[] identity = new long[size];
		int place = 0; 
		
		for (int i = 0; i < n*n; i++) {
			if (i%n == 0) {
				identity[i + place] = 1;
				place += 1;
			}
		}
		
		return new MatrixArrayFlat(identity, n);
	}
	
	long[] toArray() {
		long[] flat = new long[mat.length];
		
		for (int i = 0; i < mat.length; i++) {
			flat[i] = mat[i];
		}
		return flat;
	}
	
	// Computes the ith power of a matrix
	MatrixArrayFlat matrixpower(int i) throws WrongLength, WrongLenN, WrongPower {
		if (i < 0) throw new WrongPower(i);
		
		else if (i == 0) return id((int) Math.sqrt(mat.length));
		
		else {
			long[] org = mat;
			long[] copy = mat;
			
			int dim = (int) Math.sqrt(mat.length);
			
			long[] result = new long[dim * dim];
			
			//iterate over each column and row in array
			for(int l = 1; l < i; l++) {
				for(int row = 0; row < dim; row++) {
					for(int col = 0; col < dim; col++) {
						for(int j = 0; j < dim; j++) {
							result[dim*row + col] += 
									org[dim*row + j] * copy[j*dim + col];
						}
					}
				}
				copy = result;
			}
			
			return new MatrixArrayFlat(copy, dim);
		}
			
	}
}

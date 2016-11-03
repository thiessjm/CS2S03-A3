package cs2s03;
public class Matrix3x3flat {
	// Create record of 9 values
	private class Record9 {
		long P0, P1, P2, 
		     P3, P4, P5, 
		     P6, P7, P8;
	}

	private Record9 mat;

	// Constructor
	Matrix3x3flat(final long[] array) {
		mat = new Record9();
		//TODO
		// Check whether array has 9 elements - throw exception if not
		// CODE GOES HERE

		// Construct Matrix3x3flat using values of array interpreted row wise
		mat.P0 = array[0]; mat.P1 = array[1]; mat.P2 = array[2];
		mat.P3 = array[3]; mat.P4 = array[4]; mat.P5 = array[5];
		mat.P6 = array[6]; mat.P7 = array[7]; mat.P8 = array[8];
	}

	
	// Create a copy of a matrix
	Matrix3x3flat copy(final Matrix3x3flat orgMat) {
		Matrix3x3flat newMat = orgMat;
		return newMat;
	}

	
	// Create an identity matrix
	Matrix3x3flat id() {
		long[] identity = new long[] { 1, 0, 0, 1, 0, 0, 1, 0, 0 };
		return new Matrix3x3flat(identity);
	}

	
	// Compute the dot product of a row and column matrix
	long dotProd(long[] Row, long[] Column) {
		long A = 0;

		for (int i = 0; i < Row.length; i++) {
			A += Row[i] * Column[i];
		}
		return A;
	}
	
	
	// Takes an array of arrays (of size 9) and flattens
	long[] flatten(long[][] grid) {
		long[] flat = new long[9];
		int index = 0;

		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				flat[index++] = grid[row][col];
			}
		}
		return flat;
	}

	
	// Computes the ith power of a matrix
	Matrix3x3flat matrixpower(int i) {
		if (i < 0) {
			return this;// Throw exception
			
		} else if (i == 0) {
			return id();
			
		} else {
			Matrix3x3flat org = copy(this);

			// Define rows and columns of this matrix to apply dotProd
			long[] R0 = new long[] { org.mat.P0, org.mat.P1, org.mat.P2 };
			long[] R1 = new long[] { org.mat.P3, org.mat.P4, org.mat.P5 };
			long[] R2 = new long[] { org.mat.P6, org.mat.P7, org.mat.P8 };

			long[] C0 = new long[] { org.mat.P0, org.mat.P3, org.mat.P6 };
			long[] C1 = new long[] { org.mat.P1, org.mat.P4, org.mat.P7 };
			long[] C2 = new long[] { org.mat.P2, org.mat.P5, org.mat.P8 };

			long[] NR0 = R0;
			long[] NR1 = R1;
			long[] NR2 = R2;

			for (int n = 0; n < i; n++) {
				NR0 = new long[] 
					{ dotProd(NR0, C0), dotProd(NR0, C1), dotProd(NR0, C2) };
				
				NR1 = new long[] 
					{ dotProd(NR1, C0), dotProd(NR1, C1), dotProd(NR1, C2) };
				
				NR2 = new long[] 
					{ dotProd(NR2, C0), dotProd(NR2, C1), dotProd(NR2, C2) };
			}

			long[][] newMatrix = new long[][] { NR0, NR1, NR2 };

			return new Matrix3x3flat(flatten(newMatrix));
		}
	}
}
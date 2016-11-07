package cs2s03;

/* Class implements 3x3 matrix as a record of 3 rows, R0, R1, R2 
 * each row a record of 3 values */
public class Matrix3x3rc {
	
	// Create record of 3 rows, each row a record of 3 values
	private class Row3 {
		
		// Create record of 3 values
		private class rowOfValues {
			long V0, V1, V2;
		}
		private rowOfValues R0 = new rowOfValues();
		private rowOfValues R1 = new rowOfValues();
		private rowOfValues R2 = new rowOfValues();
	}
	
	//Create instance of Row3
	private Row3 mat;

	// Constructor
	Matrix3x3rc(long[] array) throws WrongLength {
		mat = new Row3();
		int len = array.length;
		
		if (len != 9) throw new WrongLength(len, "Matrix3x3rc" );

		// Construct Matrix3x3rc using values of array interpreted row wise
		mat.R0.V0 = array[0]; mat.R0.V1 = array[1]; mat.R0.V2 = array[2];
		mat.R1.V0 = array[3]; mat.R1.V1 = array[4]; mat.R1.V2 = array[5];
		mat.R2.V0 = array[6]; mat.R2.V1 = array[7]; mat.R2.V2 = array[8];
		
	}
	
	// Create a copy of a matrix
	Matrix3x3rc copy(final Matrix3x3rc orgMat) {
		Matrix3x3rc newMat = orgMat;
		return newMat;
	}
	
	// Create an identity matrix
	Matrix3x3rc id() throws WrongLength {
		long[] identity = new long[] { 1, 0, 0, 0, 1, 0, 0, 0, 1 };
		return new Matrix3x3rc(identity);
	}
	
	// Compute the dot product of a row and column matrix
	long dotProd(long[] Row, long[] Column) {
		long A = 0;
		for (int i = 0; i < Row.length; i++) {
			A += Row[i] * Column[i]; 
		}
		return A;
	}
	
	long[] toArray() {
		long[] flat = new long[9];
		flat[0] = mat.R0.V0; flat[1] = mat.R0.V1; flat[2] = mat.R0.V2;
		flat[3] = mat.R1.V0; flat[4] = mat.R1.V1; flat[5] = mat.R1.V2;
		flat[6] = mat.R2.V0; flat[7] = mat.R2.V1; flat[8] = mat.R2.V2;
		
		return flat;
	}
	
	Matrix3x3rc matrixpower(int i) throws WrongLength, WrongPower {
		if (i < 0) throw new WrongPower(i);
			
		else if (i == 0) return id();
			
		else {
			Matrix3x3rc org = copy(this);
			// Define rows and columns of this matrix to apply dotProd
			long[] R0 = 
					new long[] { org.mat.R0.V0, org.mat.R0.V1, org.mat.R0.V2 };
			long[] R1 = 
					new long[] { org.mat.R1.V0, org.mat.R1.V1, org.mat.R1.V2 };
			long[] R2 = 
					new long[] { org.mat.R2.V0, org.mat.R2.V1, org.mat.R2.V2 };

			long[] C0 = 
					new long[] { org.mat.R0.V0, org.mat.R1.V0, org.mat.R2.V0 };
			long[] C1 = 
					new long[] { org.mat.R0.V1, org.mat.R1.V1, org.mat.R2.V1 };
			long[] C2 = 
					new long[] { org.mat.R0.V2, org.mat.R1.V2, org.mat.R2.V2 };

			long[] NR0 = R0;
			long[] NR1 = R1;
			long[] NR2 = R2;

			for (int n = 1; n < i; n++) {
				NR0 = new long[] 
					{ dotProd(NR0, C0), dotProd(NR0, C1), dotProd(NR0, C2) };
				
				NR1 = new long[] 
					{ dotProd(NR1, C0), dotProd(NR1, C1), dotProd(NR1, C2) };
				
				NR2 = new long[] 
					{ dotProd(NR2, C0), dotProd(NR2, C1), dotProd(NR2, C2) };
			}

			long[] result = new long[9];
			result[0] = NR0[0]; result[1] = NR0[1]; result[2] = NR0[2];
			result[3] = NR1[0]; result[4] = NR1[1]; result[5] = NR1[2];
			result[6] = NR2[0]; result[7] = NR2[1]; result[8] = NR2[2];

			return new Matrix3x3rc(result);
		}
	}
}

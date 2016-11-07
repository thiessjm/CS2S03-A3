package cs2s03;

/* Class implements 3x3 matrix as a record of 3 columns, C0, C1, C2 
 * each column a record of 3 values */
public class Matrix3x3cr {
	
	// Create record of 3 rows, each row a record of 3 values
	private class Col3 {
		
		// Create record of 3 values
		private class colOfValues {
			long V0, V1, V2;
		}
		private colOfValues C0 = new colOfValues();
		private colOfValues C1 = new colOfValues();
		private colOfValues C2 = new colOfValues();
	}
	
	//Create instance of Row3
	private Col3 mat;

	// Constructor
	Matrix3x3cr(long[] array) throws WrongLength {
		mat = new Col3();
		int len = array.length;
		
		if (len != 9) throw new WrongLength(len, "Matrix3x3cr" );

		// Construct Matrix3x3cr using values of array interpreted row wise
		mat.C0.V0 = array[0]; mat.C1.V0 = array[1]; mat.C2.V0 = array[2];
		mat.C0.V1 = array[3]; mat.C1.V1 = array[4]; mat.C2.V1 = array[5];
		mat.C0.V2 = array[6]; mat.C1.V2 = array[7]; mat.C2.V2 = array[8];
		
	}
	
	// Create a copy of a matrix
	Matrix3x3cr copy(final Matrix3x3cr orgMat) {
		Matrix3x3cr newMat = orgMat;
		return newMat;
	}
	
	// Create an identity matrix
	Matrix3x3cr id() throws WrongLength {
		long[] identity = new long[] { 1, 0, 0, 0, 1, 0, 0, 0, 1 };
		return new Matrix3x3cr(identity);
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
		flat[0] = mat.C0.V0; flat[1] = mat.C1.V0; flat[2] = mat.C2.V0;
		flat[3] = mat.C0.V1; flat[4] = mat.C1.V1; flat[5] = mat.C2.V1;
		flat[6] = mat.C0.V2; flat[7] = mat.C1.V2; flat[8] = mat.C2.V2;
		
		return flat;
	}
	
	Matrix3x3cr matrixpower(int i) throws WrongLength, WrongPower {
		if (i < 0) throw new WrongPower(i);
			
		else if (i == 0) return id();
			
		else {
			Matrix3x3cr org = copy(this);
			// Define rows and columns of this matrix to apply dotProd
			long[] R0 = 
					new long[] { org.mat.C0.V0, org.mat.C1.V0, org.mat.C2.V0 };
			long[] R1 = 
					new long[] { org.mat.C0.V1, org.mat.C1.V1, org.mat.C2.V1 };
			long[] R2 = 
					new long[] { org.mat.C0.V2, org.mat.C1.V2, org.mat.C2.V2 };

			long[] C0 = 
					new long[] { org.mat.C0.V0, org.mat.C0.V1, org.mat.C0.V2 };
			long[] C1 = 
					new long[] { org.mat.C1.V0, org.mat.C1.V1, org.mat.C1.V2 };
			long[] C2 = 
					new long[] { org.mat.C2.V0, org.mat.C2.V1, org.mat.C2.V2 };

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

			return new Matrix3x3cr(result);
		}
	}
}

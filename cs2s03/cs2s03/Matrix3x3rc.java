package cs2s03;

/* Class implements 3x3 matrix as a record of 3 rows, R0, R1, R2 
 * each row a record of 3 values
 */

public class Matrix3x3rc {
	
	// Create record of 3 rows, each row a record of 3 values
	private class Row3 {
		
		// Create record of 3 values
		private class rowOfValues {
			long V0, V1, V2;
		}
		
		rowOfValues R0, R1, R2;
	}
	
	//Create instance of Row3
	private Row3 mat;

	// Constructor
	Matrix3x3rc(final long[] array) {
		mat = new Row3();
		//TODO
		// Check whether array has 9 elements - throw exception if not
		// CODE GOES HERE

		// Construct Matrix3x3flat using values of array interpreted row wise
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
	Matrix3x3rc id() {
		long[] identity = new long[] { 1, 0, 0, 1, 0, 0, 1, 0, 0 };
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
}

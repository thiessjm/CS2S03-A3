package cs2s03;

class WrongLength extends Throwable {
	private int len;
	private String name;
	
	public WrongLength (int len, String name) {
		this.len = len;
		this.name = name;
	}
	
	public String FormatError() {
		return "Incorrect array size: " + this.len + 
				" (expected 9) at " + this.name;
	}
}

class WrongPower extends Throwable {
	private int power;
	
	public WrongPower (int power) {
		this.power = power;
	}
	
	public String FormatError() {
		return "Invalid power: " + this.power + 
				" (expected non-negative)";
	}
}

public class Matrix3x3flat {
	// Create record of 9 values
	private class Record9 {
		long P0, P1, P2, P3, P4, P5, P6, P7, P8;
	}

	private Record9 mat;

	// Constructor - takes an array of length 9
	Matrix3x3flat(final long[] array) throws WrongLength {
		mat = new Record9();
		int len = array.length;
		
		if (len != 9) throw new WrongLength(len, "Matrix3x3flat");

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
	Matrix3x3flat id() throws WrongLength {
		long[] identity = new long[] { 1, 0, 0, 0, 1, 0, 0, 0, 1 };
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
	
	// Takes am matrix of type Matrix3x3flat and flattens
	long[] toArray() {
		long[] flat = new long[9];
		flat[0] = mat.P0; flat[1] = mat.P1; flat[2] = mat.P2;
		flat[3] = mat.P3; flat[4] = mat.P4; flat[5] = mat.P5;
		flat[6] = mat.P6; flat[7] = mat.P7; flat[8] = mat.P8;
		
		return flat;
	}
	
	// Computes the ith power of a matrix
	Matrix3x3flat matrixpower(int i) throws WrongLength, WrongPower {
		if (i < 0) throw new WrongPower(i);
			
		else if (i == 0) return id();
			
		else {
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

			return new Matrix3x3flat(result);
		}
	}
}

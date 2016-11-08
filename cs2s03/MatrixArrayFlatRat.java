package cs2s03;

import org.apache.commons.math3.fraction.Fraction;

public class MatrixArrayFlatRat {
	private Fraction[] fr;
	
	MatrixArrayFlatRat(final Fraction[] array, int n) throws WrongLength{
		fr = new Fraction[n * n];
		int len = array.length;
		
		if (len != n*n) throw new WrongLength(len, "MatrixArrayFlatRat");
		
		for(int i = 0; i < array.length; i++) {
			fr[i] = array[i];
		}
	}
	
	//Flattens a MatrixArrayFlatRat object to an array of fractions
	Fraction[] toArray() {
		Fraction[] flat = new Fraction[fr.length];
		
		for(int i = 0; i < fr.length; i++) {
			flat[i] = fr[i];
		}
		return flat;
	}
	
	//Creates new matrix which excludes row i and column j
	MatrixArrayFlatRat select(int i, int j) throws WrongLength {
		int len = (int) Math.sqrt(fr.length);
		
		//Create new array B with one fewer rows and one fewer columns
		Fraction[] B = new Fraction[fr.length - (len + len - 1)]; 
		int index = 0;
		
		//Loops over each index in array A
		//If the index is in the specified row i or col j
		//Do nothing, otherwise add the value at that index to new matrix B
		for (int k = 0; k < fr.length; k++) {
			if(index == B.length) break;
			
			if (Math.floor(k / len) == i) continue;
			
			else if (k % len == j) continue;
			
			else {
				B[index] = fr[k];
				index += 1;
			}
		}
		return new MatrixArrayFlatRat(B, (int) Math.sqrt(B.length));
	}
	
	//Calculates the matrix determinant using recursion
	Fraction deter() throws WrongLength{
		int len = (int) Math.sqrt(fr.length);
		Fraction[] det = new Fraction[len];
		
		//deter of 2x2 matrix
		// a*d - b*c
		if (fr.length == 4) {
			return (fr[0].multiply(fr[3]).subtract(fr[1].multiply(fr[2])));
		}
		
		for(int j = 0; j < len; j++) {
			Fraction selectDet = this.select(0, j).deter();
			
			if (j == 0) det[j] = fr[j].multiply(selectDet);
			
			else if (j % 2 == 0 ) {
				det[j] = det[j-1].add(fr[j].multiply(selectDet));
				
			} else if (j % 2 == 1 ) {
				det[j] = det[j-1].subtract(fr[j].multiply(selectDet));
			}
		}
		return det[len - 1];
	}
	
	//Transposes an n*n matrix of fractions
	MatrixArrayFlatRat transpose() throws WrongLength {
		int len = (int) Math.sqrt(fr.length);
		
		Fraction[] B = new Fraction[fr.length];
		for(int i = 0; i < len; i++) {
			for(int j = 0; j < len; j++) {
				B[i*len + j] = fr[i + j*len];
			}
		}
		return new MatrixArrayFlatRat(B, len);
	}
	
	//Multiply a matrix by a constant (fraction)
	MatrixArrayFlatRat mult(Fraction C) throws WrongLength {
		Fraction[] CxFR = new Fraction[fr.length];
		int len = (int) Math.sqrt(fr.length);
		
		for (int i = 0; i < fr.length; i++) {
			CxFR[i] = fr[i].multiply(C);
		}
		return new MatrixArrayFlatRat(CxFR, len);
	}
	
	
	/*The inverse of a matrix A is such that
	 * A * A^-1 = I	- the identity matrix 
	 * 
	 * This function calculates the inverse by first calculating the 
	 * Matrix of Minors (matMinor). We iterate though each entry in
	 * the matrix and calculate the determinant of the matrix remaining 
	 * after removing the row and column of the current entry. 
	 * (i.e The matrix of minors in a matrix of the same size containing
	 * determinants associated with the original matrix.
	 * 
	 * The creating matrix of cofactors is done in the same step as creating 
	 * the matrix of minors (technically matMinor should be called matCofactor)
	 * If the index is even, the entry is multiplied by + 1
	 * If the index is odd, the entry is multiplied by - 1 (done by negate)
	 * 
	 * The inverse is obtained by transposing the cofactor matrix (Adjugate) 
	 * and multiplying by the inverse of the determinant
	*/
	MatrixArrayFlatRat inverse() throws WrongLength{
		int len = (int) Math.sqrt(fr.length);
		Fraction inverseDet = this.deter().reciprocal();
		Fraction[] matMinor = new Fraction[fr.length];
		
		//Create matrix of minors
		//Includes cofactor application - negation of odd valued indices
		for(int i = 0; i < len; i++) {
			for(int j = 0; j < len; j++) {
				if ((i*len + j) % 2 == 0) {
					matMinor[i*len + j] = this.select(i, j).deter();
				} else {
					matMinor[i*len + j] = this.select(i, j).deter().negate();
				}
			}
		}
		//The transposed matrix of minors - Adjugate
		MatrixArrayFlatRat Adjugate = 
				(new MatrixArrayFlatRat(matMinor, len)).transpose();
		
		//The Adjugate times the inverse of the determinant 
		return Adjugate.mult(inverseDet);
		
	}
}
	

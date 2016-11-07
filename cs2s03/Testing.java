package cs2s03;

import static org.junit.Assert.*;
import org.junit.Test;

public class Testing {
	//Not everywhere 0, but M01 ^ 2 is everywhere 0
	public static long[] ID = new long[]{1, 0, 0, 0, 1, 0, 0, 0, 1};
	
	public static long[] m01 = {5, 5, 0, -5, -5, 0, 0, 0, 0};
	public static long[] m02 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	public static long[] m03 = {-2, 11, 0, 3, 1, -5, 7, 8, 0};
	public static long[] m04 = {10, 2, 2, 2, 1, 0, -2, -2, -2};
	public static long[] m05 = {-1, -1, -1, 1, 1, 1, -1, -1, -1};
	public static long[] m06 = {2, 4, 6, -1, -2, -3, 7, 8, 9};
	public static long[] m07 = {0, 0, 0, 5, 5, -3, 7, 7, 0};
	public static long[] m08 = {-6, 13, 9, 0, 15, -2, 4, 5, 0};
	public static long[] m09 = {2, 4, 6, 8, 10, 12, 14, 16, 18};
	public static long[] m10 = {23, 52, -10, 11, 0, 312, 2, 0, 0};
	
	public static long[][] master = 
			new long[][]{m01, m02, m03, m04, m05, m06, m07, m08, m09, m10};
	
	//Answers
	//The weird formatting to satisfy 80 char width limit
	public static long[] m01P2
	= {0, 0, 0, 0, 0, 0, 0, 0, 0};
	public static long[] m01P3 
	= {0, 0, 0, 0, 0, 0, 0, 0, 0};
	
	public static long[] m02P2 
	= {30, 36, 42, 66, 81, 96, 102, 126, 150};
	public static long[] m02P3 
	= {468, 576, 684, 1062, 1305, 1548, 1656, 2034, 2412};
	
	public static long[] m03P2 
	= {37, -11, -55, -38, -6, -5, 10, 85, -40};
	public static long[] m03P3 
	= {-492, -44, 55, 23, -464, 30, -45, -125, -425};
	
	public static long[] m04P2 
	= {100, 18, 16, 22, 5, 4, -20, -2, 0};
	public static long[] m04P3 
	= {1004, 186, 168, 222, 41, 36, -204, -42, -40};
	
	public static long[] m05P2 
	= {1, 1, 1, -1, -1, -1, 1, 1, 1};
	public static long[] m05P3 
	= {-1, -1, -1, 1, 1, 1, -1, -1, -1};
	
	public static long[] m06P2 
	= {42, 48, 54, -21, -24, -27, 69, 84, 99};
	public static long[] m06P3 
	= {414, 504, 594, -207, -252, -297, 747, 900, 1053};
	
	public static long[] m07P2 
	= {0, 0, 0, 4, 4, -15, 35, 35, -21};
	public static long[] m07P3 
	= {0, 0, 0, -85, -85, -12, 28, 28, -105};
	
	public static long[] m08P2 
	= {72, 162, -80, -8, 215, -30, -24, 127, 26};
	public static long[] m08P3 
	= {-752, 2966, 324, -72, 2971, -502, 248, 1723, -470};
	
	public static long[] m09P2 
	= {120, 144, 168, 264, 324, 384, 408, 504, 600};
	public static long[] m09P3 
	= {3744, 4608, 5472, 8496, 10440, 12384, 13248, 16272, 19296};
	
	public static long[] m10P2 
	= {1081, 1196, 15994, 877, 572, -110, 46, 104, -20};
	public static long[] m10P3 
	= {70007, 56212, 362342, 26243, 45604, 169694, 2162, 2392, 31988};
	
	
	//Answer arrays containing matrix power answers
	public static long[][] Power0 = {ID, ID, ID, ID, ID, ID, ID, ID, ID, ID};
	
	public static long[][] Power1 = master;
	
	public static long[][] Power2 = {m01P2, m02P2, m03P2, m04P2, m05P2, 
			                         m06P2, m07P2, m08P2, m09P2, m10P2};
	
	public static long[][] Power3 = {m01P3, m02P3, m03P3, m04P3, m05P3, 
			                         m06P3, m07P3, m08P3, m09P3, m10P3};
	
	//Array containing answer arrays
	public static long[][][] Ans = {Power0, Power1, Power2, Power3};
	
	
	
	@Test
	//This class tests correctness of matrix power for 10 matrices
	//over each matrix implementation class (6) for powers ranging
	//over -1 to 3
	public void TestCorrect() throws WrongLength, WrongPower {
		for (int i = -1; i < 4; i++) {
			for (int j = 0; j < 10; j++) {
				
				Matrix3x3flat Cl1 = new Matrix3x3flat(master[j]);
				Matrix3x3rc Cl2 = new Matrix3x3rc(master[j]);
				Matrix3x3cr Cl3 = new Matrix3x3cr(master[j]);
				MatrixArrayFlat Cl4 = new MatrixArrayFlat(master[j]);
				MatrixArrayRC Cl5 = new MatrixArrayRC(master[j]);
				MatrixArrayCR Cl6 = new MatrixArrayCR(master[j]);
				
				//Testing if WrongPower exception occurs. Fails if doesn't 
				if(i == -1) {
					try {
						Cl1.matrixpower(i);
						fail("Did not throw WrongPower exception on input -1");
					} catch (WrongPower e) {}
					
					try {
						Cl2.matrixpower(i);
						fail("Did not throw WrongPower exception on input -1");
					} catch (WrongPower e) {}
					
					try {
						Cl3.matrixpower(i);
						fail("Did not throw WrongPower exception on input -1");
					} catch (WrongPower e) {}
					
					try {
						Cl4.matrixpower(i);
						fail("Did not throw WrongPower exception on input -1");
					} catch (WrongPower e) {}
					
					try {
						Cl5.matrixpower(i);
						fail("Did not throw WrongPower exception on input -1");
					} catch (WrongPower e) {}
					
					try {
						Cl6.matrixpower(i);
						fail("Did not throw WrongPower exception on input -1");
					} catch (WrongPower e) {}
				}
				//If WrongPower exception occurs here, test should fail
				else {
					try {
					assertArrayEquals(Ans[i][j], Cl1.matrixpower(i).toArray());
					assertArrayEquals(Ans[i][j], Cl2.matrixpower(i).toArray());
					assertArrayEquals(Ans[i][j], Cl3.matrixpower(i).toArray());
					assertArrayEquals(Ans[i][j], Cl4.matrixpower(i).toArray());
					assertArrayEquals(Ans[i][j], Cl5.matrixpower(i).toArray());
					assertArrayEquals(Ans[i][j], Cl6.matrixpower(i).toArray());
					
					} catch (WrongLength e) {
						System.out.println(e.FormatError());
					}
				}
			}
		}
	}
	
	@Test
	public void TestEqual() throws WrongLength, WrongPower {
		
		//Already verified that -1 matrix power throws WrongPower exception
		//So -1 is excluded from this test
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 10; j++) {
				
				Matrix3x3flat Cl1 = new Matrix3x3flat(master[j]);
				Matrix3x3rc Cl2 = new Matrix3x3rc(master[j]);
				Matrix3x3cr Cl3 = new Matrix3x3cr(master[j]);
				MatrixArrayFlat Cl4 = new MatrixArrayFlat(master[j]);
				MatrixArrayRC Cl5 = new MatrixArrayRC(master[j]);
				MatrixArrayCR Cl6 = new MatrixArrayCR(master[j]);
				
				long[] T1 = Cl1.matrixpower(i).toArray();
				long[] T2 = Cl2.matrixpower(i).toArray();
				long[] T3 = Cl3.matrixpower(i).toArray();
				long[] T4 = Cl4.matrixpower(i).toArray();
				long[] T5 = Cl5.matrixpower(i).toArray();
				long[] T6 = Cl6.matrixpower(i).toArray();
				
				long[][] Powers = {T1, T2, T3, T4, T5, T6};
				
				for (int k = 0; k < Powers.length; k++) {
					for (int l = 0; l < Powers.length; l++) {
						assertArrayEquals(Powers[k], Powers[l]);
					}
				}
			}
		}
	}
}

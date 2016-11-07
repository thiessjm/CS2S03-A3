package cs2s03;

public class MainApp {
	
	static long intLen(long i) {
		return String.valueOf(i).length();
	}
	
	static String space(long i) {
		String space = "";
		for (int n = 0; n < (8 - i); n++) {
			space += " ";
		}
		return space;
	}
	
	static void printArraybyLine(long[] array) {
		for (int i = 0; i < array.length; i++) {
			if (i%Math.sqrt(array.length) == 0) System.out.println();
			
			System.out.printf("%d" + space(intLen(array[i])), array[i]);
		}
	}
	
	static void printArraySingle(long[] array) {
		System.out.printf("{");
		for (int i = 0; i < array.length; i++) {
			if(i == array.length - 1) System.out.printf("%d", array[i]);
			else System.out.printf("%d, ", array[i]);
		}
		System.out.printf("}");
		System.out.println();
	}
	
	
	public static void main(String[] args) throws WrongLength, WrongPower {
		long[] M01 = new long[]{5, 5, 0, -5, -5, 0, 0, 0, 0};
		long[] M02 = new long[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
		long[] M03 = new long[]{-2, 11, 0, 3, 1, -5, 7, 8, 0};
		long[] M04 = new long[]{10, 2, 2, 2, 1, 0, -2, -2, -2};
		long[] M05 = new long[]{-1, -1, -1, 1, 1, 1, -1, -1, -1};
		long[] M06 = new long[]{2, 4, 6, -1, -2, -3, 7, 8, 9};
		long[] M07 = new long[]{0, 0, 0, 5, 5, -3, 7, 7, 0};
		long[] M08 = new long[]{-6, 13, 9, 0, 15, -2, 4, 5, 0};
		long[] M09 = new long[]{2, 4, 6, 8, 10, 12, 14, 16, 18};
		long[] M10 = new long[]{23, 52, -10, 11, 0, 312, 2, 0, 0};
		
		long[] array2 = new long[9];
		array2 = M02;
		MatrixArrayRC y = new MatrixArrayRC(array2);
		
		for (int i = 1; i < 4; i++){
			printArraySingle(y.matrixpower(i).toArray()); 
		}
		System.out.println(y.toArray()[3]);
	}

}

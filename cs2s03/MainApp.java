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
	
	
	public static void main(String[] args) throws WrongLength, WrongLengthnxn, WrongPower {
		
		long[] array1 = new long[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
		MatrixArrayFlat x = new MatrixArrayFlat(array1, 3);
		
		System.out.println(x.matrixpower(1).toArray()[0]);
		
		printArraybyLine(x.matrixpower(1).toArray()); 
	}

}

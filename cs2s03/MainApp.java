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
	
	static void printArray(long[] array){
		System.out.println(array[0] + space(intLen(array[0])) + 
						   array[1] + space(intLen(array[1])) +
						   array[2]);
		
		System.out.println(array[3] + space(intLen(array[3])) + 
						   array[4] + space(intLen(array[4])) +
						   array[5]);
		
		System.out.println(array[6] + space(intLen(array[6])) + 
						   array[7] + space(intLen(array[7])) +
						   array[8]);
	}
	
	
	public static void main(String[] args) throws WrongLength, WrongPower {
		
		long[] array1 = new long[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
		Matrix3x3rc x = new Matrix3x3rc(array1);
		
		printArray(x.matrixpower(4).toArray()); 
		
		
	}

}

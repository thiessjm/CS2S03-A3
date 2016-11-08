package cs2s03;
import org.apache.commons.math3.fraction.Fraction;
public class MainApp {
	
	static long intLen(long i) {
		return String.valueOf(i).length();
	}
	
	static long frLen(Fraction f) {
		return f.toString().length();
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
	
	static void printArraybyLineFR(Fraction[] array) {
		for (int i = 0; i < array.length; i++) {
			if (i%Math.sqrt(array.length) == 0) System.out.println();
			
			System.out.printf(array[i] + space(frLen(array[i])));
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
	
	static void printArrayFR(Fraction[] array) {
		System.out.printf("{");
		for (int i = 0; i < array.length; i++) {
			if(i == array.length - 1) System.out.printf("%d", array[i]);
			else System.out.printf(array[i] + "%s", " ");
		}
		System.out.printf("}");
		System.out.println();
	}
	
	
	public static void main(String[] args) throws WrongLength, WrongPower {
		
		Fraction f1 = new Fraction(3, 1);
		Fraction f2 = new Fraction(0, 1);
		Fraction f3 = new Fraction(2, 1);
		Fraction f4 = new Fraction(2, 1);
		Fraction f5 = new Fraction(0, 1);
		Fraction f6 = new Fraction(-2, 1);
		Fraction f7 = new Fraction(0, 1);
		Fraction f8 = new Fraction(1, 1);
		Fraction f9 = new Fraction(1, 1);

		
		
		Fraction[] fArray = new Fraction[]{f1, f2, f3, f4, 
										   f5, f6, f7, f8, 
										   f9};
		
		MatrixArrayFlatRat x = new MatrixArrayFlatRat(fArray, 3);
		
//		printArraybyLineFR(x.select(0, 0));
		
		
		printArraybyLineFR(x.inverse().toArray());
		
		
	}

}

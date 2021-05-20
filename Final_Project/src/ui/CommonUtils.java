package ui;

import java.util.Scanner;

import exceptions.ReadIntOutOfRangeException;

public class CommonUtils {
	public CommonUtils() {
		
	}
	
	public int readIntBetweenRange(int lowerLimit, int upperLimit) throws ReadIntOutOfRangeException {
		Scanner kb = new java.util.Scanner(System.in);
		int readInt = kb.nextInt();
		if (readInt >= lowerLimit && readInt <= upperLimit) {
			return readInt;
		} else {
			throw new ReadIntOutOfRangeException("Read int is outside (" + lowerLimit + "..." + upperLimit + ")");
		}
	}
	
	public int readAnyInt() {
		Scanner kb = new java.util.Scanner(System.in);
		return kb.nextInt();
	}
	
	public String readShortName() {
		Scanner kb = new java.util.Scanner(System.in);
		String readString = kb.nextLine();
		return readString;
	}
}

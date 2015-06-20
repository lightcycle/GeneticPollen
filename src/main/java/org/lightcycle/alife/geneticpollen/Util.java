package org.lightcycle.alife.geneticpollen;

public class Util {
	static public int[] color(int red, int green, int blue) {
		return new int[] { bound(red, 0, 255), bound(green, 0, 255),
				bound(blue, 0, 255), 255 };
	}

	static public int[] color(int red, int green, int blue, int alpha) {
		return new int[] { bound(red, 0, 255), bound(green, 0, 255),
				bound(blue, 0, 255), bound(alpha, 0, 255) };
	}

	static public int bound(int input, int lower, int upper) {
		if (input < lower)
			return lower;
		if (input > upper)
			return upper;
		return input;
	}
}

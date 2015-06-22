package org.lightcycle.alife.geneticpollen.rules;

import static java.lang.Math.*;

public enum Direction {
	UPLEFT(-1, -1),
	LEFT(-1, 0),
	DOWNLEFT(-1, 1),
	DOWN(0, 1),
	DOWNRIGHT(1, 1),
	RIGHT(1, 0),
	UPRIGHT(1, -1),
	UP(0, -1);
	
	private int offsetX, offsetY;
	
	private Direction(int offsetX, int offsetY) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
	
	public static Direction getClosest(double x, double y) {
		double segment = (toDegrees(atan2(y, x)) + 180) / 22.5;
		if (segment < 1) {
			return LEFT;
		} else if (segment < 3) {
			return UPLEFT;
		} else if (segment < 5) {
			return UP;
		} else if (segment < 7) {
			return UPRIGHT;
		} else if (segment < 9) {
			return RIGHT;
		} else if (segment < 11) {
			return DOWNRIGHT;
		} else if (segment < 13) {
			return DOWN;
		} else if (segment < 15) {
			return DOWNLEFT;
		} else {
			return LEFT;
		}
	}
	
	public int getOffsetX() {
		return offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}
}

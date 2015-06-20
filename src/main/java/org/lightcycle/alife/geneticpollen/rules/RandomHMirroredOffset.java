package org.lightcycle.alife.geneticpollen.rules;

import java.util.Date;
import java.util.Random;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public class RandomHMirroredOffset implements Offset {
	private final Offset offset;
	
	private Random random;
	
	public RandomHMirroredOffset(Offset offset) {
		this.offset = offset;
		random = new Random((new Date()).getTime());
	}
	
	public int getX(Grid<Cell> grid, Cell cell) {
		int x = offset.getX(grid, cell);
		return random.nextBoolean() ? x : -x;
	}

	public int getY(Grid<Cell> grid, Cell cell) {
		return offset.getY(grid, cell);
	}
	
	public String toString() {
		return "RAND_MIRRORED_OFFSET" + "(" + offset.toString() + ")";
	}
}

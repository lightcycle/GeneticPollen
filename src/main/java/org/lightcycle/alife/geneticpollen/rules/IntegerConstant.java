package org.lightcycle.alife.geneticpollen.rules;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public class IntegerConstant implements IntegerSource {
	int value;

	public IntegerConstant(int value) {
		this.value = value;
	}
	
	public int getInt(Grid<Cell> grid, Cell cell) {
		return value;
	}
	
	public String toString() {
		return Integer.toString(value);
	}
}

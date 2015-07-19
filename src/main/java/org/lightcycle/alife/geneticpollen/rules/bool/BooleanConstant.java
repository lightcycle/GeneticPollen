package org.lightcycle.alife.geneticpollen.rules.bool;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;
import org.lightcycle.alife.geneticpollen.rules.BooleanSource;

public class BooleanConstant implements BooleanSource {
	boolean value;

	public BooleanConstant(boolean value) {
		this.value = value;
	}
	
	public boolean getBoolean(Grid<Cell> grid, Cell cell) {
		return value;
	}
	
	public String toString() {
		return value ? "true" : "false";
	}
}

package org.lightcycle.alife.pollenevo.rules;

import org.lightcycle.alife.pollenevo.Cell;

import org.lightcycle.alife.pollenevo.grid.Grid;

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

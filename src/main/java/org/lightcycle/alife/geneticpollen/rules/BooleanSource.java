package org.lightcycle.alife.geneticpollen.rules;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public interface BooleanSource {
	public boolean getBoolean(Grid<Cell> grid, Cell cell);
}

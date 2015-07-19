package org.lightcycle.alife.geneticpollen.rules.scalar;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public interface IntegerSource {
	public int getInt(Grid<Cell> grid, Cell cell);
}

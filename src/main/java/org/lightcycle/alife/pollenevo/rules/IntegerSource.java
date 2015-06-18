package org.lightcycle.alife.pollenevo.rules;

import org.lightcycle.alife.pollenevo.Cell;
import org.lightcycle.alife.pollenevo.grid.Grid;

public interface IntegerSource {
	public int getInt(Grid<Cell> grid, Cell cell);
}

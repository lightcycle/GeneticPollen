package org.lightcycle.alife.pollenevo.rules;

import org.lightcycle.alife.pollenevo.Cell;
import org.lightcycle.alife.pollenevo.grid.Grid;

public interface BooleanSource {
	public boolean getBoolean(Grid<Cell> grid, Cell cell);
}

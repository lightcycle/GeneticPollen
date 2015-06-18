package org.lightcycle.alife.pollenevo.action;

import org.lightcycle.alife.pollenevo.Cell;
import org.lightcycle.alife.pollenevo.grid.Grid;

public interface Action {
	public boolean apply(Grid<Cell> grid, Cell cell);
}

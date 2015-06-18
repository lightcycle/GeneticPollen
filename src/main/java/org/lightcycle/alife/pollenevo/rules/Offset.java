package org.lightcycle.alife.pollenevo.rules;

import org.lightcycle.alife.pollenevo.Cell;
import org.lightcycle.alife.pollenevo.grid.Grid;

public interface Offset {
	public int getX(Grid<Cell> grid, Cell cell);
	public int getY(Grid<Cell> grid, Cell cell);
}

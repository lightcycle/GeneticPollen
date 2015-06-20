package org.lightcycle.alife.geneticpollen.rules;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public interface Offset {
	public int getX(Grid<Cell> grid, Cell cell);
	public int getY(Grid<Cell> grid, Cell cell);
}

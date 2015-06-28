package org.lightcycle.alife.geneticpollen.action;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public interface Action {
	public boolean apply(Grid<Cell> grid, Cell cell);
}
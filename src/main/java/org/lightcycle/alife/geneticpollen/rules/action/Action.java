package org.lightcycle.alife.geneticpollen.rules.action;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;

import java.util.ListIterator;

public interface Action {
	public void apply(Grid<Cell> grid, Cell cell, ListIterator<Cell> cellIter);
}

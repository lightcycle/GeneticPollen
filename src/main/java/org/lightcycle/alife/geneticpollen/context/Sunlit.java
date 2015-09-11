package org.lightcycle.alife.geneticpollen.context;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Coordinate2D;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public class Sunlit implements ContextRule {
	@Override
	public void apply(Grid<Cell> grid) {
		// Clear connected context flag on all cells
		for (Cell cell : grid.getItems()) {
			cell.setLit(false);
		}

		// Determine highest cell in each column
		Cell[] topcell = new Cell[grid.getWidth()];
		for (Cell cell : grid.getItems()) {
			if (topcell[cell.getX()] == null || topcell[cell.getX()].getY() > cell.getY()) {
				topcell[cell.getX()] = cell;
			}
		}
		for (Cell cell : topcell) {
			if (cell != null) {
				cell.setLit(true);
			}
		}
	}
}

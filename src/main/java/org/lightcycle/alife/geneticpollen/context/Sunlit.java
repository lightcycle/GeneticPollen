package org.lightcycle.alife.geneticpollen.context;

import org.lightcycle.alife.geneticpollen.grid.Coordinate2D;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public class Sunlit {
	public static <T extends Coordinate2D & Lightable> void determineSunlit(Grid<T> grid) {
		// Clear connected context flag on all cells
		for (T cell : grid.getItems()) {
			cell.setLit(false);
		}
		
		// Determine highest cell in each column
		Object[] topcell = new Object[grid.getWidth()];
		for (T cell : grid.getItems()) {
			if (topcell[cell.getX()] == null || ((T)topcell[cell.getX()]).getY() > cell.getY()) {
				topcell[cell.getX()] = cell;
			}
		}
		for (Object cell : topcell) {
			if (cell != null) {
				((T)cell).setLit(true);
			}
		}
	}
}

package org.lightcycle.alife.pollenevo.context;

import org.lightcycle.alife.pollenevo.grid.Coordinate2D;
import org.lightcycle.alife.pollenevo.grid.Grid;

public class Sunlit {
	@SuppressWarnings("unchecked")
	public static <T extends Coordinate2D & Context> void determineSunlit(Grid<T> grid) {
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

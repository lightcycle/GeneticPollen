package org.lightcycle.alife.pollenevo.context;

import java.util.EnumSet;
import java.util.Stack;

import org.lightcycle.alife.pollenevo.grid.Coordinate2D;
import org.lightcycle.alife.pollenevo.grid.Grid;

public class Connected {
	public static enum Bound {
		TOP,
		LEFT,
		RIGHT,
		BOTTOM;
	}
	
	public static <T extends Coordinate2D & Context> void determineConnected(Grid<T> grid, EnumSet<Bound> bounds) {
		// Clear connected context flag on all cells
		for (Context context : grid.getItems()) {
			context.setConnected(false);
		}
		
		// Update starting from edges
		T cell;
		if (bounds.contains(Bound.TOP)) {
			for (int x = 0; x < grid.getWidth(); x++) {
				cell = grid.get(x, 0);
				if (cell != null) {
					markConnected(grid, cell);
				}
			}
		}
		if (bounds.contains(Bound.BOTTOM)) {
			for (int x = 0; x < grid.getWidth(); x++) {
				cell = grid.get(x, grid.getHeight() - 1);
				if (cell != null) {
					markConnected(grid, cell);
				}
			}
		}
		if (bounds.contains(Bound.LEFT)) {
			for (int y = 0; y < grid.getHeight(); y++) {
				cell = grid.get(0, y);
				if (cell != null) {
					markConnected(grid, cell);
				}
			}
		}
		if (bounds.contains(Bound.RIGHT)) {
			for (int y = 0; y < grid.getHeight(); y++) {
				cell = grid.get(grid.getWidth() - 1, y);
				if (cell != null) {
					markConnected(grid, cell);
				}
			}
		}
	}
	
	private static <T extends Coordinate2D & Context> void markConnected(Grid<T> grid, T cell) {
		Stack<T> candidates = new Stack<T>();
		T neighbor;
		candidates.push(cell);
		while (!candidates.isEmpty()) {
			cell = candidates.pop();
			if (!cell.isConnected()) {
				cell.setConnected(true);
				neighbor = grid.get(cell.getX() - 1, cell.getY());
				if (neighbor != null) {
					candidates.push(neighbor);
				}
				neighbor = grid.get(cell.getX() + 1, cell.getY());
				if (neighbor != null) {
					candidates.push(neighbor);
				}
				neighbor = grid.get(cell.getX(), cell.getY() - 1);
				if (neighbor != null) {
					candidates.push(neighbor);
				}
				neighbor = grid.get(cell.getX(), cell.getY() + 1);
				if (neighbor != null) {
					candidates.push(neighbor);
				}	
			}			
		}
	}
}

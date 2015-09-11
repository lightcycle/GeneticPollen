package org.lightcycle.alife.geneticpollen.context;

import java.util.EnumSet;
import java.util.Stack;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public class Connected implements ContextRule {
	public static enum Bound {
		TOP,
		LEFT,
		RIGHT,
		BOTTOM;
	}

	private EnumSet<Bound> bounds;

	public Connected(EnumSet<Bound> bounds) {
		this.bounds = bounds;
	}

	@Override
	public void apply(Grid<Cell> grid) {
		// Clear connected context flag on all cells
		for (Cell cell : grid.getItems()) {
			cell.setConnected(false);
		}
		
		// Update starting from edges
		Cell cell;
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
	
	private void markConnected(Grid<Cell> grid, Cell cell) {
		Stack<Cell> candidates = new Stack<Cell>();
		Cell neighbor;
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

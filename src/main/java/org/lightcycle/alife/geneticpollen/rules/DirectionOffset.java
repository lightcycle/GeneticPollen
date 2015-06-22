package org.lightcycle.alife.geneticpollen.rules;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public class DirectionOffset implements Offset {
	
	private final DirectionSource directionSource;
	
	public DirectionOffset(DirectionSource directionSource) {
		this.directionSource = directionSource;
	}
	
	public int getX(Grid<Cell> grid, Cell cell) {
		return directionSource.getDirection(grid, cell).getOffsetX();
	}

	public int getY(Grid<Cell> grid, Cell cell) {
		return directionSource.getDirection(grid, cell).getOffsetY();
	}
	
	public String toString() {
		return "OFFSET" + "(" + directionSource.toString() + ")";
	}
}

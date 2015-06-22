package org.lightcycle.alife.geneticpollen.rules;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public class ConstantDirection implements DirectionSource {
	private Direction direction;
	
	public ConstantDirection(Direction direction) {
		this.direction = direction;
	}

	@Override
	public Direction getDirection(Grid<Cell> grid, Cell cell) {
		return direction;
	}

	public String toString() {
		return direction.toString();
	}
}

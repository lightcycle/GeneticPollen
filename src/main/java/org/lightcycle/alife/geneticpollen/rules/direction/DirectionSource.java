package org.lightcycle.alife.geneticpollen.rules.direction;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;
import org.lightcycle.alife.geneticpollen.rules.direction.Direction;

public interface DirectionSource {
	public Direction getDirection(Grid<Cell> grid, Cell cell);
}

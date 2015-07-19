package org.lightcycle.alife.geneticpollen.rules.scalar;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;
import org.lightcycle.alife.geneticpollen.rules.IntegerSource;
import org.lightcycle.alife.geneticpollen.rules.direction.Direction;

public class NeighborhoodIntegerSensor implements IntegerSource {
	public NeighborhoodIntegerSensor() {
	}
	
	public int getInt(Grid<Cell> grid, Cell cell) {
		int count = 0;
		for (Direction direction : Direction.values()) {
			if (null != grid.get(cell.getX() + direction.getOffsetX(), cell.getY() + direction.getOffsetY())) {
				count++;
			}
		}
		return count;
	}
	
	public String toString() {
		return "NEIGHBOR_COUNT";
	}
}

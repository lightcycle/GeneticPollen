package org.lightcycle.alife.geneticpollen.rules;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public class NeighborhoodDirection implements DirectionSource {
	enum Type {
		MOST_NEIGHBORS,
		MOST_ENERGY;
	}
	
	private Type type;
	
	public NeighborhoodDirection(Type type) {
		this.type = type;
	}

	@Override
	public Direction getDirection(Grid<Cell> grid, Cell cell) {
		double countX = 0, countY = 0;
		for (Direction direction : Direction.values()) {
			Cell neighbor = grid.get(cell.getX() + direction.getOffsetX(), cell.getY() + direction.getOffsetY());
			if (neighbor != null) {
				switch (type) {
				case MOST_ENERGY:
					countX += neighbor.getEnergy() * direction.getOffsetX();
					countY += neighbor.getEnergy() * direction.getOffsetY();
					break;
				case MOST_NEIGHBORS:
					countX += direction.getOffsetX();
					countY += direction.getOffsetY();
					break;
				}
			}
		}
		return Direction.getClosest(countX, countY);
	}

	public String toString() {
		return type.toString();
	}
}

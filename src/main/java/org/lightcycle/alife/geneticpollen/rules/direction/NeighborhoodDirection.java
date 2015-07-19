package org.lightcycle.alife.geneticpollen.rules.direction;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;
import org.lightcycle.alife.geneticpollen.rules.direction.Direction;
import org.lightcycle.alife.geneticpollen.rules.direction.DirectionSource;

public class NeighborhoodDirection implements DirectionSource {
	public enum Type {
		MOST_NEIGHBORS,
		MOST_ENERGY,
		CLOSEST_KINSHIP;
	}
	
	private Type type;
	
	public NeighborhoodDirection(Type type) {
		this.type = type;
	}

	@Override
	public Direction getDirection(Grid<Cell> grid, Cell cell) {
		long countX = 0, countY = 0;
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
				case CLOSEST_KINSHIP:
					countX -= cell.getKinship(neighbor) * direction.getOffsetX();
					countY -= cell.getKinship(neighbor) * direction.getOffsetY();
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

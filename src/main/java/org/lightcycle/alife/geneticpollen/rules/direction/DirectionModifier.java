package org.lightcycle.alife.geneticpollen.rules.direction;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public class DirectionModifier implements DirectionSource {
	public enum Type {
		NONE,
		LEFT45,
		LEFT90,
		LEFT135,
		FLIP,
		RIGHT135,
		RIGHT90,
		RIGHT45;
	}
	
	private DirectionSource directionSource;
	
	private Type type;
	
	public DirectionModifier(DirectionSource directionSource, Type type) {
		this.directionSource = directionSource;
		this.type = type;
	}
	
	@Override
	public Direction getDirection(Grid<Cell> grid, Cell cell) {
		return Direction.values()[(directionSource.getDirection(grid, cell).ordinal() + type.ordinal()) % 8];
	}
	
	public String toString() {
		return type.name() + "(" + directionSource + ")";
	}
}

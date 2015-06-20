package org.lightcycle.alife.geneticpollen.rules;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public class DirectionOffset implements Offset {
	public enum Direction {
		UPLEFT(-1, -1),
		LEFT(-1, 0),
		DOWNLEFT(-1, 1),
		DOWN(0, 1),
		DOWNRIGHT(1, 1),
		RIGHT(1, 0),
		UPRIGHT(1, -1),
		UP(0, -1);
		
		private int offsetX, offsetY;
		
		private Direction(int offsetX, int offsetY) {
			this.offsetX = offsetX;
			this.offsetY = offsetY;
		}

		public int getOffsetX() {
			return offsetX;
		}

		public int getOffsetY() {
			return offsetY;
		}
	}
	
	private final Direction direction;
	
	public DirectionOffset(Direction direction) {
		this.direction = direction;
	}
	
	public int getX(Grid<Cell> grid, Cell cell) {
		return direction.getOffsetX();
	}

	public int getY(Grid<Cell> grid, Cell cell) {
		return direction.getOffsetY();
	}
	
	public String toString() {
		return "OFFSET" + "(" + direction.toString() + ")";
	}
}

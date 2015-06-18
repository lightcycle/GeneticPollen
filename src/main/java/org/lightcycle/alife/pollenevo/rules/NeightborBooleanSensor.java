package org.lightcycle.alife.pollenevo.rules;

import org.lightcycle.alife.pollenevo.Cell;
import org.lightcycle.alife.pollenevo.grid.Grid;

public class NeightborBooleanSensor implements BooleanSource {
	public enum Type {
		NEIGHBOR_LIT,
		NEIGHBOR_CONNECTED;
		
		public String toString() {
			switch(this) {
			case NEIGHBOR_CONNECTED:
				return "NEIGHBOR_CONNECTED?";
			case NEIGHBOR_LIT:
				return "NEIGHBOR_LIT?";
			default:
				return "";
			}
		}
	}
	
	private Type type;
	
	private Offset offset;
	
	public NeightborBooleanSensor(Type type, Offset offset) {
		this.type = type;
		this.offset = offset;
	}
	
	public boolean getBoolean(Grid<Cell> grid, Cell cell) {
		Cell neighbor;
		switch(type) {
		case NEIGHBOR_CONNECTED:
			neighbor = grid.get(cell.getX() + offset.getX(grid, cell), cell.getY() + offset.getY(grid, cell));
			return (neighbor != null) ? neighbor.isConnected() : false; 
		case NEIGHBOR_LIT:
			neighbor = grid.get(cell.getX() + offset.getX(grid, cell), cell.getY() + offset.getY(grid, cell));
			return (neighbor != null) ? neighbor.isLit() : false; 
		default:
			return false;
		}
	}
	
	public String toString() {
		return type.toString() + "(" + offset.toString() + ")";
	}

}

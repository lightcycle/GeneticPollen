package org.lightcycle.alife.pollenevo.rules;

import org.lightcycle.alife.pollenevo.Cell;
import org.lightcycle.alife.pollenevo.grid.Grid;

public class NeighborIntegerSensor implements IntegerSource {
	public enum Type {
		NEIGHBOR_ENERGY,
		KINSHIP;
		
		public String toString() {
			switch(this) {
			case NEIGHBOR_ENERGY:
				return "ENERGY";
			case KINSHIP:
				return "KINSHIP";
			default:
				return "";
			}
		}
	}
	
	final private Type type;
	
	final private Offset offset;
	
	public NeighborIntegerSensor(Type type, Offset offset) {
		this.type = type;
		this.offset = offset;
	}
	
	public int getInt(Grid<Cell> grid, Cell cell) {
		Cell neighbor = grid.get(cell.getX() + offset.getX(grid, cell), cell.getY() + offset.getY(grid, cell));
		switch(type) {
		case NEIGHBOR_ENERGY:
			return (neighbor != null) ? neighbor.getEnergy() : 0; 
		case KINSHIP:
			return (neighbor != null) ? cell.getKinship(neighbor) : Integer.MAX_VALUE;
		default:
			return 0;
		}
	}
	
	public String toString() {
		return type.toString() + "(" + offset.toString() + ")";
	}
}

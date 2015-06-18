package org.lightcycle.alife.pollenevo.rules;

import org.lightcycle.alife.pollenevo.Cell;
import org.lightcycle.alife.pollenevo.grid.Grid;

public class SelfIntegerSensor implements IntegerSource {
	public enum Type {
		ENERGY;;
		
		public String toString() {
			switch(this) {
			case ENERGY:
				return "MY_ENERGY";
			default:
				return "";
			}
		}
	}
	
	final private Type type;
	
	public SelfIntegerSensor(Type type) {
		this.type = type;
	}
	
	public int getInt(Grid<Cell> grid, Cell cell) {
		switch(type) {
		case ENERGY:
			return cell.getEnergy();
		default:
			return 0;
		}
	}
	
	public String toString() {
		return type.toString();
	}
}

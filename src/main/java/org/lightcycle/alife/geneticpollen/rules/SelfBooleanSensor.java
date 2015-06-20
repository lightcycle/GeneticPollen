package org.lightcycle.alife.geneticpollen.rules;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public class SelfBooleanSensor implements BooleanSource {
	public enum Type {
		LIT,
		CONNECTED;
		
		public String toString() {
			switch(this) {
			case CONNECTED:
				return "CONNECTED?";
			case LIT:
				return "LIT?";
			default:
				return "";
			}
		}
	}
	
	private Type type;
	
	public SelfBooleanSensor(Type type) {
		this.type = type;
	}
	
	public boolean getBoolean(Grid<Cell> grid, Cell cell) {
		switch(type) {
		case CONNECTED:
			return cell.isConnected();
		case LIT:
			return cell.isLit();
		default:
			return false;
		}
	}
	
	public String toString() {
		return type.toString();
	}

}

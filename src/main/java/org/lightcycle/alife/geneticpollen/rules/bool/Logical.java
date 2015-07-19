package org.lightcycle.alife.geneticpollen.rules.bool;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;
import org.lightcycle.alife.geneticpollen.rules.bool.BooleanSource;

public class Logical implements BooleanSource {
	public enum Type {
		AND,
		OR,
		XOR;
		
		public String toString() {
			switch(this) {
			case AND:
				return "&";
			case OR:
				return "|";
			case XOR:
				return "^";
			default:
				return "";			
			}
		}
	}
	
	private BooleanSource arg1, arg2;
	
	private Type type;
	
	public Logical(BooleanSource arg1, BooleanSource arg2, Type type) {
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.type = type;
	}
	
	public boolean getBoolean(Grid<Cell> grid, Cell cell) {
		switch(type) {
		case AND:
			return arg1.getBoolean(grid, cell) && arg2.getBoolean(grid, cell);
		case OR:
			return arg1.getBoolean(grid, cell) || arg2.getBoolean(grid, cell);
		case XOR:
			return arg1.getBoolean(grid, cell) ^ arg2.getBoolean(grid, cell);
		default:
			return false;
		}
	}
		
	public String toString() {
		return "(" + arg1.toString() + " " + type.toString() + " " + arg2.toString() + ")";
	}
}

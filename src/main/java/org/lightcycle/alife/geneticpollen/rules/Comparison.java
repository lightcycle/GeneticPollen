package org.lightcycle.alife.geneticpollen.rules;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public class Comparison implements BooleanSource {
	public enum Type {
		LESS,
		GREATER,
		LESS_OR_EQUAL,
		GREATER_OR_EQUAL,
		EQUAL,
		UNEQUAL;
		
		public String toString() {
			switch (this) {
			case EQUAL:
				return "==";
			case GREATER:
				return ">";
			case GREATER_OR_EQUAL:
				return ">=";
			case LESS:
				return "<";
			case LESS_OR_EQUAL:
				return "<=";
			case UNEQUAL:
				return "!=";
			default:
				return "";
			}
		}
	}
	
	private IntegerSource arg1, arg2;
	
	private Type type;
	
	public Comparison(IntegerSource arg1, IntegerSource arg2, Type type) {
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.type = type;
	}
	
	public boolean getBoolean(Grid<Cell> grid, Cell cell) {
		switch(type) {
		case EQUAL:
			return arg1.getInt(grid, cell) == arg2.getInt(grid, cell);
		case GREATER:
			return arg1.getInt(grid, cell) > arg2.getInt(grid, cell);
		case GREATER_OR_EQUAL:
			return arg1.getInt(grid, cell) >= arg2.getInt(grid, cell);
		case LESS:
			return arg1.getInt(grid, cell) < arg2.getInt(grid, cell);
		case LESS_OR_EQUAL:
			return arg1.getInt(grid, cell) <= arg2.getInt(grid, cell);
		case UNEQUAL:
			return arg1.getInt(grid, cell) != arg2.getInt(grid, cell);
		default:
			return false;
		}
	}
	
	public String toString() {
		return "(" + arg1.toString() + " " + type.toString() + " " + arg2.toString() + ")";
	}
}

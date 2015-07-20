package org.lightcycle.alife.geneticpollen.rules.scalar;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public class Math implements IntegerSource {
	public enum Type {
		ADD,
		SUBTRACT,
		MULTIPLY,
		DIVIDE,
		MIN,
		MAX;
		
		public String toString() {
			switch (this) {
			case ADD:
				return "+";
			case DIVIDE:
				return "/";
			case MAX:
				return "max";
			case MIN:
				return "min";
			case MULTIPLY:
				return "*";
			case SUBTRACT:
				return "-";
			default:
				return "";
			}
		}
	}
	
	private IntegerSource arg1, arg2;
	
	private Type type;
	
	public Math(IntegerSource arg1, IntegerSource arg2, Type type) {
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.type = type;
	}
	
	public int getInt(Grid<Cell> grid, Cell cell) {
		switch(type) {
		case ADD:
			return arg1.getInt(grid, cell) + arg2.getInt(grid, cell);
		case DIVIDE:
			int arg1val = arg1.getInt(grid, cell);
			int arg2val = arg2.getInt(grid, cell);
			if (arg2val == 0) {
				if (arg1val == 0) {
					return 0;
				} else {
					return Integer.MAX_VALUE;
				}
			} else {
				return arg1.getInt(grid, cell) / arg2val;				
			}
		case MULTIPLY:
			return arg1.getInt(grid, cell) * arg2.getInt(grid, cell);
		case SUBTRACT:
			return arg1.getInt(grid, cell) - arg2.getInt(grid, cell);
		case MIN:
			return (arg1.getInt(grid, cell) < arg2.getInt(grid, cell)) ? arg1.getInt(grid, cell) : arg2.getInt(grid, cell);
		case MAX:
			return (arg1.getInt(grid, cell) > arg2.getInt(grid, cell)) ? arg1.getInt(grid, cell) : arg2.getInt(grid, cell);
		default:
			return 0;
		}
	}
	
	public String toString() {
		return "(" + arg1.toString() + " " + type.toString() + " " + arg2.toString() + ")";
	}
}

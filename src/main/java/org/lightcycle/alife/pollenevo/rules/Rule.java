package org.lightcycle.alife.pollenevo.rules;

import org.lightcycle.alife.pollenevo.Cell;
import org.lightcycle.alife.pollenevo.action.Action;
import org.lightcycle.alife.pollenevo.grid.Grid;

public class Rule {
	private Action action;
	
	private BooleanSource condition;
	
	public Rule(Action action, BooleanSource condition) {
		this.action = action;
		this.condition = condition;
	}
		
	public boolean apply(Grid<Cell> grid, Cell cell) {
		if (condition.getBoolean(grid, cell)) {
			action.apply(grid, cell);
			return true;
		}
		return false;
	}
	
	public String toString() {
		return "(" + action.toString() + " <- " + condition.toString() + ")";
	}
}

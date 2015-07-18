package org.lightcycle.alife.geneticpollen.rules;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.action.Action;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public class ConditionalAction implements ActionSource {
	private Action action;
	
	private BooleanSource condition;
	
	public ConditionalAction(Action action, BooleanSource condition) {
		this.action = action;
		this.condition = condition;
	}

	@Override
	public Action getAction(Grid<Cell> grid, Cell cell) {
		return (condition.getBoolean(grid, cell))?action:null;
	}

	public String toString() {
		return "(" + action.toString() + " <- " + condition.toString() + ")";
	}
}

package org.lightcycle.alife.geneticpollen.rules.action;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.action.Action;
import org.lightcycle.alife.geneticpollen.grid.Grid;
import org.lightcycle.alife.geneticpollen.rules.action.ActionSource;
import org.lightcycle.alife.geneticpollen.rules.bool.BooleanSource;

public class IfElseAction implements ActionSource {
	private Action action1, action2;

	private BooleanSource condition;

	public IfElseAction(Action action1, Action action2, BooleanSource condition) {
		this.action1 = action1;
		this.action2 = action2;
		this.condition = condition;
	}

	@Override
	public Action getAction(Grid<Cell> grid, Cell cell) {
		return (condition.getBoolean(grid, cell))?action1:action2;
	}

	public String toString() {
		return "(" + action1.toString() + " ? " + action2.toString() + " <- " + condition.toString() + ")";
	}
}

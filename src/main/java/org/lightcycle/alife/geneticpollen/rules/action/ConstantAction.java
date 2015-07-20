package org.lightcycle.alife.geneticpollen.rules.action;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public class ConstantAction implements ActionSource {
    private Action action;

    public ConstantAction(Action action) {
        this.action = action;
    }

    @Override
    public Action getAction(Grid<Cell> grid, Cell cell) {
        return action;
    }

    public String toString() {
        return action.toString();
    }
}

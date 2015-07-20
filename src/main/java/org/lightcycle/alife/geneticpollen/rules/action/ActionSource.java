package org.lightcycle.alife.geneticpollen.rules.action;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public interface ActionSource {
    public Action getAction(Grid<Cell> grid, Cell cell);
}

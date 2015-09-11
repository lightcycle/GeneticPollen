package org.lightcycle.alife.geneticpollen.context;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public interface ContextRule {
    void apply(Grid<Cell> grid);
}

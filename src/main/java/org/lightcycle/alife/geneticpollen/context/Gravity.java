package org.lightcycle.alife.geneticpollen.context;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.Settings;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public class Gravity implements ContextRule {
    @Override
    public void apply(Grid<Cell> grid) {
        for (Cell cell : grid.getItems()) {
            if (!cell.isConnected()) {
                grid.move(cell, 0, 1);
            }
        }
    }
}

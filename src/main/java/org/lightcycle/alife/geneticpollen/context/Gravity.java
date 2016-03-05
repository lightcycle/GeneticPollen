package org.lightcycle.alife.geneticpollen.context;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.Settings;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public class Gravity implements ContextRule {
    @Override
    public void apply(Grid<Cell> grid) {
        grid.getItems().stream()
                .filter(cell -> !cell.isConnected())
                .forEach(cell -> grid.move(cell, 0, 1));
    }
}

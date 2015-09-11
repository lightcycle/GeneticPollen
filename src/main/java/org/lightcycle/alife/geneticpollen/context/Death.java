package org.lightcycle.alife.geneticpollen.context;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public class Death implements ContextRule {
    @Override
    public void apply(Grid<Cell> grid) {
        Cell[] cells = grid.getItems().toArray(new Cell[0]);
        for (Cell cell : cells) {
            if (cell.getEnergy() <= 0) {
                grid.remove(cell);
            }
        }
    }
}

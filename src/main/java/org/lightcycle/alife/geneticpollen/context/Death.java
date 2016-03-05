package org.lightcycle.alife.geneticpollen.context;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;

import java.util.stream.Collectors;

public class Death implements ContextRule {
    @Override
    public void apply(Grid<Cell> grid) {
        grid.getItems().stream()
                .filter(cell -> cell.getEnergy() <= 0)
                .collect(Collectors.toList())
                .forEach(cell -> grid.remove(cell));
    }
}

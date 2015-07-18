package org.lightcycle.alife.geneticpollen.action;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;
import org.lightcycle.alife.geneticpollen.rules.IntegerSource;

public class ProgramAction implements Action {
    private int jump;

    public ProgramAction(int jump) {
        this.jump = jump;
    }

    @Override
    public void apply(Grid<Cell> grid, Cell cell) {
        cell.moveProgramStep(jump);
    }
}

package org.lightcycle.alife.geneticpollen.action;

import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;
import org.lightcycle.alife.geneticpollen.rules.Offset;

public class ReproduceAction implements Action {
	private Offset offset;
	
	public ReproduceAction(Offset offset) {
		this.offset = offset;
	}
	
	public boolean apply(Grid<Cell> grid, Cell cell) {
		return cell.reproduce(grid, cell.getX() + offset.getX(grid, cell), cell.getY() + offset.getY(grid, cell));
	}
	
	public String toString() {
		return "Reproduce(" + offset + ")";
	}
}

package org.lightcycle.alife.pollenevo.action;

import org.lightcycle.alife.pollenevo.Cell;
import org.lightcycle.alife.pollenevo.Settings;
import org.lightcycle.alife.pollenevo.grid.Grid;
import org.lightcycle.alife.pollenevo.rules.Offset;

public class MoveAction implements Action {
	private Offset offset;
	
	public MoveAction(Offset offset) {
		this.offset = offset;
	}
	
	public boolean apply(Grid<Cell> grid, Cell cell) {
		if (grid.move(cell, offset.getX(grid, cell), offset.getY(grid, cell))) {
			if (!cell.isConnected() && offset.getY(grid, cell) < 0) {
				cell.setEnergy(cell.getEnergy() - Settings.FLY_ENERGY_COST);				
			} else {
				cell.setEnergy(cell.getEnergy() - Settings.MOVE_ENERGY_COST);				
			}
			return true;
		}
		return false;
	}
	
	public String toString() {
		return "Move(" + offset + ")";
	}
}

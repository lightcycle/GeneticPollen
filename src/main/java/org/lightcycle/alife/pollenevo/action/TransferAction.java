package org.lightcycle.alife.pollenevo.action;

import org.lightcycle.alife.pollenevo.Cell;
import org.lightcycle.alife.pollenevo.grid.Grid;
import org.lightcycle.alife.pollenevo.rules.IntegerSource;
import org.lightcycle.alife.pollenevo.rules.Offset;

import static java.lang.Math.min;
import static java.lang.Math.max;

public class TransferAction implements Action {
	private Offset offset;
	
	private IntegerSource energySource;
	
	public TransferAction(Offset offset, IntegerSource energySource) {
		this.offset = offset;
		this.energySource = energySource;
	}
	
	public boolean apply(Grid<Cell> grid, Cell cell) {
		Cell neighbor = grid.get(cell.getX() + offset.getX(grid, cell), cell.getY() + offset.getY(grid, cell));
		if (neighbor != null) {
			int amount = energySource.getInt(grid, cell);
			
			if (amount > 0) {
				amount = min(amount, cell.getEnergy());
			}
			
			if (amount < 0) {
				amount = max(amount, -neighbor.getEnergy());
			}
			
			cell.setEnergy(cell.getEnergy() - amount);
			neighbor.setEnergy(neighbor.getEnergy() + amount);
			return true;
		}
		return false;
	}
	
	public String toString() {
		return "Transfer(" + offset + ", " + energySource.toString() + ")";
	}
}

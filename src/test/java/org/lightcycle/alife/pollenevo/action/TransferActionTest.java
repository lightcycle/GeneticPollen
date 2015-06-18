package org.lightcycle.alife.pollenevo.action;

import static org.junit.Assert.*;

import org.junit.Test;
import org.lightcycle.alife.pollenevo.Cell;
import org.lightcycle.alife.pollenevo.action.Action;
import org.lightcycle.alife.pollenevo.action.TransferAction;
import org.lightcycle.alife.pollenevo.grid.Grid;
import org.lightcycle.alife.pollenevo.grid.Grid.WrapMode;
import org.lightcycle.alife.pollenevo.rules.DirectionOffset;
import org.lightcycle.alife.pollenevo.rules.DirectionOffset.Direction;
import org.lightcycle.alife.pollenevo.rules.IntegerConstant;

public class TransferActionTest {
	@Test
	public void testZeroShare() {
		Grid<Cell> grid = new Grid<Cell>(5, 5, WrapMode.NONE);
		Cell cell = new Cell(2, 2, 100, null, null, null, null);
		Cell neighbor = new Cell(1, 2, 0, null, null, null, null);
		grid.add(cell);
		grid.add(neighbor);
		Action transfer = new TransferAction(new DirectionOffset(Direction.LEFT), new IntegerConstant(0));
		transfer.apply(grid, cell);
		assertEquals(100, cell.getEnergy());
		assertEquals(0, neighbor.getEnergy());
	}
	
	@Test
	public void testNormalShare() {
		Grid<Cell> grid = new Grid<Cell>(5, 5, WrapMode.NONE);
		Cell cell = new Cell(2, 2, 100, null, null, null, null);
		Cell neighbor = new Cell(1, 2, 0, null, null, null, null);
		grid.add(cell);
		grid.add(neighbor);
		Action transfer = new TransferAction(new DirectionOffset(Direction.LEFT), new IntegerConstant(10));
		transfer.apply(grid, cell);
		assertEquals(90, cell.getEnergy());
		assertEquals(10, neighbor.getEnergy());
	}

	@Test
	public void testExcessiveShare() {
		Grid<Cell> grid = new Grid<Cell>(5, 5, WrapMode.NONE);
		Cell cell = new Cell(2, 2, 100, null, null, null, null);
		Cell neighbor = new Cell(1, 2, 0, null, null, null, null);
		grid.add(cell);
		grid.add(neighbor);
		Action transfer = new TransferAction(new DirectionOffset(Direction.LEFT), new IntegerConstant(1000));
		transfer.apply(grid, cell);
		assertEquals(0, cell.getEnergy());
		assertEquals(100, neighbor.getEnergy());
	}

	@Test
	public void testNormalSteal() {
		Grid<Cell> grid = new Grid<Cell>(5, 5, WrapMode.NONE);
		Cell cell = new Cell(2, 2, 100, null, null, null, null);
		Cell neighbor = new Cell(1, 2, 100, null, null, null, null);
		grid.add(cell);
		grid.add(neighbor);
		Action transfer = new TransferAction(new DirectionOffset(Direction.LEFT), new IntegerConstant(-10));
		transfer.apply(grid, cell);
		assertEquals(110, cell.getEnergy());
		assertEquals(90, neighbor.getEnergy());
	}
	
	@Test
	public void testExcessiveSteal() {
		Grid<Cell> grid = new Grid<Cell>(5, 5, WrapMode.NONE);
		Cell cell = new Cell(2, 2, 100, null, null, null, null);
		Cell neighbor = new Cell(1, 2, 100, null, null, null, null);
		grid.add(cell);
		grid.add(neighbor);
		Action transfer = new TransferAction(new DirectionOffset(Direction.LEFT), new IntegerConstant(-1000));
		transfer.apply(grid, cell);
		assertEquals(200, cell.getEnergy());
		assertEquals(0, neighbor.getEnergy());
	}

}

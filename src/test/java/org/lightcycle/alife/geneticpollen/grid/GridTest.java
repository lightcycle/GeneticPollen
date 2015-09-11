package org.lightcycle.alife.geneticpollen.grid;

import org.junit.Test;
import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid.WrapMode;

import junit.framework.TestCase;
import org.lightcycle.alife.geneticpollen.testutil.CellUtil;

public class GridTest extends TestCase {
	@Test
	public void testAddAndGet() {
		Grid<Cell> grid = new Grid<>(3, 3, WrapMode.NONE);
		Cell cell = CellUtil.createCell(1, 1);
		
		// Cell added where expected
		assertTrue(grid.add(cell));
		assertFalse(grid.add(CellUtil.createCell(1, 1)));
		assertSame(grid.get(1, 1), cell);
		
		// Cell not found where not expected
		assertEquals(null, grid.get(2, 1));
	}
	
	@Test
	public void testMove() {
		Grid<Cell> grid = new Grid<>(3, 3, WrapMode.NONE);
		Cell cell = CellUtil.createCell(1, 1);
		grid.add(cell);
		
		// Cell added where expected
		assertEquals(cell, grid.get(1, 1));
		assertEquals(null, grid.get(2, 1));
		
		// Cell moved
		assertTrue(grid.move(cell, 1, 0));
		assertEquals(null, grid.get(1, 1));
		assertSame(cell, grid.get(2, 1));
		
		// Cell can't move off grid
		assertFalse(grid.move(cell, 1, 0));
		assertSame(cell, grid.get(2, 1));
		
		// Cell can't move where another cell exists
		assertTrue(grid.add(CellUtil.createCell(1, 1)));
		assertFalse(grid.move(cell, -1, 0));
		assertSame(cell, grid.get(2, 1));
	}
	
	@Test
	public void testMoveWrapped() {
		Grid<Cell> grid = new Grid<>(3, 3, WrapMode.XY);
		Cell cell = CellUtil.createCell(2, 1);
		grid.add(cell);
		
		// Cell added where expected
		assertEquals(grid.get(2, 1), cell);
		assertEquals(grid.get(0, 1), null);
		
		// Cell moved
		assertTrue(grid.move(cell, 1, 0));
		assertEquals(grid.get(2, 1), null);
		assertSame(grid.get(0, 1), cell);
	}
}
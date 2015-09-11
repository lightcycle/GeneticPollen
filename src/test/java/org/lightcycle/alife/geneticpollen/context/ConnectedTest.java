package org.lightcycle.alife.geneticpollen.context;

import java.util.EnumSet;

import junit.framework.TestCase;

import org.junit.Test;
import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;
import org.lightcycle.alife.geneticpollen.grid.Grid.WrapMode;
import org.lightcycle.alife.geneticpollen.testutil.CellUtil;

public class ConnectedTest extends TestCase {
	private final Connected connected = new Connected(EnumSet.of(Connected.Bound.BOTTOM));

	@Test
	public void testConnected() {
		// _____
		// _XXX_
		// _X_X_
		// X__X_
		// ___X_
		Grid<Cell> grid = new Grid<>(5, 5, WrapMode.XY);
		grid.add(CellUtil.createCell(3, 4));
		grid.add(CellUtil.createCell(3, 3));
		grid.add(CellUtil.createCell(3, 2));
		grid.add(CellUtil.createCell(3, 1));
		grid.add(CellUtil.createCell(2, 1));
		grid.add(CellUtil.createCell(1, 1));
		grid.add(CellUtil.createCell(1, 2));
		grid.add(CellUtil.createCell(0, 3));
		
		connected.apply(grid);
		
		// Cells listed correctly
		assertTrue(grid.get(3, 4).isConnected());
		assertTrue(grid.get(3, 3).isConnected());
		assertTrue(grid.get(3, 2).isConnected());
		assertTrue(grid.get(3, 1).isConnected());
		assertTrue(grid.get(2, 1).isConnected());
		assertTrue(grid.get(1, 1).isConnected());
		assertTrue(grid.get(1, 2).isConnected());
		assertFalse(grid.get(0, 3).isConnected());
	}
}

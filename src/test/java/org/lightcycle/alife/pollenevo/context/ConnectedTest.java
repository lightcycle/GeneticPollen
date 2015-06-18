package org.lightcycle.alife.pollenevo.context;

import java.util.EnumSet;

import junit.framework.TestCase;

import org.junit.Test;
import org.lightcycle.alife.pollenevo.CellStub;
import org.lightcycle.alife.pollenevo.context.Connected;
import org.lightcycle.alife.pollenevo.grid.Grid;
import org.lightcycle.alife.pollenevo.grid.Grid.WrapMode;

public class ConnectedTest extends TestCase {
	@Test
	public void testConnected() {
		// _____
		// _XXX_
		// _X_X_
		// X__X_
		// ___X_
		Grid<CellStub> grid = new Grid<CellStub>(5, 5, WrapMode.XY);
		grid.add(new CellStub(3, 4));
		grid.add(new CellStub(3, 3));
		grid.add(new CellStub(3, 2));
		grid.add(new CellStub(3, 1));
		grid.add(new CellStub(2, 1));
		grid.add(new CellStub(1, 1));
		grid.add(new CellStub(1, 2));
		grid.add(new CellStub(0, 3));
		
		Connected.determineConnected(grid, EnumSet.of(Connected.Bound.BOTTOM));
		
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

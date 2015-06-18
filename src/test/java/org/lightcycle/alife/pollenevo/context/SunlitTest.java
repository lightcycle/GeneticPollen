package org.lightcycle.alife.pollenevo.context;

import junit.framework.TestCase;

import org.junit.Test;
import org.lightcycle.alife.pollenevo.CellStub;
import org.lightcycle.alife.pollenevo.context.Sunlit;
import org.lightcycle.alife.pollenevo.grid.Grid;
import org.lightcycle.alife.pollenevo.grid.Grid.WrapMode;

public class SunlitTest extends TestCase {
	@Test
	public void testSunlit() {
		// ___
		// _X_
		// XX_
		Grid<CellStub> grid = new Grid<CellStub>(3, 3, WrapMode.XY);
		grid.add(new CellStub(1, 1));
		grid.add(new CellStub(0, 2));
		grid.add(new CellStub(1, 2));
		
		Sunlit.determineSunlit(grid);
		
		// Cells listed correctly
		assertTrue(grid.get(1, 1).isLit());
		assertTrue(grid.get(0, 2).isLit());
		assertFalse(grid.get(1, 2).isLit());
	}
}

package org.lightcycle.alife.geneticpollen.context;

import junit.framework.TestCase;

import org.junit.Test;
import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;
import org.lightcycle.alife.geneticpollen.grid.Grid.WrapMode;
import org.lightcycle.alife.geneticpollen.testutil.CellUtil;

public class SunlitTest extends TestCase {
	private final Sunlit sunlit = new Sunlit();

	@Test
	public void testSunlit() {
		// ___
		// _X_
		// XX_
		Grid<Cell> grid = new Grid<>(3, 3, WrapMode.XY);
		grid.add(CellUtil.createCell(1, 1));
		grid.add(CellUtil.createCell(0, 2));
		grid.add(CellUtil.createCell(1, 2));

		sunlit.apply(grid);
		
		// Cells listed correctly
		assertTrue(grid.get(1, 1).isLit());
		assertTrue(grid.get(0, 2).isLit());
		assertFalse(grid.get(1, 2).isLit());
	}
}

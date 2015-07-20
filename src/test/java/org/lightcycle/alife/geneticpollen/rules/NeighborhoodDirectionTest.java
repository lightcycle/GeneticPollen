package org.lightcycle.alife.geneticpollen.rules;

import static org.junit.Assert.*;

import org.junit.Test;
import org.lightcycle.alife.geneticpollen.Cell;
import org.lightcycle.alife.geneticpollen.grid.Grid;
import org.lightcycle.alife.geneticpollen.grid.Grid.WrapMode;
import org.lightcycle.alife.geneticpollen.rules.direction.NeighborhoodDirection;
import org.lightcycle.alife.geneticpollen.rules.direction.NeighborhoodDirection.Type;
import org.lightcycle.alife.geneticpollen.rules.direction.Direction;
import org.lightcycle.alife.geneticpollen.rules.direction.DirectionSource;

public class NeighborhoodDirectionTest {
	@Test
	public void testMostNeighborsUp() {
		Grid<Cell> grid = new Grid<Cell>(3, 3, WrapMode.NONE);
		Cell cell = new Cell(1, 1, 0, null, null, null, null);
		grid.add(new Cell(0, 0, 0, null, null, null, null));
		grid.add(new Cell(2, 0, 0, null, null, null, null));
		DirectionSource directionSource = new NeighborhoodDirection(Type.MOST_NEIGHBORS);
		
		assertEquals(Direction.UP, directionSource.getDirection(grid, cell));
	}
	
	@Test
	public void testMostNeighborsLeft() {
		Grid<Cell> grid = new Grid<Cell>(3, 3, WrapMode.NONE);
		Cell cell = new Cell(1, 1, 0, null, null, null, null);
		grid.add(new Cell(0, 1, 0, null, null, null, null));
		DirectionSource directionSource = new NeighborhoodDirection(Type.MOST_NEIGHBORS);
		
		assertEquals(Direction.LEFT, directionSource.getDirection(grid, cell));
	}
}

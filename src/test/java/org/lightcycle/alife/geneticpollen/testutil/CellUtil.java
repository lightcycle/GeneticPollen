package org.lightcycle.alife.geneticpollen.testutil;

import org.lightcycle.alife.geneticpollen.Cell;

public class CellUtil {
    public static Cell createCell(int x, int y) {
        return new Cell(x, y, 10, null, new int[]{0, 0, 0}, null, null);
    }
}

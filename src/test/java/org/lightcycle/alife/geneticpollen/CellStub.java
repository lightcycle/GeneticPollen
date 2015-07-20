package org.lightcycle.alife.geneticpollen;

import org.lightcycle.alife.geneticpollen.context.Connectable;
import org.lightcycle.alife.geneticpollen.context.Lightable;
import org.lightcycle.alife.geneticpollen.grid.Coordinate2D;

public class CellStub implements Coordinate2D, Connectable, Lightable {

	private int x, y, energy;
	
	private boolean lit, connected;
	
	public CellStub(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	@Override
	public boolean isLit() {
		return lit;
	}

	@Override
	public void setLit(boolean lit) {
		this.lit = lit;
	}

	@Override
	public boolean isConnected() {
		return connected;
	}

	@Override
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
}

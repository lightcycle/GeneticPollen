package org.lightcycle.alife.pollenevo.context;

public interface Context {
	public int getEnergy();
	
	public void setEnergy(int energy);
	
	public boolean isLit();
	
	public void setLit(boolean lit);
	
	public boolean isConnected();
	
	public void setConnected(boolean connected);	
}

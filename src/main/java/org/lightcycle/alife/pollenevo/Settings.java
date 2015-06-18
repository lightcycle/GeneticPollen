package org.lightcycle.alife.pollenevo;

import java.util.EnumSet;

import org.lightcycle.alife.pollenevo.context.Connected.Bound;
import org.lightcycle.alife.pollenevo.grid.Grid.WrapMode;

public class Settings {
	public static final int WIDTH = 700;
	public static final int HEIGHT = 300;
	public static final int NUMCELLS = 10000;
	public static final int GENOME_SIZE = 100;
	public static final int INITIAL_ENERGY = 100;
	public static final int MOVE_ENERGY_COST = 5;
	public static final int FLY_ENERGY_COST = 50;
	public static final int BIRTH_COST = 75;
	public static final int AGE_ENERGY_COST = 1;
	public static final int SUNLIT_ENERGY_GAIN = 20;
	public static final double MUTATION_PROB = 0.002;
	public static final double MUTATION_RATE = 0.25;
	public static final EnumSet<Bound> BOUNDRIES = EnumSet.of(Bound.BOTTOM);
//	public static final EnumSet<Bound> BOUNDRIES = EnumSet.noneOf(Bound.class);
	public static final boolean GRAVITY = true;
	public static final WrapMode WRAPMODE = WrapMode.X;
}

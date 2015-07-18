package org.lightcycle.alife.geneticpollen;

import java.util.EnumSet;

import org.lightcycle.alife.geneticpollen.context.Connected.Bound;
import org.lightcycle.alife.geneticpollen.grid.Grid.WrapMode;

public class Settings {
	public static final long FPS = 60L;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;
	public static final int NUMCELLS = 500;
	public static final int GENOME_SIZE = 1000;
	public static final int INITIAL_ENERGY = 1000;
	public static final int MOVE_ENERGY_COST = 5;
	public static final int FLY_ENERGY_COST = 50;
	public static final int BIRTH_COST = 75;
	public static final int AGE_ENERGY_COST = 1;
	public static final int SUNLIT_ENERGY_GAIN = 6;
	public static final double MUTATION_PROB = 0.01;
	public static final double MUTATION_RATE = 0.2;
	public static final EnumSet<Bound> BOUNDRIES = EnumSet.of(Bound.BOTTOM);
	public static final boolean GRAVITY = true;
	public static final WrapMode WRAPMODE = WrapMode.X;
}

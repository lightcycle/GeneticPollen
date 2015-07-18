package org.lightcycle.alife.geneticpollen;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;

import org.lightcycle.alife.geneticpollen.action.Action;
import org.lightcycle.alife.geneticpollen.context.Context;
import org.lightcycle.alife.geneticpollen.genetics.PhenotypeProvider;
import org.lightcycle.alife.geneticpollen.genetics.PhenotypeProviderException;
import org.lightcycle.alife.geneticpollen.genetics.Genomes.Genome;
import org.lightcycle.alife.geneticpollen.grid.Coordinate2D;
import org.lightcycle.alife.geneticpollen.grid.Grid;
import org.lightcycle.alife.geneticpollen.rules.ActionSource;
import org.lightcycle.alife.geneticpollen.rules.ConditionalAction;

public class Cell implements Coordinate2D, Context
{
	private int x, y, energy;
	
	private boolean lit, connected;
	
	private ActionSource[] program;

	private int programStep = 0;
	
	private int[] color;
	
	private Genome genome;
	
	private Random random;
	
	private PhenotypeProvider phenotypeProvider;
	
	private static Map<Genome, List<ActionSource>> programCache;
	
	private ActionSource[] getRules(Genome genome) {
		if (genome == null || phenotypeProvider == null) {
			return null;
		}
		
		if (programCache == null) {
			programCache = new WeakHashMap<Genome, List<ActionSource>>();
		}
		
		List<ActionSource> program = programCache.get(genome);
		if (program == null) {
			program = new LinkedList<ActionSource>();
			Iterator<Integer> genomeInput = genome.iterator();
			while(true) {
				try {
					program.add(phenotypeProvider.getInstance(genomeInput, ActionSource.class));
				} catch (PhenotypeProviderException exception) {
					break;
				}
			}
			programCache.put(genome, program);
		}
		
		return program.toArray(new ActionSource[0]);
	}
	
	public Cell(int x, int y, int energy, Genome genome, int[] color, Random random, PhenotypeProvider phenotypeProvider) {
		this.x = x;
		this.y = y;
		this.energy = energy;
		this.color = color;
		this.genome = genome;
		this.phenotypeProvider = phenotypeProvider;
		this.random = random;
		this.program = getRules(genome);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public boolean isLit() {
		return lit;
	}

	public void setLit(boolean lit) {
		this.lit = lit;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	
	public int[] getColor() {
		return color;
	}
	
	public void update(Grid<Cell> grid, Cell cell) {
		if (lit) {
			energy += Settings.SUNLIT_ENERGY_GAIN;
		}
		if (program != null) {
			Action action = program[programStep].getAction(grid, cell);
			if (action != null) {
				action.apply(grid, cell);
			}
		}
		energy -= Settings.AGE_ENERGY_COST;
		if (energy <= 0) {
			grid.remove(this);
		}
	}
	
	public boolean reproduce(Grid<Cell> grid, int x, int y) {
		if (grid.get(x, y) == null && energy >= (Settings.BIRTH_COST + 2)) {
			energy -= Settings.BIRTH_COST;
			Cell child = null;
			if (random.nextDouble() < Settings.MUTATION_PROB) {
				// mutate
				child = new Cell(x, y, energy / 2, genome.getMutant(Settings.MUTATION_RATE, random), Util.color(random.nextInt(128) + 127, random.nextInt(128) + 127, random.nextInt(128) + 127), random, phenotypeProvider);
			} else {
				// copy
				child = new Cell(x, y, energy / 2, genome, color, random, phenotypeProvider);
			}
			grid.add(child);
			energy -= child.energy;
			return true;
		}
		return false;
	}

	public void moveProgramStep(int delta) {
		programStep += delta;
		while (programStep < 0) programStep += program.length;
		if (programStep >= program.length) programStep %= program.length;
	}
	
	public int getKinship(Cell cell) {
		return genome.getKinship(cell.genome);
	}
}
package org.lightcycle.alife.pollenevo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;

import org.lightcycle.alife.pollenevo.context.Context;
import org.lightcycle.alife.pollenevo.genetics.Genomes.Genome;
import org.lightcycle.alife.pollenevo.genetics.PhenotypeProvider;
import org.lightcycle.alife.pollenevo.genetics.PhenotypeProviderException;
import org.lightcycle.alife.pollenevo.grid.Coordinate2D;
import org.lightcycle.alife.pollenevo.grid.Grid;
import org.lightcycle.alife.pollenevo.rules.Rule;

public class Cell implements Coordinate2D, Context
{
	private int x, y, energy;
	
	private boolean lit, connected;
	
	private List<Rule> rules;
	
	private int[] color;
	
	private Genome genome;
	
	private Random random;
	
	private PhenotypeProvider phenotypeProvider;
	
	private static Map<Genome, List<Rule>> ruleCache;
	
	private List<Rule> getRules(Genome genome) {
		if (genome == null || phenotypeProvider == null) {
			return null;
		}
		
		if (ruleCache == null) {
			ruleCache = new WeakHashMap<Genome, List<Rule>>();
		}
		
		List<Rule> rules = ruleCache.get(genome);
		if (rules == null) {
			rules = new LinkedList<Rule>();
			Iterator<Integer> genomeInput = genome.iterator();
			while(true) {
				try {
					rules.add(phenotypeProvider.getInstance(genomeInput, Rule.class));
				} catch (PhenotypeProviderException exception) {
					break;
				}
			}
			ruleCache.put(genome, rules);
		}
		
		return rules;
	}
	
	public Cell(int x, int y, int energy, Genome genome, int[] color, Random random, PhenotypeProvider phenotypeProvider) {
		this.x = x;
		this.y = y;
		this.energy = energy;
		this.color = color;
		this.genome = genome;
		this.phenotypeProvider = phenotypeProvider;
		this.random = random;
		this.rules = getRules(genome);
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
		if (rules != null) {			
			for (Rule rule : rules) {
				if (rule.apply(grid, cell)) {
					break;
				}
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
	
	public int getKinship(Cell cell) {
		return genome.getKinship(cell.genome);
	}
}
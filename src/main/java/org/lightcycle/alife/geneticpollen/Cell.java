package org.lightcycle.alife.geneticpollen;

import java.util.*;

import org.lightcycle.alife.geneticpollen.rules.action.Action;
import org.lightcycle.alife.geneticpollen.genetics.PhenotypeProvider;
import org.lightcycle.alife.geneticpollen.genetics.PhenotypeProviderException;
import org.lightcycle.alife.geneticpollen.genetics.Genomes.Genome;
import org.lightcycle.alife.geneticpollen.grid.Coordinate2D;
import org.lightcycle.alife.geneticpollen.grid.Grid;
import org.lightcycle.alife.geneticpollen.rules.action.ActionSource;
import org.lightcycle.alife.geneticpollen.rules.direction.Offset;
import org.lightcycle.alife.geneticpollen.rules.scalar.IntegerSource;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Cell implements Coordinate2D
{
	private int x, y, energy;
	
	private boolean lit, connected;
	
	private ActionSource[] program;

	private int programStep = 0;
	
	private int[] color;
	
	private Genome genome;
	
	private Random random;
	
	private PhenotypeProvider phenotypeProvider;
	
	private static Map<Genome, ActionSource[]> programCache = new WeakHashMap<>();
	
	private ActionSource[] getRules(Genome genome) {
		if (genome == null || phenotypeProvider == null) {
			return null;
		}

		return programCache.computeIfAbsent(genome, g -> {
			Iterator<Integer> genomeInput = g.iterator();
			List<ActionSource> actionSources = new LinkedList<ActionSource>();
			while (true) {
				try {
					actionSources.add(phenotypeProvider.getInstance(genomeInput, ActionSource.class));
				} catch (PhenotypeProviderException exception) {
					break;
				}
			}
			return actionSources.toArray(new ActionSource[0]);
		});
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
	
	public void update(Grid<Cell> grid, ListIterator<Cell> cellIter) {
		if (lit) {
			energy += Settings.SUNLIT_ENERGY_GAIN;
		}
		if (program != null && program.length > 0) {
			Action action = program[programStep].getAction(grid, this);
			if (action != null) {
				action.apply(grid, this, cellIter);
			}
		}
		energy -= Settings.AGE_ENERGY_COST;
	}
	
	public int getKinship(Cell cell) {
		return genome.getKinship(cell.genome);
	}

	public static class MoveAction implements Action {
        private Offset offset;

        public MoveAction(Offset offset) {
            this.offset = offset;
        }

        public void apply(Grid<Cell> grid, Cell cell, ListIterator<Cell> cellIter) {
            if (grid.move(cell, offset.getX(grid, cell), offset.getY(grid, cell))) {
                if (!cell.connected && offset.getY(grid, cell) < 0) {
					cell.energy -= Settings.FLY_ENERGY_COST;
                } else {
					cell.energy -= Settings.MOVE_ENERGY_COST;
                }
            }
        }

        public String toString() {
            return "Move(" + offset + ")";
        }
    }

	public static class ProgramAction implements Action {
        private int jump;

        public ProgramAction(int jump) {
            this.jump = jump;
        }

        @Override
        public void apply(Grid<Cell> grid, Cell cell, ListIterator<Cell> cellIter) {
			cell.programStep += jump;
			while (cell.programStep < 0) cell.programStep += cell.program.length;
			if (cell.programStep >= cell.program.length) cell.programStep %= cell.program.length;
        }
    }

	public static class ReproduceAction implements Action {
        private Offset offset;

        public ReproduceAction(Offset offset) {
            this.offset = offset;
        }

        public void apply(Grid<Cell> grid, Cell cell, ListIterator<Cell> cellIter) {
			int x = cell.x + offset.getX(grid, cell);
			int y = cell.y + offset.getY(grid, cell);

			if (grid.get(x, y) == null && cell.energy >= (Settings.BIRTH_COST + 2)) {
				cell.energy -= Settings.BIRTH_COST;
				Cell child = null;
				if (cell.random.nextDouble() < Settings.MUTATION_PROB) {
					// mutate
					child = new Cell(x, y, cell.energy / 2, cell.genome.getMutant(Settings.MUTATION_RATE, cell.random), Util.color(cell.random.nextInt(128) + 127, cell.random.nextInt(128) + 127, cell.random.nextInt(128) + 127), cell.random, cell.phenotypeProvider);
				} else {
					// copy
					child = new Cell(x, y, cell.energy / 2, cell.genome, cell.color, cell.random, cell.phenotypeProvider);
				}
				grid.add(child, cellIter);
				cell.energy -= child.energy;
			}
		}

        public String toString() {
            return "Reproduce(" + offset + ")";
        }
    }

	public static class TransferAction implements Action {
        private Offset offset;

        private IntegerSource energySource;

        public TransferAction(Offset offset, IntegerSource energySource) {
            this.offset = offset;
            this.energySource = energySource;
        }

        public void apply(Grid<Cell> grid, Cell cell, ListIterator<Cell> cellIter) {
            Cell neighbor = grid.get(cell.x + offset.getX(grid, cell), cell.y + offset.getY(grid, cell));
            if (neighbor != null) {
                int amount = energySource.getInt(grid, cell);

                if (amount > 0) {
                    amount = min(amount, cell.energy);
                }

                if (amount < 0) {
                    amount = max(amount, -neighbor.energy);
                }

                cell.energy -= amount;
                neighbor.energy += amount;
            }
        }

        public String toString() {
            return "Transfer(" + offset + ", " + energySource.toString() + ")";
        }
    }
}
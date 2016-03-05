package org.lightcycle.alife.geneticpollen;

import org.lightcycle.alife.geneticpollen.context.*;
import org.lightcycle.alife.geneticpollen.genetics.Genomes;
import org.lightcycle.alife.geneticpollen.genetics.PhenotypeProvider;
import org.lightcycle.alife.geneticpollen.grid.Grid;

import java.awt.image.WritableRaster;
import java.util.*;

public class Simulation {
    private final Random random;

    private final Grid<Cell> grid;

    private final List<ContextRule> contextRulesBefore;

    private final List<ContextRule> contextRulesAfter;

    public Simulation() {
        this.grid = new Grid<Cell>(Settings.WIDTH, Settings.HEIGHT, Settings.WRAPMODE);
        this.random = new Random();
        this.contextRulesBefore = new LinkedList<>();
        contextRulesBefore.add(new Death());
        if (Settings.SUNLIT_ENERGY_GAIN > 0) {
            contextRulesBefore.add(new Sunlit());
        }
        if (!Settings.BOUNDRIES.isEmpty()) {
            contextRulesBefore.add(new Connected(Settings.BOUNDRIES));
        }
        this.contextRulesAfter = new LinkedList<>();
        if (Settings.GRAVITY) {
            contextRulesAfter.add(new Gravity());
        }
        Genomes genomes = new Genomes();
        PhenotypeProvider phenotypeProvider = new PhenotypeProvider(Settings.ROOT_PACKAGE);
        for (int i = 0; i < Settings.NUMCELLS; ) {
            if (grid.add(new Cell(random.nextInt(Settings.WIDTH), random.nextInt(Settings.HEIGHT), Settings.INITIAL_ENERGY, genomes.createRandomGenome(Settings.GENOME_SIZE, random), Util.color(random.nextInt(128) + 127, random.nextInt(128) + 127, random.nextInt(128) + 127), random, phenotypeProvider))) {
                i++;
            }
        }
    }

    public void step() {
        for (ContextRule contextRule : contextRulesBefore) {
            contextRule.apply(grid);
        }
        Collections.shuffle(grid.getItems());
        for (ContextRule contextRule : contextRulesAfter) {
            contextRule.apply(grid);
        }
        for (ListIterator<Cell> cellIter = grid.getItems().listIterator(); cellIter.hasNext(); ) {
            Cell cell = cellIter.next();
            cell.update(grid, cellIter);
        }
    }

    public void draw(WritableRaster raster) {
        grid.getItems().stream().forEach(cell -> raster.setPixel(cell.getX(), cell.getY(), cell.getColor()));
    }
}

package org.lightcycle.alife.geneticpollen;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.swing.JPanel;

import org.lightcycle.alife.geneticpollen.context.Connected;
import org.lightcycle.alife.geneticpollen.context.Sunlit;
import org.lightcycle.alife.geneticpollen.genetics.Genomes;
import org.lightcycle.alife.geneticpollen.genetics.PhenotypeProvider;
import org.lightcycle.alife.geneticpollen.grid.Grid;

public class World extends JPanel implements Runnable {
	private int width, height;
			
	private static Random random;
	private int[] background;
	private Grid<Cell> grid;
	
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		background = new int[3*width*height];
		Arrays.fill(background,0);
		grid = new Grid<Cell>(width, height, Settings.WRAPMODE);
		random = new Random();
		seed();
	}
	
	private BufferedImage image;
	
	private WritableRaster raster;
	
	private long lastUpdateNanos = 0L;
	
	private static final long FPS60NANOS = 1000000000L / Settings.FPS;
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (image == null) {
			image = (BufferedImage) createImage(width, height);
		}
		
		if (raster == null) {
			raster = image.getRaster();
		}
		
		raster.setPixels(0,0,image.getWidth(),image.getHeight(),background);
		Cell[] cellArray = grid.getItems().toArray(new Cell[0]);
		for (Cell cell : cellArray) {
			if (cell == null) continue;
			raster.setPixel(cell.getX(), cell.getY(), cell.getColor());				
		}
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);			
	}

	public void run() {
		while (true) {
			if (Settings.SUNLIT_ENERGY_GAIN > 0) {
				Sunlit.determineSunlit(grid);				
			}
			if (!Settings.BOUNDRIES.isEmpty()) {
				Connected.determineConnected(grid, Settings.BOUNDRIES);				
			}
			Collections.shuffle(grid.getItems());
			Cell[] cellArray = grid.getItems().toArray(new Cell[0]);
			for (Cell cell : cellArray) {
				if (Settings.GRAVITY) {					
					if (!cell.isConnected()) {
						grid.move(cell, 0, 1);
					}
				}
				cell.update(grid, cell);
			}
			
			long nowNanos = System.nanoTime();
			if (nowNanos - lastUpdateNanos > FPS60NANOS) {
				lastUpdateNanos = nowNanos;
				repaint();
			}
		}
	}
	
	private void seed() {
		Genomes genomes = new Genomes();
		PhenotypeProvider phenotypeProvider = new PhenotypeProvider("org.lightcycle.alife.geneticpollen");
		for (int i = 0; i < Settings.NUMCELLS; ) {
			if (grid.add(new Cell(random.nextInt(width), random.nextInt(height), Settings.INITIAL_ENERGY, genomes.createRandomGenome(Settings.GENOME_SIZE, random), Util.color(World.random.nextInt(128) + 127, World.random.nextInt(128) + 127, World.random.nextInt(128) + 127), random, phenotypeProvider))) {
				i++;				
			}
		}
	}
}

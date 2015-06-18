package org.lightcycle.alife.pollenevo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.swing.JPanel;

import org.lightcycle.alife.pollenevo.context.Connected;
import org.lightcycle.alife.pollenevo.context.Sunlit;
import org.lightcycle.alife.pollenevo.genetics.Genomes;
import org.lightcycle.alife.pollenevo.genetics.PhenotypeProvider;
import org.lightcycle.alife.pollenevo.grid.Grid;

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
			repaint();
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
		}
	}
	
	private void seed() {
		Genomes genomes = new Genomes();
		PhenotypeProvider phenotypeProvider = new PhenotypeProvider("org.lightcycle.alife.pollenevo");
		for (int i = 0; i < Settings.NUMCELLS; ) {
			if (grid.add(new Cell(random.nextInt(width), random.nextInt(height), Settings.INITIAL_ENERGY, genomes.createRandomGenome(Settings.GENOME_SIZE, random), Util.color(World.random.nextInt(128) + 127, World.random.nextInt(128) + 127, World.random.nextInt(128) + 127), random, phenotypeProvider))) {
				i++;				
			}
		}
	}
}

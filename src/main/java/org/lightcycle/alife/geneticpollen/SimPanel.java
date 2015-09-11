package org.lightcycle.alife.geneticpollen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Arrays;

public class SimPanel extends JPanel implements Runnable {
	private int[] background;

	private final Simulation simulation;

	public SimPanel() {
		background = new int[3*Settings.WIDTH*Settings.HEIGHT];
		Arrays.fill(background,0);
		simulation = new Simulation();
	}
	
	private BufferedImage image;
	
	private WritableRaster raster;
	
	private long lastUpdateNanos = 0L;
	
	private static final long FPS60NANOS = 1000000000L / Settings.FPS;
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (image == null) {
			image = (BufferedImage) createImage(Settings.WIDTH, Settings.HEIGHT);
		}
		
		if (raster == null) {
			raster = image.getRaster();
		}
		
		raster.setPixels(0,0,image.getWidth(),image.getHeight(),background);
		simulation.draw(raster);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

	public void run() {
		while (true) {
			simulation.step();
			long nowNanos = System.nanoTime();
			if (nowNanos - lastUpdateNanos > FPS60NANOS) {
				lastUpdateNanos = nowNanos;
				repaint();
			}
		}
	}
}

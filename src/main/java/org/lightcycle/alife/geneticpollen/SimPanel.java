package org.lightcycle.alife.geneticpollen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Arrays;

public class SimPanel extends JPanel implements Runnable {
	private final int[] background = new int[3*Settings.WIDTH*Settings.HEIGHT];

	private final Simulation simulation = new Simulation();

	private final BufferedImage image = new BufferedImage(Settings.WIDTH, Settings.HEIGHT, BufferedImage.TYPE_INT_RGB);

	private final WritableRaster raster = image.getRaster();

	private long lastUpdateNanos = 0L;
	
	private static final long FPS60NANOS = 1000000000L / Settings.FPS;
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

	public void run() {
		while (true) {
			simulation.step();
			long nowNanos = System.nanoTime();
			if (nowNanos - lastUpdateNanos > FPS60NANOS) {
				lastUpdateNanos = nowNanos;
				raster.setPixels(0,0,image.getWidth(),image.getHeight(),background);
				simulation.draw(raster);
				repaint();
			}
		}
	}
}

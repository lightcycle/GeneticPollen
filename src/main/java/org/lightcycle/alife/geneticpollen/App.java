package org.lightcycle.alife.geneticpollen;

import javax.swing.*;

public class App extends JFrame {
	public static void main(String[] args) {
		new App();
	}
	
	public App() {
		SimPanel world = new SimPanel();
		add(world);
		new Thread(world).start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Genetic Pollen");
		setResizable(false);
		setVisible(true);
		setSize(Settings.WIDTH, Settings.HEIGHT);
	}
}


package org.lightcycle.alife.pollenevo;

import javax.swing.*;

public class App extends JFrame {
	public static void main(String[] args) {
		JFrame window = new App();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("PollenEvo");
		window.setResizable(false);
		window.setVisible(true);
		window.setSize(Settings.WIDTH, Settings.HEIGHT);
	}
	
	public App() {
		World world = new World(Settings.WIDTH, Settings.HEIGHT);
		add(world);
		new Thread(world).start();
	}
}
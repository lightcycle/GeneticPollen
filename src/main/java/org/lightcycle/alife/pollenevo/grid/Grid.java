package org.lightcycle.alife.pollenevo.grid;

import java.util.ArrayList;
import java.util.List;

public class Grid<T extends Coordinate2D> {
	public enum WrapMode {
		NONE,
		X,
		Y,
		XY;
	}
		
	private int width, height;
	
	private List<T> items;
	
	private Object[][] grid;
	
	private WrapMode wrapMode;
	
	public Grid(int width, int height, WrapMode wrapMode) {
		this.width = width;
		this.height = height;
		this.wrapMode = wrapMode;
		items = new ArrayList<T>();
		grid = new Object[width][height];
	}
	
	public boolean add(T item) {
		item.setX(wrapX(item.getX()));
		item.setY(wrapY(item.getY()));
		if (!isOffGrid(item.getX(), item.getY()) && grid[item.getX()][item.getY()] == null) {
			grid[item.getX()][item.getY()] = item;
			items.add(item);
			return true;
		} else {
			return false;
		}
	}
	
	public void remove(T item) {
		grid[item.getX()][item.getY()] = null;
		items.remove(item);
	}
	
	@SuppressWarnings("unchecked")
	public T get(int x, int y) {
		x = wrapX(x);
		y = wrapY(y);
		return isOffGrid(x, y) ? null : (T)grid[x][y];
	}
	
	public boolean move(T item, int dx, int dy) {
		int x = wrapX(item.getX() + dx);
		int y = wrapY(item.getY() + dy);
		if (isOffGrid(x, y) || grid[x][y] != null) {
			return false;
		} else {
			grid[item.getX()][item.getY()] = null;
			grid[x][y] = item;
			item.setX(x);
			item.setY(y);
			return true;
		}
	}
	
	public List<T> getItems() {
		return items;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
		
	private int wrapX(int x) {
		if (wrapMode == WrapMode.X || wrapMode == WrapMode.XY) {
			while (x < 0) x += width;
			while (x >= width) x -= width;
		}
		return x;
	}
	
	private int wrapY(int y) {
		if (wrapMode == WrapMode.Y || wrapMode == WrapMode.XY) {
			while (y < 0) y += height;
			while (y >= height) y -= height;
		}
		return y;
	}
	
	private boolean isOffGrid(int x, int y) {
		return x < 0 || y < 0 || x >= width || y >= height;
	}
}

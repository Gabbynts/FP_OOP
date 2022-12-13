package model;

import javafx.scene.paint.Color;

public abstract class Elements {
	private Color color;
	protected double x;
	protected double y;
	
	public void Shape() {
		color = Color.WHITE;
	}
	
	public abstract void down();
	
	public void move() {
		x += 2.00;
		y += 2.00;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}




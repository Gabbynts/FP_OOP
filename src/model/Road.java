package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Road extends Rectangle{
	
	private Rectangle rectangle;
	int initialPos;
	int destinationPos;
	int restartPos;
	
	public Road (int x, int y, int width, int height) {
		setWidth(width);
		setHeight(height);
		setTranslateX(x);
		setTranslateY(y);
		setFill(Color.WHITE);
		
		initialPos = y;
		destinationPos = 400 - initialPos;
		restartPos = -1*(50 + initialPos);
	}
	
}

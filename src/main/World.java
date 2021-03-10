package main;

import constants.Constants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class World {
	private int posX;
	private int posY;
	private int h, w, r, g, b;
	private double opacity;
	
	public World() {
		posX = Constants.RAND.nextInt(Constants.SCREEN_WIDTH);
		posY = 0;
		w = Constants.RAND.nextInt(5) + 1;
		h =  Constants.RAND.nextInt(5) + 1;
		r = Constants.RAND.nextInt(100) + 150;
		g = Constants.RAND.nextInt(100) + 150;
		b = Constants.RAND.nextInt(100) + 150;
		opacity = Constants.RAND.nextFloat();
		if(opacity < 0) opacity *=-1;
		if(opacity > 0.5) opacity = 0.5;
	}
	
	public void draw(GraphicsContext gc) {
		if(opacity > 0.8) opacity-=0.01;
		if(opacity < 0.1) opacity+=0.01;
		gc.setFill(Color.rgb(r, g, b, opacity));
		gc.fillOval(posX, posY, w, h);
		posY+=15;
	}
	
	public int getPosY() {
		return posY;
	}
}
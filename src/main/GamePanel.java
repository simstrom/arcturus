package main;

import states.GameModel;
import javafx.scene.canvas.Canvas;

/**
 * GamePanel utilizes the Canvas class to supply the Game States with
 * GraphicsContext for drawing objects.
 * 
 */
public class GamePanel extends Canvas {

	private GameModel model;

	public GamePanel(final GameModel model, int width, int height) {
		this.model = model;
		this.setWidth(width);
		this.setHeight(height);
	}

	public void repaint() {
		model.draw(getGraphicsContext2D());
	}
}

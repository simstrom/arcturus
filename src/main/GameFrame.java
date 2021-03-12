package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import states.GameModel;

/**
 * This class Wraps a StackPane to organize contents in the gameScene.
 *
 * GameFrame has the job of creating the game panel, and adding it to itself in
 * order for it to show.
 *
 */
public class GameFrame extends StackPane {
	private GamePanel g;

	public GameFrame(GameModel model, int width, int height) {
		g = new GamePanel(model, width, height);
		this.getChildren().add(g);
	}

	public void repaint() {
		g.repaint();
	}

	public Canvas getCanvas() {
		return g;
	}
}

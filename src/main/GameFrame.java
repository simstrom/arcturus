package main;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import states.GameModel;

/**
 * This class Wraps a HBox: A HBox is a second level JavaFX container used
 * organize contents in the window (Stage/Scene).
 *
 * The GameFrame, in this case, has the job of creating the game panel, and
 * adding it to itself in order for it to show.
 *
 */
public class GameFrame extends StackPane {
	private GamePanel g;

	public GameFrame(GameModel model, int width, int height) {
		// Create a new GamePanel and add's it to the frame
		g = new GamePanel(model, width, height);
		this.getChildren().add(g);
	}

	public void repaint() {
		g.repaint();
	}
	
	public Canvas getCanvas( ) {
		return g;
	}
}

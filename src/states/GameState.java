package states;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import main.GameFrame;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import environment.World;

/**
 * This class represents a state of the game. GameState is an abstract class
 * that defines general functions that are used to delegate to the current state
 * such as handle user input, rendering, and updating logic.
 * 
 */

public abstract class GameState {
	protected Group contents = new Group();
	protected GameModel model;
	protected ArrayList<World> universe = new ArrayList<>();

	public GameState(GameModel model) {
		this.model = model;
		contents.getStylesheets().add(Constants.STYLESHEET);
	}

	public abstract void update();

	public abstract void draw(GraphicsContext g);

	public abstract void init(GameFrame frame);

	public abstract void mouseClicked(MouseEvent event);

	public abstract void handleInput(List<String> input);

	public void drawBg(GraphicsContext g, Color color) {
		g.setFill(color);
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
	}

	public abstract void activate(GameFrame frame);

	public abstract void deactivate(GameFrame frame);
}

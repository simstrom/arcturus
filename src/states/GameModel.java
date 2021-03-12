package states;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import main.GameFrame;

/**
 * This class represents the current state of the game. It also are responsible
 * for keeping track of game essential data such as highscore and current level.
 *
 */
public class GameModel {
	private GameFrame frame;
	private GameState currentState;
	private int score = 0;
	private ArrayList<Integer> highScoreList = new ArrayList<>();
	private int level = 1;
	private boolean gameOver = false;

	public GameModel() {
		// We start out in the MenuState.
		this.currentState = new MenuState(this);

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("highscore.game")));
			highScoreList = (ArrayList<Integer>) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Switch to a new state, stored in the 'state' reference.
	 *
	 * This will call 'deactivate' on the current state, then store the new state as
	 * the current state, and finally call 'activate' on the new current state.
	 */
	public void switchState(GameState nextState) {
		currentState.deactivate(frame);
		currentState = nextState;
		currentState.activate(frame);
	}

	/**
	 * Delegates the keyPress from GamePanel to the current state
	 * 
	 * @param key
	 */
//	public void keyPressed(KeyEvent key) {
//		currentState.keyPressed(key);
//	}

	public void mouseClicked(MouseEvent event) {
		currentState.mouseClicked(event);
	}

	public void handleInput(List<String> input) {
		currentState.handleInput(input);
	}

	/**
	 * The update function is called every iteration of the game loop. it's usually
	 * used to update the games logic e.g. objects position, velocity, etc...
	 */
	public void update() {
		currentState.update();
	}

	/**
	 * @param g Graphics object passed from GamePanel This function delegates
	 *          drawing from the GamePanel to the current state
	 */
	public void draw(GraphicsContext g) {
		currentState.draw(g);
	}

	public Integer getScore() {
		return score;
	}

	public void increaseScore() {
		score++;
	}

	public void resetScore() {
		highScoreList.add(score);
		Collections.sort(highScoreList);
		if (highScoreList.size() > 3) {
			highScoreList.remove(0);
		}
		score = 0;
		level = 1;
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("highscore.game")));
			out.writeObject(this.highScoreList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean getGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public ArrayList<Integer> getHighScore() {
		return highScoreList;
	}

	public void increaseLevel() {
		level++;
	}

	public int getLevel() {
		return level;
	}

	public void setFrame(GameFrame frame) {
		this.frame = frame;
		currentState.init(frame);
	}
}

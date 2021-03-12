package environment;

import constants.Constants;
import constants.Images;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import sprites.Player;
import states.GameModel;

/**
 * Handles the printing of the UI in PlayState.
 * 
 * @author simonnystrom
 *
 */
public class PlayUI {
	private GameModel model;
	private Player player;

	public PlayUI(GameModel model, Player player) {
		this.model = model;
		this.player = player;
	}

	public void draw(GraphicsContext g) {
		g.setTextAlign(TextAlignment.CENTER);
		g.setFont(Constants.miscFont);
		g.setFill(Color.BLACK);
		g.fillRect(0, 0, Constants.SCREEN_WIDTH, 70);
		g.setFill(Color.WHITE);
		g.fillText("Score: " + model.getScore(), 70, 50);
		g.drawImage(Images.PLAYER_LIFE, 350, 30);
		g.fillText("X " + player.getLives(), 410, 50);
		g.fillText("Level: " + model.getLevel(), 730, 50);
		if (model.getGameOver())
			drawGameOver(g);
	}

	public void drawGameOver(GraphicsContext g) {
		g.setFont(Constants.mainFont);
		g.setFill(Color.SLATEBLUE);
		if ((model.getHighScore() == null) || (model.getScore() > model.getHighScore().get(2))) {
			g.fillText("NEW \n HIGHSCORE!", Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 3.2);
		} else {
			g.fillText("Game Over!", Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2.5);
		}
		g.setFont(Constants.subFont);
		g.setFill(Color.WHITE);
		g.fillText("Your Score \n" + model.getScore(), Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
		g.setFont(Constants.miscFont);
		g.fillText("Press SPACE to continue", Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 1.5);
	}

	public void drawAmmo(GraphicsContext g, int ammoCounter) {
		g.fillText("RAPID FIRE AMMO LEFT\n" + (ammoCounter) + " / " + Constants.POWER_AMMO, 360, 720);
		g.drawImage(Images.RAPID_BADGE, 25, 80);
	}

}

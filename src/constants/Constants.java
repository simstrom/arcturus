package constants;

import java.util.Random;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Declaration of global constants.
 */
public interface Constants {
	String GAME_TITLE = "ARCTURUS";
	int SCREEN_WIDTH = 800;
	int SCREEN_HEIGHT = 800;
	String STYLESHEET = "style.css";

	String PLAY = "PLAY";
	String SCORE = "SCORE";
	String QUIT = "QUIT";

	Font titleFont = Font.font("Futura", FontWeight.BOLD, 120);
	Font mainFont = Font.font("Futura", FontWeight.BOLD, 84);
	Font subFont = Font.font("Futura", FontWeight.LIGHT, 24);
	Font miscFont = Font.font("Futura", FontWeight.LIGHT, 18);

	Random RAND = new Random();
	int PLAYER_SIZE = 60;
	int MAX_AMMO = 10;
	int POWER_AMMO = MAX_AMMO * 3;
	int MAX_ENEMIES = 7;
	int MAX_METEORS = 5;

	int EXPLOSION_W = 128;
	int EXPLOSION_ROWS = 3;
	int EXPLOSION_COL = 3;
	int EXPLOSION_H = 128;
	int EXPLOSION_STEPS = 15;
}
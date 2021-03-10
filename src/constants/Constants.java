package constants;

import java.util.Random;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * In this file we define some global constants.
 *
 * An interface is one way to store variables that are truly constants and not
 * subject to change during run time.
 *
 * Please note that the problem with global variables is that anyone can change
 * them whenever. This makes it extremely hard to reason about your code. But
 * for constants, this is not a problem since we cannot change them, and
 * therefore they stay the same during the entire execution of the program.
 */
public interface Constants {
	String GAME_TITLE = "ARCTURUS";
	int SCREEN_WIDTH = 800;
	int SCREEN_HEIGHT = 1000;
	String STYLESHEET = "style.css";
	
	String PLAY = "PLAY";
	String SCORE = "SCORE";
	String QUIT = "QUIT";
	
	Font titleFont = Font.font( "Futura", FontWeight.BOLD, 120 );
	Font mainFont = Font.font( "Futura", FontWeight.BOLD, 84 );
	Font subFont = Font.font( "Futura", FontWeight.LIGHT, 24 );
	Font miscFont = Font.font( "Futura", FontWeight.LIGHT, 18 );
	
	Random RAND = new Random();
	int PLAYER_SIZE = 60;
	int MAX_AMMO = 10;
	int POWER_AMMO = MAX_AMMO * 2;
	int MAX_ENEMIES = 10;
	
	int EXPLOSION_W = 128;
	int EXPLOSION_ROWS = 3;
	int EXPLOSION_COL = 3;
	int EXPLOSION_H = 128;
	int EXPLOSION_STEPS = 15;
}

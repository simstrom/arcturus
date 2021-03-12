package sprites;

import constants.Constants;
import constants.Images;
import states.GameModel;

/**
 * One enemy-type which moves on the Y-axis in a downward direction with a
 * individual(faster) speed. Spawns from the top of the screen.
 * 
 * @author simonnystrom
 *
 */
public class Ship extends Enemy {

	public Ship(GameModel model) {
		super(model);
		setSpeed((model.getScore() / 20) + 1);
		setPosition(50 + Constants.RAND.nextInt(Constants.SCREEN_WIDTH - 100), 0);
		setImage(Images.ENEMIES[Constants.RAND.nextInt(Images.ENEMIES.length)]);
	}

	@Override
	public void update() {
		super.update();
		if (!exploding && !destroyed) {
			setPosY(posY += speed);
		}
		if (posY > Constants.SCREEN_HEIGHT) {
			destroyed = true;
		}
	}
}

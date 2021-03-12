package sprites;

import constants.Constants;
import constants.Images;
import states.GameModel;

/**
 * One enemy-type which moves on the X-axis in a sideways direction with a
 * individual(slower) speed. Spawns from either the left or right of the screen
 * based on randomness.
 * 
 * @author simonnystrom
 *
 */
public class Meteor extends Enemy {
	int spawn;

	public Meteor(GameModel model) {
		super(model);
		setSpeed((model.getScore() / 50) + 1);
		spawn = Constants.RAND.nextInt(2);
		if (spawn == 1)
			spawn = posX = Constants.SCREEN_WIDTH;
		setPosition(spawn, Constants.RAND.nextInt(Constants.SCREEN_HEIGHT - 60));
		setImage(Images.METEORS[Constants.RAND.nextInt(Images.METEORS.length)]);
	}

	@Override
	public void update() {
		super.update();
		if (!exploding && !destroyed) {
			if (spawn == 0)
				setPosX(posX += speed);
			if (spawn == Constants.SCREEN_WIDTH)
				setPosX(posX -= speed);
		}
		if (spawn == 0 && posX > Constants.SCREEN_WIDTH)
			destroyed = true;
		if (spawn == Constants.SCREEN_WIDTH && posX < 0)
			destroyed = true;
	}
}

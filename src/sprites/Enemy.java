package sprites;

import states.GameModel;

/**
 * Superclass to individual enemy classes. Holds general information about the
 * enemy such as speed and position.
 * 
 * @author simonnystrom
 *
 */
public abstract class Enemy extends Sprite {
	protected int speed;
	protected int posY = 0;
	protected int posX = 0;

	public Enemy(GameModel model) {
		super(model);
	}

	@Override
	public void update() {
		super.update();
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}

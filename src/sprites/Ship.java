package sprites;

import constants.Constants;
import constants.Images;
import states.GameModel;

public class Ship extends Enemy {

	public Ship(GameModel model) {
		super(model);
		setPosition(50 + Constants.RAND.nextInt(Constants.SCREEN_WIDTH - 100), 0);
		setImage(Images.ENEMIES[Constants.RAND.nextInt(Images.ENEMIES.length)]);
	}

	@Override
	public void setVelocity(double x, double y) {
		speed = (int) y;		
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

//	@Override
//	public Enemy newEnemy(GameModel model) {
//		Enemy newEnemy = new Ship(model);
//		newEnemy.setPosition(50 + Constants.RAND.nextInt(Constants.SCREEN_WIDTH - 100), 0);
//		newEnemy.setImage(Images.ENEMIES[Constants.RAND.nextInt(Images.ENEMIES.length)]);
//		return newEnemy;
//	}

}

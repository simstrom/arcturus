package sprites;

import constants.Constants;
import constants.Images;
import states.GameModel;

public class Meteor extends Enemy {
	int spawn;

	public Meteor(GameModel model) {
		super(model);
		spawn = Constants.RAND.nextInt(2);
		System.out.println(spawn);
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

	@Override
	public void setVelocity(double x, double y) {
		this.speed = (int) x;		
	}

//	@Override
//	public Enemy newEnemy(GameModel model) {
//		Enemy newEnemy = new Meteor(model);
//		newEnemy.setPosition(50 + Constants.RAND.nextInt(Constants.SCREEN_WIDTH - 100), 0);
//		newEnemy.setImage(Images.METEORS[Constants.RAND.nextInt(Images.METEORS.length)]);
//		return newEnemy;
//	}

}

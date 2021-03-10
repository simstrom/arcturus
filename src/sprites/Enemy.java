package sprites;

import java.util.ArrayList;

import constants.Constants;
import constants.Images;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.util.Duration;
import states.GameModel;

public abstract class Enemy extends Sprite {
	protected int speed = ((model.getScore() / 20) + 1);
	protected int posY = 0;
	protected int posX = 0;
	private ArrayList<Projectile> enemyLaserList = new ArrayList<>();

	public Enemy(GameModel model) {
		super(model);

//		Timeline enemyLasers = new Timeline (new KeyFrame(Duration.seconds(1), e -> enemyShoot()));
//			enemyLasers.setCycleCount(Timeline.INDEFINITE);
//			enemyLasers.play();
	}

//	public abstract Enemy newEnemy(GameModel model);

	@Override
	public void update() {
		super.update();
	}

	public void increaseSpeed() {
		speed++;
	}

	@Override
	public abstract void setVelocity(double x, double y);

	@Override
	public Projectile shoot() {
		return new EnemyProjectile(model, posX, posY);
	}
}

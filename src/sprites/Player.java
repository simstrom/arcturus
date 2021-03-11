package sprites;

import constants.Constants;
import states.GameModel;

public class Player extends Sprite {
	private int posX;
	private int posY;
	private double velocityX;
	private double velocityY;
	private int lives = 3;
	private boolean hasShield;
	private boolean hasRapidFire;

	public Player(GameModel model) {
		super(model);
		this.posX = 360;
		this.posY = 800;
		this.velocityX = 0;
		this.velocityY = 0;
	}

	public void setVelocity(double x, double y) {
		velocityX = x;
		velocityY = y;
	}

	public void addVelocity(double x, double y) {
		velocityX += x;
		velocityY += y;
	}

	@Override
	public void update() {
		super.update();
		setPosX(posX += velocityX);
		if (posX > Constants.SCREEN_WIDTH - Constants.PLAYER_SIZE) {
			posX = Constants.SCREEN_WIDTH - Constants.PLAYER_SIZE;
		} else if (posX < 0) {
			setPosX(0);
		}
		setPosY(posY += velocityY);
		if (posY > Constants.SCREEN_HEIGHT - Constants.PLAYER_SIZE) {
			posY = Constants.SCREEN_HEIGHT - Constants.PLAYER_SIZE;
		} else if (posY < 0) {
			setPosY(0);
		}
	}

	public boolean intersects(PowerUp powerUp) {
		return powerUp.getBoundary().intersects(this.getBoundary());
	}

	@Override
	public Projectile shoot() {
		return new PlayerProjectile(model, posX, posY, hasRapidFire);
	}

	public void takeDamage() {
		if (lives == 1) {
			lives--;
			this.explode();
		} else {
			setPosition(posX = 360, posY = 800);
			lives--;

		}
	}

	public int getLives() {
		return lives;
	}

	public void setShield(boolean value) {
		this.hasShield = value;
	}

	public boolean hasShield() {
		return hasShield;
	}

	public void setRapidFire(boolean value) {
		this.hasRapidFire = value;
	}

	public boolean hasRapidFire() {
		return hasRapidFire;
	}
}

package sprites;

import constants.Constants;
import states.GameModel;
import states.Sound;

public class Player extends Sprite {
	private int posX;
	private int posY;
	private double velocityX;
	private double velocityY;
	private int lives = 3;
	private boolean hasShield;
	private boolean hasRapidFire;
	private Sound laser = new Sound("resources/laser.mp3", 0.05);
	private Sound lose = new Sound("resources/lose.mp3", 0.5);
	private Sound shieldUp = new Sound("resources/shieldup.mp3", 0.5);
	private Sound shieldDown = new Sound("resources/shielddown.mp3", 0.5);
	private Sound rapidUp = new Sound("resources/rapidfire.wav", 0.5);



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
		laser.play();
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
		lose.play();
	}

	public int getLives() {
		return lives;
	}

	public void setShield(boolean value) {
		this.hasShield = value;
		if (value == true) {
			shieldUp.play();
		} else {
			shieldDown.play();
		}
	}

	public boolean hasShield() {
		return hasShield;
	}

	public void setRapidFire(boolean value) {
		this.hasRapidFire = value;
		if (value == true)
			rapidUp.play();
	}

	public boolean hasRapidFire() {
		return hasRapidFire;
	}
}

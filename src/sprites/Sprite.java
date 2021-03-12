package sprites;

import constants.Constants;
import constants.Images;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import states.GameModel;
import states.Sound;

public abstract class Sprite {
	protected GameModel model;
	private Image image;
	protected int posX;
	private int posY;
	private int width;
	private int height;
	protected boolean exploding;
	protected boolean destroyed;
	private int explosionStep = 0;
	
	private Sound explosion = new Sound("resources/explosion.wav", 0.1);

	public Sprite(GameModel model) {
		this.model = model;
	}

	public void setImage(Image i) {
		image = i;
		width = Constants.PLAYER_SIZE;
		height = Constants.PLAYER_SIZE;
	}

	public void setImage(String filename) {
		Image i = new Image(filename);
		setImage(i);
	}

	public void setPosition(int x, int y) {
		posX = x;
		posY = y;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getPosX() {
		return posX;
	}

	public abstract void setVelocity(double x, double y);

	public void update() {
		if (exploding) {
			explosionStep++;
			destroyed = explosionStep > Constants.EXPLOSION_STEPS;
		}
	}

	public void render(GraphicsContext gc) {
		if (exploding) {
			gc.drawImage(Images.EXPLOSION, explosionStep % Constants.EXPLOSION_COL * Constants.EXPLOSION_COL,
					(explosionStep / Constants.EXPLOSION_ROWS) * Constants.EXPLOSION_H + 1, Constants.EXPLOSION_W,
					Constants.EXPLOSION_H, posX, posY, Constants.PLAYER_SIZE, Constants.PLAYER_SIZE);
		} else {
			gc.drawImage(image, posX, posY, Constants.PLAYER_SIZE, Constants.PLAYER_SIZE);
		}
	}

	public Rectangle2D getBoundary() {
		return new Rectangle2D(posX, posY, width, height);
	}

	public boolean intersects(Sprite s) {
		return s.getBoundary().intersects(this.getBoundary());
	}

	public abstract Projectile shoot();

	public void explode() {
		exploding = true;
		explosionStep = -1;
		explosion.play();
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public boolean isExploding() {
		return exploding;
	}
}

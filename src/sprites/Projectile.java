package sprites;

import constants.Constants;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

/**
 * Superclass of all projectiles to easily add enemy projectiles in the future.
 * Holds general responsibilities for projectiles such as checking intersections
 * and position.
 * 
 * @author simonnystrom
 *
 */
public abstract class Projectile {
	protected double posX;
	protected double posY;
	protected int speed = 10;

	public Projectile(double posX, double posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public double getPosY() {
		return posY;
	}

	public abstract void update();

	public abstract void render(GraphicsContext g);

	public Rectangle2D getBoundary() {
		return new Rectangle2D(posX, posY, Constants.PLAYER_SIZE, Constants.PLAYER_SIZE);
	}

	public boolean instersects(Sprite s) {
		return s.getBoundary().intersects(this.getBoundary());
	}
}

package sprites;

import constants.Constants;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import states.GameModel;

public abstract class Projectile {
	protected GameModel model;
	protected double posX;
	public double posY;
	protected int speed = 10;

	public Projectile(GameModel model, double posX, double posY) {
		this.model = model;
		this.posX = posX;
		this.posY = posY;
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

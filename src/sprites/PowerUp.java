package sprites;

import constants.Constants;
import constants.Images;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Superclass of all power-ups, making it easier to add new abilities in the
 * future. Responsible for drawing it to the map when dropped and checking if
 * being picked up by the player.
 * 
 * @author simonnystrom
 *
 */
public abstract class PowerUp {
	private Image image;
	private int posX;
	private int posY;
	private double width;
	private double height;

	public PowerUp(int posX, int posY, Image image) {
		this.image = image;
		this.posX = posX;
		this.posY = posY;
		this.width = image.getWidth();
		this.height = image.getHeight();
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(image, posX, posY);
	}

	public void renderActive(GraphicsContext gc, int posX, int posY) {
		gc.drawImage(Images.ACTIVE_SHIELD, posX - 15, posY - 20, Constants.PLAYER_SIZE + 30,
				Constants.PLAYER_SIZE + 30);
		gc.drawImage(Images.SHIELD_BADGE, 25, 120);
	}

	public Rectangle2D getBoundary() {
		return new Rectangle2D(posX, posY, width, height);
	}

	public boolean intersects(Sprite s) {
		return s.getBoundary().intersects(this.getBoundary());
	}
}

package sprites;

import constants.Images;
import javafx.scene.canvas.GraphicsContext;

/**
 * Renders the specified player laser projectile, decreasing its position on the
 * Y-axis for upward momentum with speed based on standard or power.
 * 
 * @author simonnystrom
 *
 */
public class PlayerProjectile extends Projectile {
	private int powerSize = 30;
	private boolean power = false;

	public PlayerProjectile(double posX, double posY, boolean power) {
		super(posX, posY);
		this.power = power;
	}

	public void update() {
		posY -= speed;
	}

	@Override
	public void render(GraphicsContext g) {
		if (power) {
			speed = 50;
			g.drawImage(Images.PLAYER_POWER_LASER, posX + 15, posY - 75, powerSize, powerSize + 60);
		} else {
			g.drawImage(Images.PLAYER_LASER, posX + 25, posY - 20);
		}
	}

}

package sprites;

import constants.Images;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Shield extends PowerUp {

	public Shield(int posX, int posY, Image image) {
		super(posX, posY, image);
	}

	public void renderShield(GraphicsContext gc, int posX, int posY) {
		gc.drawImage(Images.ACTIVE_SHIELD, posX, posY);
	}
}

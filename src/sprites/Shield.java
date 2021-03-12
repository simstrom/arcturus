package sprites;

import javafx.scene.image.Image;

public class Shield extends PowerUp {
	private boolean active;

	public Shield(int posX, int posY, Image image) {
		super(posX, posY, image);
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}
}

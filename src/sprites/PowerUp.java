package sprites;

import constants.Constants;
import constants.Images;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import states.GameModel;

public class PowerUp {
	private GameModel model;
	private Image image;
	private int posX;
	private int posY;
	private double width;
	private double height;
	protected boolean activeShield;

	public PowerUp(int posX, int posY, Image image) {
		this.image = image;
		this.posX = posX;
		this.posY = posY;
		this.width = image.getWidth();
		this.height = image.getHeight();
	}

	public void update() {
		
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(image, posX, posY);
	}
	
	public void renderActive(GraphicsContext gc, int posX, int posY) {
		gc.drawImage(Images.ACTIVE_SHIELD, posX-15, posY-20, Constants.PLAYER_SIZE+30, Constants.PLAYER_SIZE+30);
		gc.drawImage(Images.SHIELD_BADGE, 25, 120);
	}

	public Rectangle2D getBoundary() {
		return new Rectangle2D(posX, posY, width, height);
	}

	public boolean intersects(Sprite s) {
		return s.getBoundary().intersects(this.getBoundary());
	}
	
	public void setActive(boolean active) {
		this.activeShield = active;
	}
	
	public boolean isActive() {
		return activeShield;
	}

	public void use() {		
	}
	
		
	}

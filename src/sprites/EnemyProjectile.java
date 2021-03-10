package sprites;

import constants.Images;
import javafx.scene.canvas.GraphicsContext;
import states.GameModel;

public class EnemyProjectile extends Projectile {

	public EnemyProjectile(GameModel model, double posX, double posY) {
		super(model, posX, posY);
	}
	
	public void update() {
		posY+=speed;
	}

	@Override
	public void render(GraphicsContext g) {
		g.drawImage(Images.ENEMY_LASER, posX+25, posY+40);
	}

}

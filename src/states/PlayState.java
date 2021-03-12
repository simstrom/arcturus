package states;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import main.GameFrame;
import sound.Sound;
import sprites.Enemy;
import sprites.Meteor;
import sprites.Player;
import sprites.PowerUp;
import sprites.Projectile;
import sprites.RapidFire;
import sprites.Shield;
import sprites.Ship;

import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import constants.Images;
import environment.PlayUI;
import environment.World;

/**
 * This state represents the Playing State of the Game The main responsibility
 * of this class is to; - create Game Objects - update Game Objects - draw Game
 * Objects Game Objects are for instance; players, enemies, npc's, etc...
 *
 * The PlayState can also be thought off as a blue print where data is loaded
 * into some container from a file or some other type of data storage.
 *
 * It can also be created by some class responsible for object creation and then
 * passed to the PlayState as a parameter. This means all the PlayState has to
 * do is receive a list of objects, store them in some container and then for
 * every object in that container update and render that object.
 *
 * This way you can let the user define different Levels based on what
 * parameters are passed into the PlayState.
 */
public class PlayState extends GameState {

	protected Color bgColor;
	protected Player player;
	private int ammoCounter = 0;
	private ArrayList<Projectile> laserList = new ArrayList<>();
	protected ArrayList<Enemy> enemyList = new ArrayList<>();
	protected ArrayList<PowerUp> powerUps = new ArrayList<>();
	private PowerUp activePower = null;
	private PlayUI ui;

	public PlayState(GameModel model) {
		super(model);
		bgColor = Color.BLACK;
		player = new Player(model);
		player.setImage(Images.PLAYER);
		ui = new PlayUI(model, player);

		for (int i = 0; i < Constants.MAX_ENEMIES; i++) {
			enemyList.add(new Ship(model));
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		g.clearRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		drawBg(g, bgColor);

		player.render(g);

		for (int i = powerUps.size() - 1; i >= 0; i--) {
			PowerUp powerUp = powerUps.get(i);
			powerUp.render(g);
		}
		if (player.hasShield())
			activePower.renderActive(g, player.getPosX(), player.getPosY());

		for (int i = laserList.size() - 1; i >= 0; i--) {
			Projectile shot = laserList.get(i);
			shot.render(g);
		}

		for (int i = enemyList.size() - 1; i >= 0; i--) {
			Enemy enemy = enemyList.get(i);
			enemy.render(g);
		}

		ui.draw(g);
		if (player.hasRapidFire())
			ui.drawAmmo(g, ammoCounter);

		for (World world : universe) {
			world.draw(g);
		}
	}

	@Override
	public void init(GameFrame frame) {
	}

	public void mouseClicked(MouseEvent event) {
		if ((!model.getGameOver()) && (laserList.size() < Constants.MAX_AMMO)) {
			laserList.add(player.shoot());
		}
		if (player.hasRapidFire()) {
			ammoCounter++;
			if (ammoCounter == Constants.POWER_AMMO) {
				player.setRapidFire(false);
				ammoCounter = 0;
			}
		}
	}

	@Override
	public void handleInput(List<String> input) {
		player.setVelocity(0, 0);
		if (input.contains("A"))
			player.addVelocity(-6, 0);
		if (input.contains("D"))
			player.addVelocity(6, 0);
		if (input.contains("W"))
			player.addVelocity(0, -6);
		if (input.contains("S"))
			player.addVelocity(0, 6);
		if (model.getGameOver() && (input.contains("SPACE"))) {
			Sound.click.play();
			model.setGameOver(false);
			model.switchState(new ScoreState(model));
		}
	}

	@Override
	public void update() {
		// Here one would probably instead move the player and any
		// enemies / moving obstacles currently active.
		player.update();

		for (int i = laserList.size() - 1; i >= 0; i--) {
			Projectile laser = laserList.get(i);
			if (laser.getPosY() < 0) {
				laserList.remove(i);
				continue;
			}
			laser.update();

			for (Enemy enemy : enemyList) {
				if (laser.instersects(enemy) && !enemy.isExploding()) {
					model.increaseScore();
					enemy.explode();
					if (laserList.size() != 0)
						laserList.remove(i);
					if (model.getScore() == 50) {
						model.increaseLevel();
						model.switchState(new SplashState(model));
					}
					int chance = Constants.RAND.nextInt(2);
					if (chance == 1) {
						int drop = Constants.RAND.nextInt(2);
						if (drop == 1) {
							powerUps.add(new RapidFire(enemy.getPosX(), enemy.getPosY(), Images.RAPID));
						} else {
							powerUps.add(new Shield(enemy.getPosX(), enemy.getPosY(), Images.SHIELD));
						}
					}
				}
			}
		}

		for (int i = powerUps.size() - 1; i >= 0; i--) {
			if (player.intersects(powerUps.get(i))) {
				if (powerUps.get(i) instanceof Shield)
					player.setShield(true);
				if (powerUps.get(i) instanceof RapidFire) {
					player.setRapidFire(true);
				}
				activePower = powerUps.get(i);
				powerUps.remove(i);
			}

		}

		for (Enemy enemy : enemyList) {
			enemy.update();

		}
		for (int i = enemyList.size() - 1; i >= 0; i--) {
			if (player.intersects(enemyList.get(i)) && (!player.isExploding()) && (!player.hasShield())
					&& (!enemyList.get(i).isExploding())) {
				player.takeDamage();
			} else if (player.intersects(enemyList.get(i)) && player.hasShield()) {
				enemyList.get(i).explode();
				player.setShield(false);
			}
			if (enemyList.get(i).isDestroyed()) {
				if (enemyList.get(i) instanceof Ship) {
					enemyList.set(i, new Ship(model));
				} else {
					enemyList.set(i, new Meteor(model));
				}
			}
		}

		if (player.isDestroyed()) {
			model.setGameOver(player.isDestroyed());
		}

		if (Constants.RAND.nextInt(10) > 2) {
			universe.add(new World());
		}
		for (int i = 0; i < universe.size(); i++) {
			if (universe.get(i).getPosY() > Constants.SCREEN_HEIGHT)
				universe.remove(i);
		}
	}

	@Override
	public void activate(GameFrame frame) {
	}

	@Override
	public void deactivate(GameFrame frame) {
	}

}

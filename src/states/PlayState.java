package states;

import testing.Tester;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import main.GameFrame;
import main.World;
import sprites.Enemy;
import sprites.Meteor;
import sprites.Player;
import sprites.PowerUp;
import sprites.Projectile;
import sprites.RapidFire;
import sprites.Shield;
import sprites.Ship;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import constants.Constants;
import constants.Images;

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
	/*
	 * The following three variables are just used to show that a change of state
	 * can be made. The same variables also exist in the MenuState, can you think of
	 * a way to make this more general and not duplicate variables?
	 */
	protected Player player;
	private int ammoCounter = 0;
	private ArrayList<Projectile> laserList = new ArrayList<>();
	protected ArrayList<Enemy> enemyList = new ArrayList<>();
	protected ArrayList<PowerUp> powerUps = new ArrayList<>();
	private PowerUp activePower = null;
	private int progress = 0;
	protected Color bgColor;
	protected Image bg;

	/* Class only used for testing */
	private Tester tester;

	public PlayState(GameModel model) {
		super(model);
		bgColor = Color.BLACK;
		bg = Images.MENU_BG;
		player = new Player(model);
		player.setImage(Images.PLAYER);

		for (int i = 0; i < Constants.MAX_ENEMIES; i++) {
			enemyList.add(new Ship(model));
		}
		
//		IntStream.range(0, Constants.MAX_ENEMIES).mapToObj(i -> Enemy.newEnemy(model)).forEach(enemyList::add);
	}

	/**
	 * Draws information text to the screen.
	 */
	@Override
	public void draw(GraphicsContext g) {
		g.clearRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		drawBg(g, bgColor);

		player.render(g);

		for (int i = powerUps.size() - 1; i >= 0; i--) {
			PowerUp powerUp = powerUps.get(i);
			powerUp.render(g);
		}

		if (player.hasShield()) {
			activePower.renderActive(g, player.getPosX(), player.getPosY());
		}

		for (int i = laserList.size() - 1; i >= 0; i--) {
			Projectile shot = laserList.get(i);
			shot.render(g);
		}

		for (int i = enemyList.size() - 1; i >= 0; i--) {
			Enemy enemy = enemyList.get(i);
			enemy.render(g);
		}

		g.setTextAlign(TextAlignment.CENTER);
		g.setFont(Constants.miscFont);
		g.setFill(Color.BLACK);
		g.fillRect(0, 0, Constants.SCREEN_WIDTH, 70);
		g.setFill(Color.WHITE);
		g.fillText("Score: " + model.getScore(), 70, 50);
		g.drawImage(Images.PLAYER_LIFE, 350, 30);
		g.fillText("X " + player.getLives(), 410, 50);
		g.fillText("Level: " + model.getLevel(), 730, 50);
		if (player.hasRapidFire()) {
			g.fillText("RAPID FIRE AMMO LEFT\n" + (ammoCounter) + " / 20", 360, 920);
			g.drawImage(Images.RAPID_BADGE, 25, 80);
		}
		if (model.getGameOver()) {
			g.setFont(Constants.mainFont);
			g.setFill(Color.SLATEBLUE);
			if (model.getScore() > model.getHighScore().get(0)) {
				g.fillText("NEW \n HIGHSCORE!", Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2.5);
			} else {
			g.fillText("Game Over!", Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2.5);
			}
			g.setFont(Constants.subFont);
			g.setFill(Color.WHITE);
			g.fillText("Your Score \n" + model.getScore(), Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 1.5);
		}

		for (World world : universe) {
			world.draw(g);
		}

//		drawBg(g, bgColor);
//
//		g.setFill(fontColor);
//		g.setFont(new Font(30)); // Big letters
//		g.fillText(informationText, SCREEN_WIDTH / 3, SCREEN_HEIGHT / 3);
		// Can also use:
		// g.setStroke(fontColor);
		// g.strokeText(informationText, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);

		// This could be a call to all our objects that we want to draw.
		// Using the tester simply to illustrate how it could work.
//		tester.delegate(g);
	}
	
	@Override
	public void init(GameFrame frame) {
		}

//	@Override
//	public void keyPressed(KeyEvent key) {
//		System.out.println("Trycker på " + key.getCode() + " i PlayState");
//
//		player.setVelocity(0, 0);
//		// CASE ISTÄLLET FÖR IF.
//		if (key.getCode() == KeyCode.A) {
//			player.addVelocity(-6, 0);
//		} else if (key.getCode() == KeyCode.D) {
//			player.addVelocity(6, 0);
//		} else if (key.getCode() == KeyCode.W) {
//			player.addVelocity(0, -6);
//		} else if (key.getCode() == KeyCode.S) {
//			player.addVelocity(0, 6);
//		}
//	}

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
		if (model.getGameOver()  && (input.contains("SPACE"))){
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
			if (laser.posY < 0) {
				laserList.remove(i);
				continue;
			}
			laser.update();

			for (Enemy enemy : enemyList) {
				if (laser.instersects(enemy) && !enemy.isExploding()) {
					model.increaseScore();
					enemy.explode();
					laserList.remove(i);
//					progress++;
					if (model.getScore() == 10) {
						model.increaseLevel();
						model.switchState(new SplashState(model));
					}
					// RANDOM CHANCE TO DROP POWERUP
					int chance = Constants.RAND.nextInt(30);
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
				System.out.println("Picked up POWERUP!");
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
//		
//		if (model.isBetween(model.getScore(), 9, 11)) {
//			model.switchState(new PlayStateTwo(model));
//		}
	}

	/**
	 * We currently don't have anything to activate in the PlayState so we leave
	 * this method empty in this case.
	 */
	@Override
	public void activate(GameFrame frame) {
	}

	/**
	 * We currently don't have anything to deactivate in the PlayState so we leave
	 * this method empty in this case.
	 */
	@Override
	public void deactivate(GameFrame frame) {
	}

}

package states;

import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.GameFrame;
import sound.Sound;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.util.List;

import constants.Constants;
import constants.Images;
import environment.World;

/**
 * This state represents the menu of the Game The main responsibility of this
 * class is to allow the user to swap state to the PlayState
 */
public class MenuState extends GameState {
	private Image bg = Images.MENU_BG;

	public MenuState(GameModel model) {
		super(model);
		if (!Sound.menuMusic.isPlaying())
		Sound.menuMusic.play();
	}

	/**
	 * Draws information text to the screen
	 */
	@Override
	public void draw(GraphicsContext g) {
		g.drawImage(bg, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

		for (World world : universe) {
			world.draw(g);
		}
	}

	@Override
	public void init(GameFrame frame) {
		Label logo = new Label(Constants.GAME_TITLE);
		logo.setFont(Constants.titleFont);
		logo.setStyle("-fx-text-fill: #6671DA");
		Label creator = new Label("© 2021, VasaValley Projects™");
		creator.setFont(Constants.miscFont);
		creator.setStyle("-fx-text-fill: #363C70");

		Button play = new Button(Constants.PLAY);
		play.setOnAction(e -> {
			Sound.click.play();
			Sound.menuMusic.stop();
			Sound.gameMusic.play();
			model.switchState(new PlayState(model));
		});

		Button score = new Button(Constants.SCORE);
		score.setOnAction(e -> {
			Sound.click.play();
			model.switchState(new ScoreState(model));
		});

		Button quit = new Button(Constants.QUIT);
		quit.setOnAction(e -> {
			Sound.click.play();
			System.exit(0);
		});

		VBox menuItems = new VBox(30, play, score, quit);
		menuItems.setAlignment(Pos.CENTER);
		VBox menuElements = new VBox(100, logo, menuItems, creator);
		menuElements.setAlignment(Pos.CENTER);

		Glow glow = new Glow();
		glow.setLevel(1.0);
		logo.setEffect(glow);
		creator.setEffect(glow);

		StackPane.setAlignment(menuElements, Pos.CENTER);
		contents.getChildren().addAll(menuElements);
		frame.getChildren().add(contents);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
	}

	@Override
	public void handleInput(List<String> input) {
	}

	@Override
	public void update() {
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
		init(frame);
	}

	@Override
	public void deactivate(GameFrame frame) {
		frame.getChildren().remove(contents);
	}

}

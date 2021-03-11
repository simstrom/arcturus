package states;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.util.List;

import constants.Constants;
import constants.Images;
import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.GameFrame;
import main.World;

public class ScoreState extends GameState {

	public ScoreState(GameModel model) {
		super(model);
		model.resetScore();
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
	public void draw(GraphicsContext g) {
		g.drawImage(Images.SCORE_BG, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

		g.setFont(Constants.subFont);
		g.setFill(Color.WHITE);
		int place = 1;
		int height = 575;
		for (int i = model.getHighScore().size() - 1; i >= 0; i--) {
			g.fillText(place + " : " + model.getHighScore().get(i).toString() + " points", SCREEN_WIDTH / 2,
					SCREEN_HEIGHT - height);
			place++;
			height -= 100;
		}

		for (World world : universe) {
			world.draw(g);
		}
	}

	@Override
	public void init(GameFrame frame) {
		Label title = new Label("HIGHSCORES");
		title.setFont(Constants.mainFont);
		title.setTextFill(Color.web("#6671DA"));

		Glow glow = new Glow();
		glow.setLevel(1.0);
		title.setEffect(glow);

		Button back = new Button("BACK TO MENU");
		VBox buttonBox = new VBox(back);
		buttonBox.setAlignment(Pos.CENTER);
		back.setOnAction(e -> {
			model.switchState(new MenuState(model));
		});

		VBox scoreElements = new VBox(500, title, buttonBox);
		contents.getChildren().add(scoreElements);
		frame.getChildren().add(contents);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
	}

	@Override
	public void handleInput(List<String> input) {
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

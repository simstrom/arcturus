package main;

import states.GameModel;

import java.util.ArrayList;

import constants.Constants;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Main is the entry point of the application. It also runs the main game loop
 * to update all elements.
 * 
 */
public class Main extends Application {
	private ArrayList<String> input = new ArrayList<>();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage gameStage) throws Exception {
		gameStage.setTitle(Constants.GAME_TITLE);
		gameStage.setWidth(Constants.SCREEN_WIDTH);
		gameStage.setHeight(Constants.SCREEN_HEIGHT);
		gameStage.setResizable(false);
		GameModel model = new GameModel();
		GameFrame frame = new GameFrame(model, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		model.setFrame(frame);
		Scene gameScene = new Scene(frame);
		// Set the target number of frames per second
		final double targetFps = 120.0;
		// Calculate frequency in nano seconds
		final double nanoPerUpdate = 1000000000.0 / targetFps;

		gameStage.setScene(gameScene);

		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				String code = event.getCode().toString();
				if (!input.contains(code)) {
					input.add(code);
				}
			}
		});

		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				String code = event.getCode().toString();
				input.remove(code);
				if (input.contains("SPACE"))
					input.remove("SPACE");
			}
		});

		gameScene.setOnMouseClicked(event -> {
			model.mouseClicked(event);
		});

		new AnimationTimer() {
			long lastUpdate = 0;

			public void handle(long now) {
				model.handleInput(input);

				if ((now - lastUpdate) > nanoPerUpdate) {
					model.update();
					frame.repaint();
					lastUpdate = now;
				}
			}
		}.start();
		gameStage.show();

	}
}
package main;

import states.GameModel;
import states.MenuState;

import java.util.ArrayList;

import constants.Constants;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * This Class is the entry point of the application.
 * <p>
 * This Class has the following primary responsibilities: 1. Serve as the entry
 * for the Application
 * <p>
 * 2. Create the GameModel (For more information about the GameModel see
 * /src/states/GameModel)
 * <p>
 * 3. Create the GameFrame (A HBox Wrapper): (For more information about the
 * GameFrame see /src/main/GameFrame)...
 * <p>
 * 4. Create the GamePanel (A VBOx Wrapper): (For more information about the
 * GamePanel see /src/main/GamePanel)...
 * <p>
 * 5. Run the main loop of the game.
 * <p>
 * https://gitlab.liu.se/magni54/ExempelProjekt-TDDE10_725G90
 * 
 * @author magni54, alomi60
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

		// We set up a setOnKeyPressed, to handle keyboard input,
		// like we had a onMouseClick in the canvas for the paint lab.
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

		// We set an AnimationTimer, to control the flow of the game.
		new AnimationTimer() {
			long lastUpdate = 0;

			// This method will be called
			public void handle(long now) {
				model.handleInput(input);

				// Perform game update and game rendering. This will
				// execute approximately 60 times per second, or as
				// close to that as possible. Can vary greatly between systems.
				// If we want closer control we use something like the
				// if-statement below to control frame rate.

				if ((now - lastUpdate) > nanoPerUpdate) {
					model.update();
					frame.repaint();
					lastUpdate = now;
				}
			}
		}.start(); // We start the timer.
		gameStage.show();

	}
}
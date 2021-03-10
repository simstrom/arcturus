package states;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.GameFrame;
import main.World;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.util.List;

import constants.Constants;
import constants.Images;

/**
 * This state represents the menu of the Game The main responsibility of this
 * class is to allow the user to swap state to the PlayState
 */
public class MenuState extends GameState {
	/*
	 * The following three variables are just used to show that a change of state
	 * can be made. The same variables also exist in the PlayState, can you think of
	 * a way to make this more general and not duplicate variables?
	 */
	private String informationText;
	private Image bg = Images.MENU_BG;
	private Color fontColor;
	// A PlayState, so we can change to the PlayState from the menu.
	private PlayState playState;
	


	public MenuState(GameModel model) {
		super(model);
		playState = new PlayState(model);
		informationText = "Press Enter To Play\nEscape to exit";
		fontColor = Color.DARKSLATEBLUE;
	}

	/**
	 * Draws information text to the screen
	 */
	@Override
	public void draw(GraphicsContext g) {
//		drawBg(g, bgColor);
		g.drawImage(bg, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
//		g.setFont(Constants.mainFont); // Big letters
		// Print the information text, centered on the canvas
//		g.setFill(fontColor);
//		g.fillText(Constants.GAME_TITLE, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 5);
//		g.setStroke(fontColor);
//		g.strokeText(Constants.GAME_TITLE, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 4);
		
		// Can also use:
		// g.setStroke(fontColor);
		// g.strokeText(informationText, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
		
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
			model.switchState(playState);
		});
		
		Button score = new Button(Constants.SCORE);
		score.setOnAction(e -> {
			model.switchState(new ScoreState(model));
		});
		
		Button quit = new Button(Constants.QUIT);
		quit.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
			System.out.println("DONE");
			System.exit(0);
			}
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

	/**
	 *
	 * @param key KeyEvent representing the pressed key
	 *
	 *            This function prints the pressed key to the console it's used to
	 *            show that a change of state has been made
	 *
	 *            For more information see GameState
	 */
//	@Override
//	public void keyPressed(KeyEvent key) {
//		if (key.getCode() == KeyCode.ESCAPE) {
//			System.exit(0);
//		}
//	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
	}
	
	@Override
	public void handleInput(List<String> input) {
	}

	/**
	 * We have nothing to update in the menu, no moving objects etc. so we leave the
	 * update method empty.
	 */
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

	/**
	 * We currently don't have anything to activate in the MenuState so we leave
	 * this method empty in this case.
	 */
	@Override
	public void activate(GameFrame frame) {
		init(frame);
	}

	/**
	 * We currently don't have anything to deactivate in the MenuState so we leave
	 * this method empty in this case.
	 */

	@Override
	public void deactivate(GameFrame frame) {
		frame.getChildren().remove(contents);
	}

}

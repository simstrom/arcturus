package states;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import constants.Images;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.GameFrame;
import main.Main;
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
//		drawBg(g, Color.web("#231a42"));
		g.drawImage(Images.SCORE_BG, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

		
		for (World world : universe) {
			world.draw(g);
		}

//		g.setFill(Color.WHITE);
//		g.setFont(Constants.mainFont);
//		g.fillText("HIGH SCORES", SCREEN_WIDTH / 2, SCREEN_HEIGHT / 5);
		
//		g.setFont(Constants.subFont);
//		int place = 1;
//		int height = 600;
//		for (int i = 2; i >= 0; i--) {	
//			g.fillText(place + ". " + model.getHighScore().get(i).toString() + " points", SCREEN_WIDTH / 2, SCREEN_HEIGHT - height);
//			place++;
//			height -= 100;
//		}
	}
	
	@Override
	public void init(GameFrame frame) {
		Label title = new Label("HIGHSCORES");
		title.setFont(Constants.mainFont);
		title.setTextFill(Color.web("#6671DA"));
		
		Text[] scoreList = {new Text("1st : " + model.getHighScore().get(2)), new Text("2nd : " + model.getHighScore().get(1)), new Text("3rd : " + model.getHighScore().get(0))};
		for (Text text : scoreList) {
			text.setFont(Constants.subFont);
			text.setFill(Color.WHITE);
		}
	
		VBox scoreBox = new VBox(30, scoreList[0], scoreList[1], scoreList[2]);
		scoreBox.setAlignment(Pos.CENTER);
		Glow glow = new Glow();   
        glow.setLevel(1.0);  
        title.setEffect(glow);
		
		Button back = new Button("BACK TO MENU");
		VBox buttonBox = new VBox(back);
		buttonBox.setAlignment(Pos.CENTER);
		back.setOnAction(e -> {
			model.switchState(new MenuState(model));
		});

		VBox scoreElements = new VBox(100, title, scoreBox, buttonBox);
		contents.getChildren().add(scoreElements);
		frame.getChildren().add(contents);
		System.out.println(Main.getInput());
	}

//	@Override
//	public void keyPressed(KeyEvent key) {
//		
//	}

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

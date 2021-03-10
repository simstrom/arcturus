package states;

import java.util.stream.IntStream;

import constants.Constants;
import constants.Images;
import javafx.scene.paint.Color;
import sprites.Enemy;
import sprites.Meteor;
import sprites.Shield;
import sprites.Ship;

public class LevelTwo extends PlayState {

	public LevelTwo(GameModel model) {
		super(model);
		bgColor = Color.web("#1c0e29");

		for (int i = 0; i < 4; i++) {
			enemyList.add(new Meteor(model));
		}
	}

	@Override
	public void update() {
		super.update();
	}

}

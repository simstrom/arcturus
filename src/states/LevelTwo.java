package states;

import javafx.scene.paint.Color;
import sprites.Meteor;

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

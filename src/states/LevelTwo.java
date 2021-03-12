package states;

import constants.Constants;
import javafx.scene.paint.Color;
import sprites.Meteor;

/**
 * Defines the updated logic for the next level. Adding more enemies of a
 * different type and changing the background.
 * 
 * @author simonnystrom
 *
 */
public class LevelTwo extends PlayState {

	public LevelTwo(GameModel model) {
		super(model);
		bgColor = Color.web("#1c0e29");

		for (int i = 0; i < Constants.MAX_METEORS; i++) {
			enemyList.add(new Meteor(model));
		}
	}

	@Override
	public void update() {
		super.update();
	}

}

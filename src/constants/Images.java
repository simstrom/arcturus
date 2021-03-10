package constants;

import javafx.scene.image.Image;

public interface Images {
	Image MENU_BG = new Image("menubg.png");
	Image SCORE_BG = new Image("scorebg.png");
	Image GAME_BG = new Image("gamebg.png");
	Image SPLASH_BG = new Image("splashbg.png");
	
	Image PLAYER = new Image("player.png");
	Image PLAYER_LIFE = new Image("playerlife.png");
	Image PLAYER_LASER = new Image("playerlaser.png");
	Image PLAYER_POWER_LASER = new Image("playerpowerlaser.png");
	Image SHIELD = new Image("shield_gold.png");
	Image ACTIVE_SHIELD = new Image("shield.png");
	Image SHIELD_BADGE = new Image("shieldbadge.png");
	Image RAPID = new Image("bolt_gold.png");
	Image RAPID_BADGE = new Image("rapidbadge.png");
	
	Image EXPLOSION = new Image("explosion.png");
	
	Image ENEMIES[] = {
			new Image("enemyBlack1.png"),
			new Image("enemyBlack2.png"),
			new Image("enemyBlack3.png"),
			new Image("enemyBlack4.png"),
			new Image("enemyBlack5.png"),
			new Image("enemyRed1.png"),
			new Image("enemyRed2.png"),
			new Image("enemyRed3.png"),
			new Image("enemyRed4.png"),
			new Image("enemyRed5.png"),
	};
//	KOLLA OM SPEED PÅ X AXEL ISTÄLLET!
	Image METEORS[] = { 
			new Image("meteor1.png"),
			new Image("meteor2.png"),
			new Image("meteor3.png"),
			new Image("meteor4.png"),
	};
	Image ENEMY_LASER = new Image("enemylaser.png");
	
}

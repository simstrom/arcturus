package states;

import java.nio.file.Paths;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
	
	public Sound() {
	}
	
	public static void playMusic(String music) {
		Media m = new Media(Paths.get(music).toUri().toString());
		MediaPlayer mp = new MediaPlayer(m);
		mp.play();
	}
}

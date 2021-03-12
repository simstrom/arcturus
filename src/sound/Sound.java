package sound;

import java.nio.file.Paths;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Responsible for playing background music as well as sound effects on
 * different actions.
 * 
 * @author simonnystrom
 *
 */
public class Sound {
	private Media m;
	private MediaPlayer mp;
	private boolean playing = false;
	public static Sound click = new Sound("resources/click.wav", 0.1);
	public static Sound menuMusic = new Sound("resources/menusong.mp3", 0.05);
	public static Sound gameMusic = new Sound("resources/gamesong.mp3", 0.05);

	public Sound(String path, double volume) {
		m = new Media(Paths.get(path).toUri().toString());
		mp = new MediaPlayer(m);
		mp.setVolume(volume);
	}

	public void play() {
		mp.seek(Duration.ZERO);
		mp.play();
		playing = true;
	}

	public void stop() {
		mp.stop();
		playing = false;
	}

	public boolean isPlaying() {
		return playing;
	}
}

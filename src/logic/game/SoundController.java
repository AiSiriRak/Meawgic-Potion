package logic.game;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundController {
	private static final String MUSIC_FILE_PATH = "Sound/song.mp3";
	private static MediaPlayer mediaPlayer;
	private static SoundController instance = new SoundController();
	private final List<MediaPlayer> activeEffects = new ArrayList<>();
	private boolean musicEnabled = true;
	private boolean effectsEnabled = true;

	public static SoundController getInstance() {
		return instance;
	}

	public void playMusic() {
		if (musicEnabled && mediaPlayer != null) {
			mediaPlayer.play();
		} else if (musicEnabled && mediaPlayer == null) {
			try {
				URL resource = getClass().getClassLoader().getResource(MUSIC_FILE_PATH);
				if (resource != null) {
					mediaPlayer = new MediaPlayer(new Media(resource.toString()));
					mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
					mediaPlayer.setVolume(0.1);
					mediaPlayer.play();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void pauseMusic() {
		mediaPlayer.pause();
	}

	public void playEffectSound(String effectFileName) {
		if (!effectsEnabled)
			return;

		try {
			URL resource = getClass().getClassLoader().getResource("Sound/" + effectFileName + ".mp3");
			Media media = new Media(resource.toString());
			MediaPlayer effectPlayer = new MediaPlayer(media);
			effectPlayer.setVolume(0.2);
			effectPlayer.setOnReady(() -> effectPlayer.play());
			effectPlayer.setOnEndOfMedia(() -> activeEffects.remove(effectPlayer));
			activeEffects.add(effectPlayer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stopAllEffects() {
		for (MediaPlayer player : new ArrayList<>(activeEffects)) {
			player.stop();
		}
		activeEffects.clear();
	}

	public void setMusicEnabled(boolean enabled) {
		this.musicEnabled = enabled;

		if (enabled) {
			playMusic();
		} else {
			pauseMusic();
		}
	}

	public void setEffectsEnabled(boolean enabled) {
		this.effectsEnabled = enabled;
		if (!enabled) {
			stopAllEffects();
		}
	}

}
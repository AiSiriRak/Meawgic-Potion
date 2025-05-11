package logic.game;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundController {
    private final String MUSIC_FILE_PATH = "Sound/song.mp3";
    private MediaPlayer mediaPlayer;
    private static SoundController instance = new SoundController();
    private final List<MediaPlayer> activeEffects = new ArrayList<>();
    private boolean musicEnabled = true;
    private boolean effectsEnabled = true;
    private double currentMusicTime = 0.0;

    private SoundController() {
        initializeMusicPlayer();
        if (musicEnabled) {
            playMusic();
        }
    }

    private void initializeMusicPlayer() {
        if (mediaPlayer == null) {
            try {
                URL resource = getClass().getClassLoader().getResource(MUSIC_FILE_PATH);
                if (resource != null) {
                    Media media = new Media(resource.toString());
                    mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                    mediaPlayer.setVolume(0.5);
                    mediaPlayer.setOnError(() -> {
                        mediaPlayer.dispose();
                        mediaPlayer = null;
                    });
                } else {
                }
            } catch (Exception e) {
                e.printStackTrace();
                mediaPlayer = null;
            }
        }
    }

    public static SoundController getInstance() {
        return instance;
    }

    public void playMusic() {
        if (musicEnabled && mediaPlayer != null) {
            mediaPlayer.play();
            mediaPlayer.seek(javafx.util.Duration.seconds(currentMusicTime));
        } else if (musicEnabled && mediaPlayer == null) {
            initializeMusicPlayer();
            if (mediaPlayer != null) {
                mediaPlayer.play();
            }
        } else {
        }
    }

    public void pauseMusic() {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            currentMusicTime = mediaPlayer.getCurrentTime().toSeconds();
            mediaPlayer.pause();
        }
    }

    public void playEffectSound(String effectFileName) {
        if (!effectsEnabled) return;

        try {
            URL resource = getClass().getClassLoader().getResource("Sound/" + effectFileName + ".mp3");
            if (resource != null) {
                Media media = new Media(resource.toString());
                MediaPlayer effectPlayer = new MediaPlayer(media);
                effectPlayer.setVolume(0.9);
                effectPlayer.setOnReady(() -> effectPlayer.play());
                effectPlayer.setOnEndOfMedia(() -> activeEffects.remove(effectPlayer));
                effectPlayer.setOnError(() -> System.err.println("playEffectSound: MediaPlayer error: " + effectPlayer.getError()));
                activeEffects.add(effectPlayer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public boolean isMusicEnabled() {
        return musicEnabled;
    }

    public boolean isEffectsEnabled() {
        return effectsEnabled;
    }

    public void stopAllEffects() {
        for (MediaPlayer player : new ArrayList<>(activeEffects)) {
            player.stop();
        }
        activeEffects.clear();
    }
}
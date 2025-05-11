package logic.components;

import javafx.scene.image.Image;
import logic.game.SoundController;

public class Animation {

	private Image[] frames;
	private int currentFrame;
	private double frameDurationMillis;
	private long lastFrameTime;

	public Animation(Image[] frames, int fps) {
		this.frames = frames;
		this.currentFrame = 0;
		this.frameDurationMillis = 1000.0 / fps;
		this.lastFrameTime = System.currentTimeMillis();
	}

	public void update() {
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastFrameTime >= frameDurationMillis) {
			currentFrame = (currentFrame + 1) % frames.length;
			lastFrameTime = currentTime;
		}
	}

	public Image getCurrentFrame() {
		return frames[currentFrame];
	}
}

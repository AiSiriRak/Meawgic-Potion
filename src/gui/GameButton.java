package gui;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import logic.game.SoundController;

public class GameButton extends Button {

	public GameButton(String text, String effect) {
		setGameButton(text, 48, effect);
	}

	public GameButton(String text, int size, String effect) {
		setGameButton(text, size, effect);
	}

	private void setGameButton(String text, int size, String effect) {
		ImageView imageView = new ImageView(ClassLoader.getSystemResource("Images/" + text + "_btn.png").toString());
		imageView.setFitWidth(size);
		imageView.setPreserveRatio(true);
		setGraphic(imageView);
		setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-border-color: transparent;");

		setHover(effect);

	}
	
	public void setHover(String sound) {
		setOnMouseEntered(e -> {
			setScaleX(1.08);
			setScaleY(1.08);
			SoundController.getInstance().playEffectSound(sound);
		});
		setOnMouseExited(e -> {
			setScaleX(1);
			setScaleY(1);
		});
	}
}
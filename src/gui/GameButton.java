package gui;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class GameButton extends Button {

	public GameButton(String text) {
		setGameButton(text, 48);
	}

	public GameButton(String text, int size) {
		setGameButton(text, size);
	}

	private void setGameButton(String text, int size) {
		ImageView imageView = new ImageView(ClassLoader.getSystemResource("Images/" + text + "_btn.png").toString());
		imageView.setFitWidth(size);
		imageView.setPreserveRatio(true);
		setGraphic(imageView);
		setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-border-color: transparent;");

		setHover();

	}

	private void setHover() {
		// Set Bigger When Hover
		setOnMouseEntered(e -> {
			setScaleX(1.08);
			setScaleY(1.08);
		});
		setOnMouseExited(e -> {
			setScaleX(1);
			setScaleY(1);
		});
	}
}
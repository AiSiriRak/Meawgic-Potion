package gui.button;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class GameButton extends Button {

	public GameButton(String text) {
		super(text);
		ImageView imageView = new ImageView(ClassLoader.getSystemResource("Images/" + text + "_btn.png").toString());
		imageView.setFitWidth(48);
		imageView.setPreserveRatio(true);
		imageView.setSmooth(true);
		setGraphic(imageView);
		setText("");
		setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-border-color: transparent;");
	}
}
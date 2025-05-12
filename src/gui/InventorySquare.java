package gui;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;

public class InventorySquare extends StackPane {
	private int xPosition;
	private int yPosition;

	public InventorySquare(int x, int y, String inventory) {
		this.xPosition = x;
		this.yPosition = y;

		Image bgImage = new Image(ClassLoader.getSystemResource("Images/" + inventory + "_frame.png").toString());
		BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(48, 48, false, false, false, false));
		this.setBackground(new Background(backgroundImage));
		this.setBorder(Border.EMPTY);
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

}
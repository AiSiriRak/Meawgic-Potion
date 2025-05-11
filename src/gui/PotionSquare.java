package gui;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;

public class PotionSquare extends Pane {
	private int xPosition;
	private int yPosition;
	private String inventory;

	public PotionSquare(int x, int y) {
		this.setxPosition(x);
		this.setyPosition(y);

		Image bgImage = new Image(ClassLoader.getSystemResource("Images/" + "Brewing_Frame.png").toString());
		BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(48, 48, false, false, false, false));
		this.setBackground(new Background(backgroundImage));
		this.setBorder(Border.EMPTY);
	}

	public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
}
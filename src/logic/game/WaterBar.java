package logic.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class WaterBar extends Pane {
	private ImageView barView;
	private String waterLevelTxt;
	private int waterLevel;

	private boolean isEnoughWater;

	public WaterBar() {
		this.waterLevelTxt = "00";
		this.barView = new ImageView();
		this.getChildren().add(barView);
		updateBar(0);

		this.setTranslateX(GameController.SCREEN_WIDTH - 100);
		this.setTranslateY(GameController.SCREEN_HEIGHT / 2 - 160);
	}

	public void updateBar(int waterLevel) {
		if (waterLevel >= 0) {
			this.waterLevel = waterLevel;
			this.isEnoughWater = true;
			this.waterLevelTxt = waterLevel == 10 ? "" + waterLevel : "0" + waterLevel;
			Image image = new Image(
					ClassLoader.getSystemResource("Images/WaterBar_" + this.waterLevelTxt + ".png").toString());
			barView.setImage(image);
		} else {
			System.out.println("No Water!!");
			this.isEnoughWater = false;
		}
	}

	public int getWaterLevel() {
		return this.waterLevel;
	}

	public boolean isEnoughWater() {
		return isEnoughWater;
	}

	public void setEnoughWater(boolean isEnoughWater) {
		this.isEnoughWater = isEnoughWater;
	}
}

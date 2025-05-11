package logic.object;

import entity.base.Crop;
import entity.data.*;
import gui.ControlBrewing;
import gui.PlantPane;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import logic.game.GameController;
import logic.game.SoundController;

public class CropPlot extends GameObject implements Interactable, DoTimer {
	protected Rectangle2D interactArea;
	private Crop item;
	private int currentStage;

	private boolean isWatered;
	private boolean isTiming;
	private int currentTime;

	private PlantPane plantPane;

	public CropPlot(String name, double x, double y) {
		super(name, x, y, new Rectangle2D(x + 0, y + 10, 192, 182));
		this.setImage(new Image(ClassLoader.getSystemResource("Images/Crop_0.png").toString()));
		this.interactArea = new Rectangle2D(x + 64, y + 192, 64, 64);
		this.currentStage = 0;
		this.item = null;
		this.isWatered = false;
		this.isTiming = false;
		this.currentTime = 0;
	}

	@Override
	public Rectangle2D getInteractArea() {
		return this.interactArea;
	}

	@Override
	public void interact() {
		System.out.println("Interact with " + this.name);

		switch (this.currentStage) {

		case 0:
            // Only show plant selection if no item is set
            if (this.item == null && plantPane != null) {
                plantPane.show();
                GameController.setCurrentPlantPane(plantPane);
            }
            break;

		case 1:
			GameController.waterBar.updateBar(GameController.waterBar.getWaterLevel() - 3);
			if (GameController.waterBar.isEnoughWater()) {
				SoundController.getInstance().playEffectSound("Water");
				isWatered = true;

				this.changeStage(2);

				this.startTiming(this.item.getDuration());

//				System.out.println("Watered!!");
			}
			break;

		case 3:
			GameController.getInventoryPane().addIngredient(item);
			this.item = null;
			isWatered = false;
			this.changeStage(0);

			System.out.println("Gain 1 Crop!!");
			break;
		}

	}

	public Crop getItem() {
		return this.item;
	}

	public void setItem(Crop item) {
		this.item = item;
	}

	public void changeStage(int stage) {
		this.currentStage = stage;

		Canvas canvas = new Canvas(192, 192);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setImageSmoothing(false);
		
		if (plantPane != null && stage > 0) {
	        plantPane.setVisible(false);
	    }

		Image img = null;
		switch (this.currentStage) {
		case 0:
       	img = new Image(ClassLoader.getSystemResource("Images/Crop_0.png").toString());
			break;
		case 1:
			if (plantPane != null) {
				plantPane.show();
				GameController.setCurrentPlantPane(plantPane);
				if (isWatered) {
					img = new Image(ClassLoader.getSystemResource("Images/Crop_1.png").toString());
					SoundController.getInstance().playEffectSound("Water");
				} else {
					img = new Image(ClassLoader.getSystemResource("Images/Crop_1_Dry.png").toString());
				}
			}
			break;
		case 2:
			img = new Image(ClassLoader.getSystemResource("Images/Crop_2.png").toString());
			break;
		case 3:
			img = new Image(ClassLoader.getSystemResource("Images/Crop_3.png").toString());
			break;
		}
		gc.drawImage(img, 0, 0);
		if (this.currentStage != 0) {
			gc.drawImage(new Image(ClassLoader.getSystemResource("Images/" + this.item.getName() + ".png").toString()),
					76, 125, 40, 40);
			if (this.currentStage == 3)
				gc.drawImage(
						new Image(ClassLoader.getSystemResource("Images/" + this.item.getName() + ".png").toString()),
						72, 27, 48, 48);
		}

		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);
		WritableImage combined = new WritableImage(192, 192);
		canvas.snapshot(params, combined);

		this.setImage(combined);
	}

	@Override
	public boolean getCanInteracte() {
		if (currentStage == 2 || (currentStage == 1 && isWatered))
			return false;
		return true;
	}

	@Override
	public void startTiming(int second) {
		this.isTiming = true;
		this.currentTime = second;

		Thread cropTimerThread = new Thread(() -> {
			while (this.isTiming) {
				try {
					Thread.sleep(1000);
					this.currentTime--;

					if (this.currentTime <= 0) {
						this.isTiming = false;

						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								changeStage(3);
							}
						});

						;
					} else if (currentTime <= second / 2) {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								changeStage(2);
							}
						});
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, "Start Timing - " + this.name);
		cropTimerThread.start();
	}

	@Override
	public String getTime() {
		return String.format("%02d : %02d", Math.floorDiv(this.currentTime, 60), this.currentTime % 60);
	}

	@Override
	public boolean isTiming() {
		return this.isTiming;
	}

	public void setPlantPane(PlantPane planePane) {
		this.plantPane = planePane;
	}
}
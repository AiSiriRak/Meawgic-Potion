package logic.object;

import gui.pane.ControlBrewing;
import entity.base.Potion;
import entity.data.PotionData;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import logic.components.Animation;
import logic.components.DoAnimation;
import logic.game.GameController;

public class Pot extends GameObject implements Interactable, DoAnimation, DoTimer {
	protected Rectangle2D interactArea;
	private Potion potion;
	private int currentStage;
	final private int fps = 6;
	private Animation potAnimation;

	private boolean isTiming;
	private int currentTime;

	public Pot(String name, double x, double y) {
		super(name, x, y, new Rectangle2D(x + 21, y + 51, 86, 77));
		this.setImage(new Image(ClassLoader.getSystemResource("Images/Pot_Empty.png").toString()));
		this.interactArea = new Rectangle2D(x + 32, y + 132, 64, 64);
		this.currentStage = 0;
		this.potion = null;
		this.isTiming = false;
		this.currentTime = 0;
		setAnimation();
	}

	@Override
	public void interact() {
		System.out.println("Interact with " + this.name);

		switch (this.currentStage) {
		case 0:
			GameController.getControlBrewing().setVisible(true);

			this.potion = PotionData.NIGHT_VISION.getItem();
			this.startTiming(this.potion.getDuration());
			this.changeStage(1);

			break;
		case 2:
			this.potion = null;
			this.changeStage(0);

			System.out.println("Gain 1 Potion!!");
			break;
		}
	}

	@Override
	public Rectangle2D getInteractArea() {
		return this.interactArea;
	}

	public void interactAreaRender(GraphicsContext gc, double camX, double camY) {

		gc.setFill(Color.GREEN);
		gc.fillRect(interactArea.getMinX() - camX, interactArea.getMinY() - camY, interactArea.getWidth(),
				interactArea.getHeight());

	}

	public void setAnimation() {
		Image[] PotFrames = new Image[] { new Image(ClassLoader.getSystemResource("Images/Pot_1.png").toString()),
				new Image(ClassLoader.getSystemResource("Images/Pot_2.png").toString()),
				new Image(ClassLoader.getSystemResource("Images/Pot_3.png").toString()),
				new Image(ClassLoader.getSystemResource("Images/Pot_2.png").toString()) };
		potAnimation = new Animation(PotFrames, fps);
	}

	public void updateAnimation() {
		if (currentStage == 1) {

			potAnimation.update();

			Canvas canvas = new Canvas(128, 128);
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.setImageSmoothing(false);
			Image img = potAnimation.getCurrentFrame();

			gc.drawImage(img, 0, 0);

			gc.setFill(Color.web("#34022A", 0.6));
			gc.fillOval(92, 1, 36, 36);

			gc.drawImage(
					new Image(ClassLoader.getSystemResource("Images/" + this.potion.getName() + ".png").toString()), 95,
					4, 32, 32);

			SnapshotParameters params = new SnapshotParameters();
			params.setFill(Color.TRANSPARENT);
			WritableImage combined = new WritableImage(192, 192);
			canvas.snapshot(params, combined);
			this.setImage(combined);

		}
	}

	public void changeStage(int stage) {
		this.currentStage = stage;

		switch (this.currentStage) {

		case 0:
			this.setImage(new Image(ClassLoader.getSystemResource("Images/Pot_Empty.png").toString()));
			break;
		case 1:
			updateAnimation();
			break;
		case 2:
			Canvas canvas = new Canvas(192, 192);
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.setImageSmoothing(false);
			Image img = new Image(ClassLoader.getSystemResource("Images/Pot_Done.png").toString());

			gc.drawImage(img, 0, 0);

			gc.setFill(Color.web("#34022A", 0.7));
			gc.fillOval(6, 6, 52, 52);

			gc.drawImage(
					new Image(ClassLoader.getSystemResource("Images/" + this.potion.getName() + ".png").toString()), 10,
					10, 48, 48);

			SnapshotParameters params = new SnapshotParameters();
			params.setFill(Color.TRANSPARENT);
			WritableImage combined = new WritableImage(192, 192);
			canvas.snapshot(params, combined);
			this.setImage(combined);
			break;
		}
	}

	@Override
	public boolean getCanInteracte() {
		if (currentStage == 1)
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
								changeStage(2);
							}
						});

						;
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

}

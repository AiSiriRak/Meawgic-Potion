package logic.object;

import entity.base.Potion;
import entity.potion.*;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.components.Animation;
import logic.components.DoAnimation;

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
	public Rectangle2D getInteractArea() {
		return this.interactArea;
	}

	@Override
	public void interact() {
		System.out.println("Interact with " + this.name);
		switch (this.currentStage) {
		case 0:
			this.potion = new NightVision();
			this.startTiming(this.potion.getDuration());
			this.changeStage(1);

//			System.out.println("Crafted!!");
			break;
		case 2:
			this.potion = null;
			this.changeStage(0);

			System.out.println("Gain 1 Potion!!");
			break;
		}
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
			this.setImage(potAnimation.getCurrentFrame());
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
			this.setImage(new Image(ClassLoader.getSystemResource("Images/Pot_Done.png").toString()));
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

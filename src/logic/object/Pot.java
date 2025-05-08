package logic.object;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.components.Animation;
import logic.components.DoAnimation;
import logic.game.GameController;

public class Pot extends GameObject implements Interactable, DoAnimation {
	protected Rectangle2D interactArea;
	private int currentStage;
	final private int fps = 6;
	private Animation potAnimation;

	public Pot(String name, double x, double y) {
		super(name, x, y, new Rectangle2D(x + 21, y + 51, 86, 77));
		this.setImage(new Image(ClassLoader.getSystemResource("Images/Pot_Empty.png").toString()));
		this.interactArea = new Rectangle2D(x + 32, y + 132, 64, 64);
		this.currentStage = 0;
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
			this.changeStage(1);

			System.out.println("Crafted!!");
			break;
		case 2:
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

}

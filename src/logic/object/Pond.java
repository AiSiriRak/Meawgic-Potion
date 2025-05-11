package logic.object;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import logic.components.Animation;
import logic.components.DoAnimation;
import logic.game.GameController;
import logic.game.SoundController;

public class Pond extends GameObject implements Interactable, DoAnimation {

	protected Rectangle2D interactArea;
	private Animation pondAnimation;

	final private int fps = 2;

	public Pond(String name, double x, double y) {
		super(name, x, y, new Rectangle2D(x + 0, y + 0, 128, 128));
		this.interactArea = new Rectangle2D(x + 130, y + 32, 64, 64);
		setAnimation();
	}

	@Override
	public Rectangle2D getInteractArea() {
		return this.interactArea;
	}

	@Override
	public void interact() {
		SoundController.getInstance().playEffectSound("Water");
		System.out.println("Interact with " + this.name);
		GameController.waterBar.updateBar(10);

	}

	public void setAnimation() {
		Image[] pondFrames = new Image[] { new Image(ClassLoader.getSystemResource("Images/Pond_1.png").toString()),
				new Image(ClassLoader.getSystemResource("Images/Pond_2.png").toString()) };
		pondAnimation = new Animation(pondFrames, fps);
	}

	@Override
	public void updateAnimation() {
		pondAnimation.update();
		this.setImage(pondAnimation.getCurrentFrame());
	}

	@Override
	public boolean getCanInteract() {
		if (GameController.waterBar.getWaterLevel() == 10)
			return false;
		return true;
	}

}

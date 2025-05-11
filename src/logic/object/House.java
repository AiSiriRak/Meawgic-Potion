package logic.object;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import logic.components.OutsideMap;
import logic.game.GameController;
import logic.game.SoundController;

public class House extends GameObject implements Interactable {
	protected Rectangle2D interactArea;

	public House(String name, double x, double y) {
		super(name, x, y, new Rectangle2D(x + 62, y + 0, 196, 283));
		this.setImage(new Image(ClassLoader.getSystemResource("Images/House.png").toString()));
		this.interactArea = new Rectangle2D(x + 128, y + 283, 64, 64);
	}

	@Override
	public Rectangle2D getInteractArea() {
		return this.interactArea;
	}

	@Override
	public void interact() {
		SoundController.getInstance().playEffectSound("Door");
		System.out.println("Interact with " + this.name);
		GameController.switchCurrentMap();

	}

	@Override
	public boolean getCanInteracte() {
		if (GameController.currentMap instanceof OutsideMap)
			return true;
		else
			return false;
	}

}

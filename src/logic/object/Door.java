package logic.object;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.WritableImage;
import logic.components.InsideMap;
import logic.game.GameController;
import logic.game.SoundController;

public class Door extends GameObject implements Interactable {
	protected Rectangle2D interactArea;

	public Door(String name, double x, double y) {
		super(name, x, y, new Rectangle2D(x, y, 0, 0));
		this.setImage(new WritableImage(1, 1));
		this.interactArea = new Rectangle2D(x, y - 64, 64, 64);
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
		if (GameController.currentMap instanceof InsideMap)
			return true;
		else
			return false;
	}

}

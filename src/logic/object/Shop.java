package logic.object;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class Shop extends GameObject implements Interactable {
	protected Rectangle2D interactArea;

	public Shop(String name, double x, double y) {
		super(name, x, y, new Rectangle2D(x + 9, y + 115, 49, 13));
		this.setImage(new Image(ClassLoader.getSystemResource("Images/Shop.png").toString()));
		this.interactArea = new Rectangle2D(x, y + 128, 64, 64);
	}

	@Override
	public Rectangle2D getInteractArea() {
		return this.interactArea;
	}

	@Override
	public void interact() {
		System.out.println("Interact with " + this.name);

	}

	@Override
	public boolean getCanInteracte() {
		return true;
	}

}

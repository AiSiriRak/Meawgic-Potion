package logic.object;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

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
		System.out.println("Interact with " + this.name);

	}

}

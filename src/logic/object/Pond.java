package logic.object;

import javafx.geometry.Rectangle2D;

public class Pond extends GameObject implements Interactable {

	protected Rectangle2D interactArea;

	public Pond(String name, double x, double y) {
		super(name, x, y, "Pond", new Rectangle2D(x + 0, y + 0, 128, 128));
		this.interactArea = new Rectangle2D(x + 130, y + 32, 64, 64);
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

package logic.object;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import logic.components.Player;

public class House extends GameObject implements Interactable {
	protected Rectangle2D interactArea;

	public House(String name, double x, double y) {
		super(name, x, y, "House", new Rectangle2D(x + 62, y + 0, 196, 283));
		this.interactArea = new Rectangle2D(x + 128, y + 283, 64, 64);
	}

	@Override
	public void onInteract(Player player) {

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
	public void interactAreaRender(GraphicsContext gc, double camX, double camY) {
		gc.setStroke(Color.GREEN);
		gc.setLineWidth(2);
		gc.strokeRect(interactArea.getMinX() - camX, interactArea.getMinY() - camY, interactArea.getWidth(),
				interactArea.getHeight());

	}

}

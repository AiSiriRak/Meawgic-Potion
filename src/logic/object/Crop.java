package logic.object;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Crop extends GameObject implements Interactable {
	protected Rectangle2D interactArea;

	public Crop(String name, double x, double y) {
		super(name, x, y, "Crop", new Rectangle2D(x + 0, y + 10, 192, 182));
		this.interactArea = new Rectangle2D(x + 64, y + 192, 64, 64);
	}

	@Override
	public Rectangle2D getInteractArea() {
		return this.interactArea;
	}

	@Override
	public void interact() {
		System.out.println("Interact with " + this.name);

	}

	public void interactAreaRender(GraphicsContext gc, double camX, double camY) {

		gc.setFill(Color.GREEN);
		gc.fillRect(interactArea.getMinX() - camX, interactArea.getMinY() - camY, interactArea.getWidth(),
				interactArea.getHeight());

	}

}

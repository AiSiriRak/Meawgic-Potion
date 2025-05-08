package logic.object;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public interface Interactable {
	Rectangle2D getInteractArea();

	void interact();

	public void interactAreaRender(GraphicsContext gc, double camX, double camY);
}

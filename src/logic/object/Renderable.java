package logic.object;

import javafx.scene.canvas.GraphicsContext;

public interface Renderable {
	double getY();

	void render(GraphicsContext gc, double camX, double camY);
}

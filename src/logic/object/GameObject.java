package logic.object;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class GameObject implements Collidable, Renderable {

	protected double x, y;
	protected Image image;
	protected Rectangle2D hitbox;
	protected String name;
	protected int interactAreaBorder;

	public GameObject(String name, double x, double y, Rectangle2D hitbox) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.hitbox = hitbox;
		this.interactAreaBorder = 2;
	}

	public void render(GraphicsContext gc, double camX, double camY) {
		gc.setImageSmoothing(false);

		// Render Interact Area
		if (this instanceof Interactable) {
			Rectangle2D interactArea = ((Interactable) this).getInteractArea();
			if (((Interactable) this).getCanInteracte()) {

				gc.setFill(Color.color(1, 1, 1, 0.2));
				gc.setStroke(Color.WHITE);
				gc.setLineWidth(interactAreaBorder);

			} else {
				gc.setFill(Color.color(1, 1, 1, 0.1));
				gc.setStroke(Color.color(1, 1, 1, 0.2));
				gc.setLineWidth(2);
			}
			gc.strokeRoundRect(interactArea.getMinX() - camX, interactArea.getMinY() - camY, interactArea.getWidth(),
					interactArea.getHeight(), 20, 20);
			gc.fillRoundRect(interactArea.getMinX() - camX, interactArea.getMinY() - camY, interactArea.getWidth(),
					interactArea.getHeight(), 20, 20);
		}

		// Render Image0
		gc.drawImage(image, 0, 0, this.image.getWidth(), this.image.getHeight(), x - camX, y - camY,
				this.image.getWidth(), this.image.getHeight());

		// Render Timer
		if (this instanceof DoTimer) {
			if (((DoTimer) this).isTiming()) {

				gc.setFont(new Font(16));

				gc.setFill(Color.WHITE);
				gc.fillRoundRect(x - camX, y - camY - 10, 70, 30, 8, 8);

				gc.setFill(Color.BLACK);
				gc.setFont(new Font(16));
				gc.fillText(((DoTimer) this).getTime(), x - camX + 11, y - camY + 9);

			}
		}

		// Render Hitbox
//		gc.setStroke(Color.RED);
//		gc.setLineWidth(2);
//		gc.strokeRect(hitbox.getMinX() - camX, hitbox.getMinY() - camY, hitbox.getWidth(), hitbox.getHeight());

	}

	@Override
	public Rectangle2D getHitbox() {
		return this.hitbox;
	}

	public double getY() {
		return this.getHitbox().getMaxY();

	}

	public void setInteractAreaBorder(int i) {
		this.interactAreaBorder = i;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}

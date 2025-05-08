package logic.object;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class GameObject implements Collidable, Renderable {

	protected double x, y;
	protected Image image;
	protected Rectangle2D hitbox;
	protected String name;
	protected int interactAreaBorder;

	public GameObject(String name, double x, double y, String imageName, Rectangle2D hitbox) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.image = new Image(ClassLoader.getSystemResource("Images/" + imageName + ".png").toString());
		this.hitbox = hitbox;
		this.interactAreaBorder = 2;
	}

	public void render(GraphicsContext gc, double camX, double camY) {

		// Render Interact Area
		if (this instanceof Interactable) {

			Rectangle2D interactArea = ((Interactable) this).getInteractArea();
			gc.setFill(Color.color(1, 1, 1, 0.2));
			gc.setStroke(Color.WHITE);
			gc.setLineWidth(interactAreaBorder);
			gc.strokeRoundRect(interactArea.getMinX() - camX, interactArea.getMinY() - camY, interactArea.getWidth(),
					interactArea.getHeight(), 20, 20);
			gc.fillRoundRect(interactArea.getMinX() - camX, interactArea.getMinY() - camY, interactArea.getWidth(),
					interactArea.getHeight(), 20, 20);
		}

		// Render Image
		gc.drawImage(image, 0, 0, this.image.getWidth(), this.image.getHeight(), x - camX, y - camY,
				this.image.getWidth(), this.image.getHeight());

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
		return this.y + this.getHitbox().getHeight();
	}

	public void setInteractAreaBorder(int i) {
		this.interactAreaBorder = i;
	}
}

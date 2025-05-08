package logic.object;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.components.Player;

public abstract class GameObject implements Collidable {

	protected double x, y;
	protected Image image;
	protected Rectangle2D hitbox;
	protected String name;

	public GameObject(String name, double x, double y, String imageName, Rectangle2D hitbox) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.image = new Image(ClassLoader.getSystemResource("Images/" + imageName + ".png").toString());
		this.hitbox = hitbox;
	}

	public abstract void onInteract(Player player); // โต้ตอบ

	public void render(GraphicsContext gc, double camX, double camY) {
		gc.drawImage(image, 0, 0, this.image.getWidth(), this.image.getHeight(), x - camX, y - camY,
				this.image.getWidth(), this.image.getHeight());
		// Render Hitbox
		gc.setStroke(Color.RED);
		gc.setLineWidth(2);
		gc.strokeRect(hitbox.getMinX() - camX, hitbox.getMinY() - camY, hitbox.getWidth(), hitbox.getHeight());

	}

	@Override
	public Rectangle2D getHitbox() {
		return this.hitbox;
	}
}

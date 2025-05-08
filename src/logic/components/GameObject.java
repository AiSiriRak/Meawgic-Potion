package logic.components;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class GameObject {

	protected double x, y;
	protected Image image;

	public GameObject(double x, double y, String imageName) {
		this.x = x;
		this.y = y;
		this.image = new Image(ClassLoader.getSystemResource("Images/" + imageName + ".png").toString());
	}

	public abstract void onInteract(Player player); // โต้ตอบ

	public void render(GraphicsContext gc, double camX, double camY) {
		gc.drawImage(image, 0, 0, 96, 96, x - camX, y - camY, 192, 192);
	}

	public boolean isColliding(Player player) {
		return player.getBounds().intersects(this.getBounds());
	}

	public Rectangle2D getBounds() {
		return new Rectangle2D(x, y, image.getWidth(), image.getHeight());
	}

}

package logic.components;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.object.Collidable;
import logic.object.Renderable;

public class Player implements Collidable, Renderable {
	private double x, y;
	final static double SPEED = 10;
	final static double SIZE = 64;
	private Image playerImage;

	private Rectangle2D hitbox;

	public Player(double x, double y) {
		this.x = x;
		this.y = y;
		this.playerImage = new Image(ClassLoader.getSystemResource("Images/" + "Player_Down1" + ".png").toString());

	}

	public void render(GraphicsContext gc, double camX, double camY) {
		gc.drawImage(playerImage, getPosX() - camX, getPosY() - camY, SIZE, SIZE);

		// Render Hitbox
		this.hitbox = new Rectangle2D(x + SIZE / 4, y + SIZE / 1.6, SIZE / 2, SIZE / 3.2);
		gc.setStroke(Color.AQUA);
		gc.setLineWidth(2);
		gc.strokeRect(hitbox.getMinX() - camX, hitbox.getMinY() - camY, hitbox.getWidth(), hitbox.getHeight());

	}

	public void setImage(String name) {
		this.playerImage = new Image(ClassLoader.getSystemResource("Images/Player_" + name + ".png").toString());
	}

	public void setPosX(double x) {
		this.x = x;
	}

	public void setPosY(double y) {
		this.y = y;
	}

	public double getPosX() {
		return x;
	}

	public double getPosY() {
		return y;
	}

	public double getY() {
		return y + SIZE;
	}

	public Rectangle2D getHitbox() {
		return this.hitbox;

	}

	public Rectangle2D getHitbox(double nextX, double nextY) {
		return new Rectangle2D(SIZE / 4 + nextX, SIZE / 1.6 + nextY, SIZE / 2, SIZE / 3.2);

	}
}

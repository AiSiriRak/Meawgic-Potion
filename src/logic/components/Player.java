package logic.components;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.object.Collidable;
import logic.object.Renderable;

public class Player implements Collidable, Renderable, DoAnimation {
	private double x, y;
	final static double SPEED = 8;
	final static double SIZE = 64;
	private Image playerImage;

	private Animation walkDownAnimation;
	private Animation walkUpAnimation;
	private Animation walkLeftAnimation;
	private Animation walkRightAnimation;
	final private int fps = 6;
	private String direction;

	private Rectangle2D hitbox;

	public Player(double x, double y) {
		this.x = x;
		this.y = y;
		this.setImage(new Image(ClassLoader.getSystemResource("Images/walk_down_1.png").toString()));
		this.direction = "down";
		setAnimation();

	}

	public void render(GraphicsContext gc, double camX, double camY) {
		gc.drawImage(playerImage, getPosX() - camX, getPosY() - camY, SIZE, SIZE);

		this.hitbox = new Rectangle2D(x + SIZE / 4, y + SIZE / 1.6, SIZE / 2, SIZE / 3.2);

	}

	public void setAnimation() {
		Image[] walkDownFrames = new Image[] {
				new Image(ClassLoader.getSystemResource("Images/walk_down_1.png").toString()),
				new Image(ClassLoader.getSystemResource("Images/walk_down_2.png").toString()),
				new Image(ClassLoader.getSystemResource("Images/walk_down_3.png").toString()) };
		walkDownAnimation = new Animation(walkDownFrames, fps);

		Image[] walkUpFrames = new Image[] {
				new Image(ClassLoader.getSystemResource("Images/walk_up_1.png").toString()),
				new Image(ClassLoader.getSystemResource("Images/walk_up_2.png").toString()),
				new Image(ClassLoader.getSystemResource("Images/walk_up_3.png").toString()) };
		walkUpAnimation = new Animation(walkUpFrames, fps);

		Image[] walkLeftFrames = new Image[] {
				new Image(ClassLoader.getSystemResource("Images/walk_left_1.png").toString()),
				new Image(ClassLoader.getSystemResource("Images/walk_left_2.png").toString()),
				new Image(ClassLoader.getSystemResource("Images/walk_left_3.png").toString()) };
		walkLeftAnimation = new Animation(walkLeftFrames, fps);

		Image[] walkRightFrames = new Image[] {
				new Image(ClassLoader.getSystemResource("Images/walk_right_1.png").toString()),
				new Image(ClassLoader.getSystemResource("Images/walk_right_2.png").toString()),
				new Image(ClassLoader.getSystemResource("Images/walk_right_3.png").toString()) };
		walkRightAnimation = new Animation(walkRightFrames, fps);
	}

	public void updateAnimation() {
		switch (this.direction) {
		case "up":
			walkUpAnimation.update();
			setImage(walkUpAnimation.getCurrentFrame());
			break;
		case "down":
			walkDownAnimation.update();
			setImage(walkDownAnimation.getCurrentFrame());
			break;
		case "left":
			walkLeftAnimation.update();
			setImage(walkLeftAnimation.getCurrentFrame());
			break;
		case "right":
			walkRightAnimation.update();
			setImage(walkRightAnimation.getCurrentFrame());
			break;
		default:
			walkDownAnimation.update();
			setImage(walkDownAnimation.getCurrentFrame());

		}

	}

	public void setImage(Image image) {
		this.playerImage = image;
	}

	public void setDirection(String direction) {
		this.direction = direction;
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

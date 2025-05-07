package logic.components;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player {
	private double x, y;
	final static double SPEED = 6;
	final static double SIZE = 96;
	private Image playerImage;

	public Player(double x, double y) {
		this.x = x;
		this.y = y;
		this.playerImage = new Image(ClassLoader.getSystemResource("Images/" + "Player_Down1" + ".png").toString());
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(playerImage, getX(), getY(), SIZE, SIZE);
	}

//	public Image getCurrentImage() {
//		String name = switch (direction) {
//		case UP -> "player_up.png";
//		case DOWN -> "player_down.png";
//		case LEFT -> "player_left.png";
//		case RIGHT -> "player_right.png";
//		};
//		return new Image(getClass().getResourceAsStream("/images/" + name));
//	}

	public void setImage(String name) {
		this.playerImage = new Image(ClassLoader.getSystemResource("Images/Player_" + name + ".png").toString());
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}

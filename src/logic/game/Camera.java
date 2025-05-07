package logic.game;

import logic.components.Map;
import logic.components.Player;

public class Camera {
	private double offsetX, offsetY;
	private final Player player;
	private int mapWidth, mapHeight;

	public Camera(Player player, Map map) {
		this.player = player;
		this.mapWidth = map.getMapWidth();
		this.mapHeight = map.getMapHeight();
		this.offsetX = player.getX() - GameController.SCREEN_WIDTH / 2;
		this.offsetY = player.getY() - GameController.SCREEN_HEIGHT / 2;
	}

	public void update() {
		int scWidth = GameController.SCREEN_WIDTH;
		int scHeight = GameController.SCREEN_HEIGHT;
		offsetX = Math.max(0, Math.min(this.player.getX() - scWidth / 2, this.mapWidth - scWidth));
		offsetY = Math.max(0, Math.min(this.player.getY() - scHeight / 2, this.mapHeight - scHeight));
	}

	public double getOffsetX() {
		return offsetX;
	}

	public double getOffsetY() {
		return offsetY;
	}
}

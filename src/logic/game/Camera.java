package logic.game;

import logic.components.Map;
import logic.components.Player;

public class Camera {
	private double offsetX, offsetY;
	private final Player player;
	private double mapWidth;
	private double mapHeight;

	public Camera(Player player, Map map) {
		this.player = player;
		this.mapWidth = map.getMapWidth();
		this.mapHeight = map.getMapHeight();
	}

	public void update() {
		offsetX = Math.max(0, Math.min(this.player.getPosX() - GameController.getScreenWidth() / 2,
				this.mapWidth - GameController.getScreenWidth()));
		offsetY = Math.max(0, Math.min(this.player.getPosY() - GameController.getScreenHeight() / 2,
				this.mapHeight - GameController.getScreenHeight()));
	}

	public double getOffsetX() {
		return offsetX;
	}

	public double getOffsetY() {
		return offsetY;
	}
}

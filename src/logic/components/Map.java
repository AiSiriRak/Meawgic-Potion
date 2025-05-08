package logic.components;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.game.Camera;
import logic.game.GameController;
import logic.game.KeyboardController;
import logic.object.GameObject;
import logic.object.Interactable;

public abstract class Map extends Canvas {

	protected int mapWidth;
	protected int mapHeight;
	protected Image bg;
	private String name;
	private Player player;
	private Camera camera;
	ArrayList<GameObject> gameObjectList;
	private boolean isEHandled = false;

	public Map(int mapWidth, int mapHeight, String name, int playerStartX, int playerStartY,
			ArrayList<GameObject> gameObjectList) {
		super(mapWidth, mapHeight);
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		this.name = name;
		this.bg = new Image(ClassLoader.getSystemResource("Images/" + name + ".png").toString());
		this.player = new Player(playerStartX, playerStartY);
		this.camera = new Camera(player, this);
		this.gameObjectList = gameObjectList;

		updateCanvas(getGraphicsContext2D());

	}

	public void updateCanvas(GraphicsContext gc) {

		int scWidth = GameController.SCREEN_WIDTH;
		int scHeight = GameController.SCREEN_HEIGHT;
		Thread updateCanvas = new Thread(() -> {
			while (true) {
				try {

					double cameraX = camera.getOffsetX();
					double cameraY = camera.getOffsetY();
					Platform.runLater(new Runnable() {
						@Override
						public void run() {

							gc.clearRect(0, 0, GameController.SCREEN_WIDTH, GameController.SCREEN_HEIGHT);

							gc.drawImage(bg, cameraX, cameraY, scWidth, scHeight, 0, 0, scWidth, scHeight);
							playerMove();
							player.render(gc, cameraX, cameraY);
							for (GameObject go : gameObjectList) {
								go.render(gc, cameraX, cameraY);
								if (go instanceof Interactable) {
									((Interactable) go).interactAreaRender(gc, cameraX, cameraY);
								}
							}

							camera.update();

						}
					});
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, "UpdateCanvas : " + name);
		updateCanvas.start();
	}

	public boolean willCollide(Player player, double nextX, double nextY) {
		Rectangle2D futurePlayerBounds = player.getHitbox(nextX, nextY);

		for (GameObject obj : gameObjectList) {
			if (futurePlayerBounds.intersects(obj.getHitbox())) {
				return true;

			}
		}
		return false;
	}

	public void interact() {
		KeyboardController keyboard = GameController.getKeyboardController();
		if (keyboard.isEPressed() && !isEHandled) {
			for (GameObject obj : gameObjectList) {
				if (obj instanceof Interactable) {
					if (((Interactable) obj).getInteractArea().contains(player.getHitbox())) {
						((Interactable) obj).interact();
					}
				}
			}
			isEHandled = true;
		}
		if (!keyboard.isEPressed()) {
			isEHandled = false;
		}
	}

	public void playerMove() {

		KeyboardController keyboard = GameController.getKeyboardController();

		if (keyboard.isUpPressed()) {
			if (player.getY() > 0 && !this.willCollide(player, player.getX(), player.getY() - Player.SPEED)) {
				player.setY(player.getY() - Player.SPEED);
			}
		}
		if (keyboard.isDownPressed()) {
			if (player.getY() < this.mapHeight - Player.SIZE
					&& !this.willCollide(player, player.getX(), player.getY() + Player.SPEED)) {
				player.setY(player.getY() + Player.SPEED);
				player.setImage("Down1");
			}
		}
		if (keyboard.isLeftPressed()) {
			if (player.getX() > 0 && !this.willCollide(player, player.getX() - Player.SPEED, player.getY())) {
				player.setX(player.getX() - Player.SPEED);
			}
		}
		if (keyboard.isRightPressed()) {
			if (player.getX() < this.mapWidth - Player.SIZE
					&& !this.willCollide(player, player.getX() + Player.SPEED, player.getY())) {
				player.setX(player.getX() + Player.SPEED);
			}
		}
		this.interact();

	}

	public int getMapWidth() {
		return this.mapWidth;
	}

	public int getMapHeight() {
		return this.mapHeight;
	}

	public String getName() {
		return this.name;
	}

}

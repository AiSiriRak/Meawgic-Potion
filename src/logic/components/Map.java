package logic.components;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
import logic.object.Renderable;

public abstract class Map extends Canvas {

	protected double mapWidth;
	protected double mapHeight;

	protected int playerStartX;
	protected int playerStartY;

	protected Image background;
	protected Player player;
	protected Camera camera;

	protected Rectangle2D mapEdge;

	protected ArrayList<GameObject> gameObjectList;

	protected List<Renderable> renderables = new ArrayList<>();

	protected boolean isEHandled = false;

	public Map(Image background, Rectangle2D mapEdge, int playerStartX, int playerStartY,
			ArrayList<GameObject> gameObjectList) {
		super(background.getWidth(), background.getHeight());
		this.mapWidth = background.getWidth();
		this.mapHeight = background.getHeight();
		this.background = background;
		this.playerStartX = playerStartX;
		this.playerStartY = playerStartY;
		this.player = new Player(playerStartX, playerStartY);
		this.camera = new Camera(player, this);
		this.gameObjectList = gameObjectList;
		this.mapEdge = mapEdge;

		updateCanvas();

	}

	public void updateCanvas() {
		GraphicsContext gc = getGraphicsContext2D();
		gc.setImageSmoothing(false);

		int scWidth = GameController.getScreenWidth();
		int scHeight = GameController.getScreenHeight();
		Thread updateCanvas = new Thread(() -> {
			while (true) {
				try {

					double cameraX = camera.getOffsetX();
					double cameraY = camera.getOffsetY();
					Platform.runLater(new Runnable() {
						@Override
						public void run() {

							gc.clearRect(0, 0, GameController.getScreenWidth(), GameController.getScreenHeight());

							gc.drawImage(background, cameraX, cameraY, scWidth, scHeight, 0, 0, scWidth, scHeight);
							playerMove();

							for (GameObject obj : gameObjectList) {
								if (obj instanceof DoAnimation) {
									((DoAnimation) obj).updateAnimation();
								}
							}

							renderables.clear();
							renderables.addAll(gameObjectList);
							renderables.add(player);
							renderables.sort(Comparator.comparingDouble(Renderable::getY));

							for (Renderable r : renderables) {
								r.render(gc, cameraX, cameraY);
							}

							camera.update();

						}
					});
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});
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

		for (GameObject obj : gameObjectList) {
			if (obj instanceof Interactable) {
				if (((Interactable) obj).getInteractArea().contains(player.getHitbox())
						&& ((Interactable) obj).getCanInteract()) {
					obj.setInteractAreaBorder(5);

					if (keyboard.isEPressed() && !isEHandled) {
						((Interactable) obj).interact();
						isEHandled = true;
					}
					if (!keyboard.isEPressed()) {
						isEHandled = false;
					}
				} else {
					obj.setInteractAreaBorder(2);
				}
			}
		}

	}

	public void playerMove() {

		KeyboardController keyboard = GameController.getKeyboardController();
		if (keyboard.isUpPressed()) {
			if (player.getPosY() > this.mapEdge.getMinY()
					&& !this.willCollide(player, player.getPosX(), player.getPosY() - Player.SPEED)) {
				player.setPosY(player.getPosY() - Player.SPEED);
				player.setDirection("up");
				player.updateAnimation();
			}
		}
		if (keyboard.isDownPressed()) {
			if (player.getPosY() < this.mapEdge.getMaxY() - Player.SIZE
					&& !this.willCollide(player, player.getPosX(), player.getPosY() + Player.SPEED)) {
				player.setPosY(player.getPosY() + Player.SPEED);
				player.setDirection("down");
				player.updateAnimation();
			}
		}
		if (keyboard.isLeftPressed()) {
			if (player.getPosX() > this.mapEdge.getMinX()
					&& !this.willCollide(player, player.getPosX() - Player.SPEED, player.getPosY())) {
				player.setPosX(player.getPosX() - Player.SPEED);
				player.setDirection("left");
				player.updateAnimation();
			}
		}
		if (keyboard.isRightPressed()) {
			if (player.getPosX() < this.mapEdge.getMaxX() - Player.SIZE
					&& !this.willCollide(player, player.getPosX() + Player.SPEED, player.getPosY())) {
				player.setPosX(player.getPosX() + Player.SPEED);
				player.setDirection("right");
				player.updateAnimation();

			}
		}

		this.interact();
	}

	public double getMapWidth() {
		return this.mapWidth;
	}

	public double getMapHeight() {
		return this.mapHeight;
	}

	public void resetPlayerPos() {
		this.player.setPosX(playerStartX);
		this.player.setPosY(playerStartY);
	}

	public List<GameObject> getGameObjects() {
	    return gameObjectList;
	}

}

package logic.components;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.game.Camera;
import logic.game.GameController;
import logic.game.KeyboardController;

public class Map extends Canvas {

	protected int mapWidth;
	protected int mapHeight;
	protected Image bg;
	private String name;
	private Player player;
	private Camera camera;

	public Map(int mapWidth, int mapHeight, String name) {
		super(mapWidth, mapHeight);
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		this.name = name;
		this.bg = new Image(ClassLoader.getSystemResource("Images/" + name + ".png").toString());
		this.player = new Player(mapWidth / 2, mapHeight / 2);
		this.camera = new Camera(player, this);
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
					System.out.println(player.getX() + " , " + cameraX + " , " + mapWidth);
					Platform.runLater(new Runnable() {
						@Override
						public void run() {

							gc.clearRect(0, 0, GameController.SCREEN_WIDTH, GameController.SCREEN_HEIGHT);

							playerMove();
							gc.drawImage(bg, camera.getOffsetX(), camera.getOffsetY(), scWidth, scHeight, 0, 0, scWidth,
									scHeight);
							player.render(gc);
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

	public void playerMove() {
		KeyboardController keyboard = GameController.getKeyboardController();

		if (keyboard.isUpPressed()) {
			if (player.getY() > 0) {
				player.setY(player.getY() - Player.SPEED);
			}
		}
		if (keyboard.isDownPressed()) {
			if (player.getY() < this.mapHeight - Player.SIZE) {
				player.setY(player.getY() + Player.SPEED);
				player.setImage("Down1");
			}
		}
		if (keyboard.isLeftPressed()) {
			if (player.getX() > 0) {
				player.setX(player.getX() - Player.SPEED);
			}
		}
		if (keyboard.isRightPressed()) {
			if (player.getX() < this.mapWidth - Player.SIZE) {
				player.setX(player.getX() + Player.SPEED);
			}
		}
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

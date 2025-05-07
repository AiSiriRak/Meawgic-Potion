package logic.game;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import logic.components.Map;
import logic.components.OutsideMap;

public class GameController {

	public static final int TILE_SIZE = 32;

	public static final int SCREEN_WIDTH = 960;
	public static final int SCREEN_HEIGHT = 600;

	private static Scene scene;
	private static StackPane root;
	private static Map map;
	private static KeyboardController keyboardController;

	public static void setupScene() {
		root = new StackPane();

		scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		keyboardController = new KeyboardController();
		map = new OutsideMap();
		root.getChildren().add(map);
	}

	public static Scene getScene() {
		return scene;
	}

	public static KeyboardController getKeyboardController() {
		return keyboardController;
	}
}

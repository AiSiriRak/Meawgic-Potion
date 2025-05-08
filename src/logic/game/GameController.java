package logic.game;

import application.Main;
import gui.button.InventoryButton;
import gui.button.SettingButton;
import gui.pane.InventoryPane;
import gui.pane.SettingPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import logic.components.InsideMap;
import logic.components.Map;
import logic.components.OutsideMap;

public class GameController {
	public static final int TILE_SIZE = 32;
	public static final int SCREEN_WIDTH = 960;
	public static final int SCREEN_HEIGHT = 600;

	private static Scene scene;
	private static Pane root;
	private static KeyboardController keyboardController;
	private static Map map;

	public static void setupScene() {
		try {
			StackPane layeredRoot = new StackPane();
			scene = new Scene(layeredRoot, SCREEN_WIDTH, SCREEN_HEIGHT, Color.BLACK);

			// Base map
			root = new Pane();

			Map outsideMap = new OutsideMap();
			Map insideMap = new InsideMap();

			root.getChildren().add(outsideMap);

			// UI Components
			InventoryButton inventoryButton = new InventoryButton();
			SettingButton settingButton = new SettingButton();
			InventoryPane inventoryPane = new InventoryPane();
			SettingPane settingPane = new SettingPane(Main.getPrimaryStage());

			inventoryPane.setVisible(false);
			settingPane.setVisible(false);

			// Button container
			HBox overlay = new HBox(10);
			overlay.setAlignment(Pos.BOTTOM_LEFT);
			overlay.setPadding(new Insets(10));
			overlay.getChildren().addAll(settingButton, inventoryButton);
			StackPane.setAlignment(overlay, Pos.TOP_LEFT);

			inventoryButton.setOnAction(e -> inventoryPane.setVisible(!inventoryPane.isVisible()));
			settingButton.setOnAction(e -> settingPane.setVisible(!settingPane.isVisible()));

			layeredRoot.getChildren().addAll(root, overlay, inventoryPane, settingPane);

			keyboardController = new KeyboardController();

			Main.getPrimaryStage().setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void resetGame() {
		setupScene();
	}

	public static Scene getScene() {
		return scene;
	}

	public static KeyboardController getKeyboardController() {
		return keyboardController;
	}
}
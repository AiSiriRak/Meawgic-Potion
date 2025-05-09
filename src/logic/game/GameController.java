package logic.game;

import application.Main;
import gui.button.InventoryButton;
import gui.button.SettingButton;
import gui.pane.InventoryPane;
import gui.pane.SettingPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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

	private static Map outsideMap;
	private static Map insideMap;
	private static Map currentMap;

	public static WaterBar waterBar;

	public static void setupScene() {
		try {
			StackPane layeredRoot = new StackPane();
			scene = new Scene(layeredRoot, SCREEN_WIDTH, SCREEN_HEIGHT, Color.BLACK);

			// Base map
			root = new Pane();

			keyboardController = new KeyboardController();

			outsideMap = new OutsideMap();
			insideMap = new InsideMap();
			currentMap = outsideMap;

			root.getChildren().add(currentMap);

			// UI Components
			InventoryButton inventoryButton = new InventoryButton();
			SettingButton settingButton = new SettingButton();
			InventoryPane inventoryPane = new InventoryPane();
			SettingPane settingPane = new SettingPane(Main.getPrimaryStage());

			waterBar = new WaterBar();

			inventoryPane.setVisible(false);
			settingPane.setVisible(false);

			// Button container
			HBox overlay = new HBox(10);
			overlay.setAlignment(Pos.BOTTOM_LEFT);
			overlay.setPadding(new Insets(10));
			overlay.getChildren().addAll(settingButton, inventoryButton);
			StackPane.setAlignment(overlay, Pos.TOP_LEFT);

			inventoryButton.setOnMouseEntered(e -> {
				inventoryButton.setScaleX(1.08);
				inventoryButton.setScaleY(1.08);
			});
			inventoryButton.setOnMouseExited(e -> {
				inventoryButton.setScaleX(1);
				inventoryButton.setScaleY(1);
			});
			settingButton.setOnMouseEntered(e -> {
				settingButton.setScaleX(1.08);
				settingButton.setScaleY(1.08);
			});
			settingButton.setOnMouseExited(e -> {
				settingButton.setScaleX(1);
				settingButton.setScaleY(1);
			});
			inventoryButton.setOnAction(e -> inventoryPane.setVisible(!inventoryPane.isVisible()));
			settingButton.setOnAction(e -> settingPane.setVisible(!settingPane.isVisible()));

			layeredRoot.getChildren().addAll(root, overlay, inventoryPane, settingPane, waterBar);

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

	public static void switchCurrentMap() {
		if (currentMap == outsideMap) {
			insideMap.resetPlayerPos();
			currentMap = insideMap;
		} else {
			outsideMap.resetPlayerPos();
			currentMap = outsideMap;
		}
		root.getChildren().clear();
		root.getChildren().add(currentMap);
	}

}
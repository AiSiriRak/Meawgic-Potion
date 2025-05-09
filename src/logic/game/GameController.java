package logic.game;


import java.util.ArrayList;

import Font.FontRect;
import Inventory.IngredientCounter;
import Inventory.PotionCounter;
import application.Main;
import gui.button.InventoryButton;
import gui.button.SettingButton;
import gui.pane.ControlBrewing;
import gui.pane.InventoryPane;
import gui.pane.SettingPane;
import gui.pane.ShopPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.components.InsideMap;
import logic.components.Map;
import logic.components.OutsideMap;

public class GameController {
	public static final int SCREEN_WIDTH = 960;
	public static final int SCREEN_HEIGHT = 600;

	private static Scene scene;
	private static Pane root;
	private static KeyboardController keyboardController;

	private static Map outsideMap;
	private static Map insideMap;
	private static Map currentMap;

	private static ControlBrewing controlBrewing;

	public static StackPane warningPane;
	public static WaterBar waterBar;

	private static InventoryPane inventoryPane;
	
	private static IngredientCounter sharedIngredientCounter = new IngredientCounter();
	private static PotionCounter sharedPotionCounter = new PotionCounter();
	
	private static ArrayList<ControlBrewing> controlBrewings = new ArrayList<>();
    private static ControlBrewing currentControlBrewing;
    

	public static ShopPane shopPane;

	public static void setupScene() {
		try {
			StackPane layeredRoot = new StackPane();
			scene = new Scene(layeredRoot, SCREEN_WIDTH, SCREEN_HEIGHT, Color.BLACK);

			root = new Pane();

			keyboardController = new KeyboardController();

			outsideMap = new OutsideMap();
			insideMap = new InsideMap();
			currentMap = outsideMap;

			root.getChildren().add(currentMap);

			InventoryButton inventoryButton = new InventoryButton();
			SettingButton settingButton = new SettingButton();
			inventoryPane = new InventoryPane(sharedIngredientCounter, sharedPotionCounter);
			SettingPane settingPane = new SettingPane(Main.getPrimaryStage());

			shopPane = new ShopPane();

			// Set WaterBar
			warningPane = new StackPane();
			Rectangle warningBg = new Rectangle(160, 36);
			warningBg.setFill(Color.web("#FAF5DF"));
			Text warning = new Text("No Water!");
			warning.setFont(FontRect.BOLD.getFont(24));
			warning.setFill(Color.web("#34022A"));
			warningPane.getChildren().addAll(warningBg, warning);
			warningPane.setTranslateX(300);
			warningPane.setVisible(false);

			waterBar = new WaterBar();

			// Set InventoryPan && SettingPane
			inventoryPane.setVisible(false);
			settingPane.setVisible(false);

			shopPane.setVisible(false);

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



			for (ControlBrewing cb : controlBrewings) {
                cb.setVisible(false);
                layeredRoot.getChildren().add(cb);
            }
            

			layeredRoot.getChildren().addAll(root, overlay, inventoryPane, settingPane, shopPane, warningPane, waterBar);


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

	public static ControlBrewing getControlBrewing() {
		return controlBrewing;
	}

	public static Pane getRoot() {
		return root;
	}

	public static InventoryPane getInventoryPane() {
		return inventoryPane;
	}

	public static IngredientCounter getSharedIngredientCounter() {
		return sharedIngredientCounter;
	}

	public static void setSharedIngredientCounter(IngredientCounter sharedIngredientCounter) {
		GameController.sharedIngredientCounter = sharedIngredientCounter;
	}

	public static PotionCounter getSharedPotionCounter() {
		return sharedPotionCounter;
	}

	public static void setSharedPotionCounter(PotionCounter sharedPotionCounter) {
		GameController.sharedPotionCounter = sharedPotionCounter;
	}
	
	public static void addControlBrewing(ControlBrewing controlBrewing) {
        controlBrewings.add(controlBrewing);
    }

    public static void setCurrentControlBrewing(ControlBrewing controlBrewing) {
        currentControlBrewing = controlBrewing;
    }

    public static ControlBrewing getCurrentControlBrewing() {
        return currentControlBrewing;
    }
}
package logic.game;

import java.util.ArrayList;

import Font.FontRect;
import Inventory.IngredientCounter;
import Inventory.PotionCounter;
import application.Main;
import gui.ControlBrewing;
import gui.GameButton;
import gui.InventoryPane;
import gui.PlantPane;
import gui.SettingPane;
import gui.ShopPane;
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
	public static Map currentMap;

	private static InventoryPane inventoryPane;
	private static ControlBrewing controlBrewing;
	private static PlantPane plantPane;

	private static ArrayList<ControlBrewing> controlBrewings = new ArrayList<>();
	private static ControlBrewing currentControlBrewing;

	private static ArrayList<PlantPane> plantPanes = new ArrayList<>();
	private static PlantPane currentPlantPane;

	public static StackPane warningWaterPane;
	public static WaterBar waterBar;

	private static IngredientCounter sharedIngredientCounter = new IngredientCounter();
	private static PotionCounter sharedPotionCounter = new PotionCounter();

	public static ShopPane shopPane;

	public static Coin coin;
	private static String coinText;
	private static StackPane coinPane;
	public static StackPane warningCoinPane;

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

			GameButton inventoryButton = new GameButton("Inventory", 64);
			inventoryPane = new InventoryPane(sharedIngredientCounter, sharedPotionCounter);
			GameButton settingButton = new GameButton("setting", 64);
			SettingPane settingPane = new SettingPane(Main.getPrimaryStage());

			shopPane = new ShopPane();

			// Set Water Warning Pane
			warningWaterPane = getTextPane(160, 36, "No Water!!!");
			warningWaterPane.setTranslateX(300);
			warningWaterPane.setVisible(false);

			// Set Coin Warning Pane
			warningCoinPane = getTextPane(300, 36, "Not Enough Money!!!");
			warningCoinPane.setTranslateY(150);
			warningCoinPane.setVisible(false);

			// Set Waterbar
			waterBar = new WaterBar();

			// Set InventoryPan && SettingPane
			inventoryPane.setVisible(false);
			settingPane.setVisible(false);

			shopPane.setVisible(false);

			// Setup Coin
			coin = new Coin();
			updateCoinDisplay();
			coinPane = getTextPane(200, 36, coinText);
			coinPane.setTranslateX(360);
			coinPane.setTranslateY(-270);

			// Button container
			HBox overlay = new HBox(10);
			overlay.setAlignment(Pos.BOTTOM_LEFT);
			overlay.setPadding(new Insets(10));
			overlay.getChildren().addAll(settingButton, inventoryButton);
			StackPane.setAlignment(overlay, Pos.TOP_LEFT);

			inventoryButton.setOnAction(e -> inventoryPane.setVisible(!inventoryPane.isVisible()));
			settingButton.setOnAction(e -> settingPane.setVisible(!settingPane.isVisible()));

			for (ControlBrewing cb : controlBrewings) {
				cb.setVisible(false);
				layeredRoot.getChildren().add(cb);
			}

			for (PlantPane p : plantPanes) {
				System.out.println(3);
				p.setVisible(false);
				layeredRoot.getChildren().add(p);
			}

			layeredRoot.getChildren().addAll(root, warningWaterPane, warningCoinPane, waterBar, coinPane, overlay,
					inventoryPane, settingPane, shopPane);

			Main.getPrimaryStage().setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static StackPane getTextPane(int width, int height, String word) {
		Text text = new Text(word);
		text.setFont(FontRect.BOLD.getFont(24));
		text.setFill(Color.web("#34022A"));

		StackPane textPane = new StackPane();
		Rectangle textBg = new Rectangle(width, height);
		textBg.setFill(Color.web("#FAF5DF"));

		textPane.getChildren().addAll(textBg, text);

		return textPane;
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
		if (currentMap instanceof OutsideMap) {
			insideMap.resetPlayerPos();
			currentMap = insideMap;
		} else {
			outsideMap.resetPlayerPos();
			currentMap = outsideMap;
		}
		root.getChildren().clear();
		root.getChildren().add(currentMap);
	}

	public static void updateCoinDisplay() {
		if (coin != null) {
			coinText = "Coins : " + coin.getCoin();
		}
	}

	public static void addControlBrewing(ControlBrewing controlBrewing) {
		controlBrewings.add(controlBrewing);
	}

	public static void addPlantPane(PlantPane plantPane) {
		plantPanes.add(plantPane);
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

	public static void setCurrentControlBrewing(ControlBrewing controlBrewing) {
		currentControlBrewing = controlBrewing;
	}

	public static ControlBrewing getCurrentControlBrewing() {
		return currentControlBrewing;
	}

	public static PlantPane getCurrentPlantPane() {
		return currentPlantPane;
	}

	public static void setCurrentPlantPane(PlantPane currentPlantPane) {
		GameController.currentPlantPane = currentPlantPane;
	}

	public static PlantPane getPlantPane() {
		return plantPane;
	}
}
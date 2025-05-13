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
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.components.InsideMap;
import logic.components.Map;
import logic.components.OutsideMap;
import logic.object.CropPlot;
import logic.object.GameObject;
import logic.object.Pot;

public class GameController {
	private static final int SCREEN_WIDTH = 960;
	private static final int SCREEN_HEIGHT = 600;

	private static Scene scene;
	private static Pane root;
	private static KeyboardController keyboardController;

	private static Map outsideMap;
	private static Map insideMap;
	private static Map currentMap;

	private static InventoryPane inventoryPane;
	private static ControlBrewing controlBrewing;
	private static PlantPane plantPane;

	private static ArrayList<ControlBrewing> controlBrewings = new ArrayList<>();
	private static ControlBrewing currentControlBrewing;

	private static ArrayList<PlantPane> plantPanes = new ArrayList<>();
	private static PlantPane currentPlantPane;

	private static StackPane warningWaterPane;
	private static WaterBar waterBar;

	private static IngredientCounter sharedIngredientCounter = new IngredientCounter();
	private static PotionCounter sharedPotionCounter = new PotionCounter();

	private static ShopPane shopPane;

	private static Coin coin;
	private static Text coinText;
	private static StackPane warningCoinPane;

	public static void setupScene() {
		try {
			StackPane layeredRoot = new StackPane();
			scene = new Scene(layeredRoot, SCREEN_WIDTH, SCREEN_HEIGHT, Color.BLACK);

			root = new Pane();

			keyboardController = new KeyboardController();
			
			// Set Waterbar
			waterBar = new WaterBar();
			
			if (outsideMap == null) {
			    outsideMap = new OutsideMap();
			}
			if (insideMap == null) {
			    insideMap = new InsideMap();
			}
			
			currentMap = outsideMap;

			root.getChildren().add(currentMap);

			// Initialize Coin FIRST
			coinText = new Text();
			updateCoinDisplay();
			coinText.setFont(FontRect.BOLD.getFont(20));
			StackPane.setAlignment(coinText, Pos.TOP_RIGHT);
			StackPane.setMargin(coinText, new Insets(10, 20, 0, 0));

			coin = new Coin();
			updateCoinDisplay();
			coinText.setFill(Color.WHITE);

			// Game Button
			GameButton inventoryButton = new GameButton("Inventory", 64, "Click_ingredient");
			inventoryPane = new InventoryPane(sharedIngredientCounter, sharedPotionCounter);

			GameButton settingButton = new GameButton("Setting", 64, "Click_ingredient");
			SettingPane settingPane = new SettingPane(Main.getPrimaryStage());

			Image PotionGuildeImg = new Image(ClassLoader.getSystemResource("Images/PotionGuide.png").toString());
			BackgroundImage bg = new BackgroundImage(PotionGuildeImg, BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
					new BackgroundSize(500, 384.6, false, false, false, false));
			GameButton potionGuideButton = new GameButton("PotionGuide", 64, "Click_ingredient");

			// Potion's Guide Page
			StackPane potionGuide = new StackPane();
			potionGuide.setBackground(new Background(bg));
			GameButton exitButton = new GameButton("Exit", "Click_ingredient");
			exitButton.setTranslateX(240);
			exitButton.setTranslateY(-180);
			exitButton.setOnAction(e -> {
				potionGuide.setVisible(false);
			});
			potionGuide.setVisible(false);

			potionGuideButton.setOnMouseClicked(e -> {
				potionGuide.setVisible(!potionGuide.isVisible());
				exitButton.setVisible(true);
			});

			potionGuide.getChildren().add(exitButton);

			// Shop Pane
			shopPane = new ShopPane();
			shopPane.setVisible(false);

			// Set Warning Pane
			warningWaterPane = getTextPane(160, 36, new Text("No Water!!!"));
			warningWaterPane.setTranslateX(300);
			warningWaterPane.setVisible(false);

			warningCoinPane = getTextPane(300, 36, new Text("Not Enough Money!!!"));
			warningCoinPane.setTranslateY(200);
			warningCoinPane.setVisible(false);

			// Set InventoryPan && SettingPane
			inventoryPane.setVisible(false);
			settingPane.setVisible(false);

			// Button container
			HBox overlay = new HBox(10);
			overlay.setAlignment(Pos.BOTTOM_LEFT);
			overlay.setPadding(new Insets(10));
			overlay.getChildren().addAll(settingButton, inventoryButton, potionGuideButton);
			StackPane.setAlignment(overlay, Pos.TOP_LEFT);

			inventoryButton.setOnAction(e -> inventoryPane.setVisible(!inventoryPane.isVisible()));
			settingButton.setOnAction(e -> settingPane.setVisible(!settingPane.isVisible()));
			
			for (ControlBrewing cb : controlBrewings) {
				cb.setVisible(false);
				layeredRoot.getChildren().add(cb);
			}

			for (PlantPane p : plantPanes) {
				p.setVisible(false);
				layeredRoot.getChildren().add(p);
			}

			layeredRoot.getChildren().addAll(root, overlay, inventoryPane, settingPane, potionGuide, shopPane,
					warningWaterPane, warningCoinPane, waterBar, coinText);

			Main.getPrimaryStage().setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static StackPane getTextPane(int width, int height, Text coinText) {
		Text text = coinText;
		text.setFont(FontRect.BOLD.getFont(24));
		text.setFill(Color.web("#34022A"));

		StackPane textPane = new StackPane();
		Rectangle textBg = new Rectangle(width, height);
		textBg.setFill(Color.web("#FAF5DF"));

		textPane.getChildren().addAll(textBg, text);

		return textPane;
	}

	public static void resetGame() {
		inventoryPane.refreshInventory();
		sharedIngredientCounter.reset();
		sharedPotionCounter.reset();
		
		setupScene();
		
		for (GameObject obj : GameController.outsideMap.getGameObjects()) {
			if (obj instanceof CropPlot) {
				((CropPlot) obj).reset();
			}
		}
		
		for (GameObject obj : GameController.insideMap.getGameObjects()) {
			if (obj instanceof Pot) {
				((Pot) obj).reset();
			}
		}
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
		if (coinText != null && coin != null) {
			coinText.setText("Coins : " + coin.getCoins());
		}
	}

	public static void addControlBrewing(ControlBrewing controlBrewing) {
		controlBrewings.add(controlBrewing);
	}

	public static void addPlantPane(PlantPane plantPane) {
		plantPanes.add(plantPane);
	}

	public static Map getCurrentMap() {
		return currentMap;
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

	public static Scene getScene() {
		return scene;
	}

	public static KeyboardController getKeyboardController() {
		return keyboardController;
	}

	public static int getScreenWidth() {
		return SCREEN_WIDTH;
	}

	public static int getScreenHeight() {
		return SCREEN_HEIGHT;
	}

	public static Coin getCoin() {
		return coin;
	}

	public static StackPane getWarningCoinPane() {
		return warningCoinPane;
	}

	public static StackPane getWarningWaterPane() {
		return warningWaterPane;
	}

	public static WaterBar getWaterBar() {
		return waterBar;
	}

	public static ShopPane getShopPane() {
		return shopPane;
	}
	
}
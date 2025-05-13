package gui;

import java.util.ArrayList;

import Font.FontRect;
import Inventory.IngredientCounter;
import Inventory.PotionCounter;
import entity.base.Ingredient;
import entity.base.Item;
import entity.base.Potion;
import entity.base.Stone;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Duration;
import logic.game.GameController;
import logic.game.SoundController;

public class InventoryPane extends StackPane {
	private final ArrayList<InventorySquare> ingredientAllCells = new ArrayList<>();
	private final ArrayList<InventorySquare> potionAllCells = new ArrayList<>();
	private IngredientCounter ingredientCounter;
	private PotionCounter potionCounter;

	public InventoryPane(IngredientCounter ingredientCounter, PotionCounter potionCounter) {
		this.ingredientCounter = ingredientCounter;
		this.potionCounter = potionCounter;

		VBox contentBox = new VBox(10);
		Image image = new Image(ClassLoader.getSystemResource("Images/Inventory_pane.png").toString());
		BackgroundImage bgImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true));
		contentBox.setPrefSize(416, 320);
		contentBox.setMinSize(416, 320);
		contentBox.setMaxSize(416, 320);
		contentBox.setAlignment(Pos.CENTER);
		contentBox.setPadding(new Insets(10));
		contentBox.setBackground(new Background(bgImage));

		Text inventoryLabel = new Text("INVENTORY");
		inventoryLabel.setFont(FontRect.BOLD.getFont(24));
		GridPane ingredientGrid = createInventoryGrid(ingredientAllCells, ingredientCounter.getIngredientCounter());

		Text potionLabel = new Text("POTIONS");
		potionLabel.setFont(FontRect.BOLD.getFont(16));
		GridPane potionGrid = createInventoryGrid(potionAllCells, potionCounter.getPotionCounter());

		contentBox.getChildren().addAll(inventoryLabel, ingredientGrid, potionLabel, potionGrid);

		GameButton exitButton = new GameButton("Exit", "Click_ingredient");
		exitButton.setTranslateX(200);
		exitButton.setTranslateY(-150);
		exitButton.setOnMouseClicked(e -> this.setVisible(false));

		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(contentBox, exitButton);
	}

	private GridPane createInventoryGrid(ArrayList<InventorySquare> cellList, ArrayList<? extends Item> items) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(5);
		grid.setVgap(5);
		int index = 0;

		for (int row = 0; row < 2; row++) {
			for (int col = 0; col < 7; col++) {
				InventorySquare square = new InventorySquare(col, row, "Inventory");
				square.setPrefSize(48, 48);
				square.setAlignment(Pos.CENTER);
				cellList.add(square);
				grid.add(square, col, row);

				if (index < items.size()) {
					Item item = items.get(index++);
					setupSquareWithItem(square, item);

					if (item instanceof Stone && cellList == ingredientAllCells) {
						square.setOnMouseClicked(e -> {
							if (GameController.getCoin().decreaseCoin(5)) {
								GameController.getInventoryPane().addIngredient((Stone) item);
								SoundController.getInstance().playEffectSound("Buy");
							} else {
								SoundController.getInstance().playEffectSound("Wrong");
							}
						});
					}
				}
			}
		}
		return grid;
	}

	public void refreshInventory() {
		for (InventorySquare square : ingredientAllCells) {
			square.getChildren().clear();
		}
		for (InventorySquare square : potionAllCells) {
			square.getChildren().clear();
		}

		int index = 0;
		for (Ingredient ingredient : ingredientCounter.getIngredientCounter()) {
			if (index >= ingredientAllCells.size())
				break;
			setupSquareWithItem(ingredientAllCells.get(index++), ingredient);
		}

		index = 0;
		for (Potion potion : potionCounter.getPotionCounter()) {
			if (index >= potionAllCells.size())
				break;
			setupSquareWithItem(potionAllCells.get(index++), potion);
		}
	}

	public void addPotion(Potion potion) {
		for (Potion p : potionCounter.getPotionCounter()) {
			if (p.getName().equals(potion.getName())) {
				p.setAmount(p.getAmount() + 1);
				break;
			}
		}
		refreshInventory();
	}

	public void addIngredient(Ingredient ingredient) {
		for (Ingredient i : ingredientCounter.getIngredientCounter()) {
			if (i.getName().equals(ingredient.getName())) {
				i.setAmount(i.getAmount() + 1);
				break;
			}
		}
		refreshInventory();
	}

	private void setupSquareWithItem(InventorySquare square, Item item) {
		ImageView imageView = new ImageView(item.getItemImage().getImage());
		imageView.setFitWidth(35);
		imageView.setFitHeight(35);
		square.setAlignment(Pos.CENTER);
		square.getChildren().add(imageView);

		Text amountText = new Text(String.valueOf(item.getAmount()));
		amountText.setStyle("-fx-fill: white; -fx-font-size: 16;");
		StackPane.setAlignment(amountText, Pos.BOTTOM_RIGHT);
		square.getChildren().add(amountText);

		Tooltip tooltip = new Tooltip(item.getName());
		tooltip.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 12;");
		tooltip.setShowDelay(Duration.millis(300));
		tooltip.setHideDelay(Duration.millis(100));
		Tooltip.install(square, tooltip);
	}

	public PotionCounter getPotionCounter() {
		return potionCounter;
	}

	public IngredientCounter getIngredientCounter() {
		return ingredientCounter;
	}
}
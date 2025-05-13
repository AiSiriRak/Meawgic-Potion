package gui;

import java.util.ArrayList;

import Font.FontRect;
import entity.base.Ingredient;
import entity.base.Item;
import entity.base.Potion;
import inventory.IngredientCounter;
import inventory.PotionCounter;
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

public class BrewingPane extends VBox {

	private final ArrayList<InventorySquare> ingredientAllCells = new ArrayList<>();
	private final ArrayList<InventorySquare> potionAllCells = new ArrayList<>();
	private final ArrayList<Ingredient> brewIngredient = new ArrayList<>();

	private final IngredientCounter ingredientCounter;
	private final PotionCounter potionCounter;
	private final BrewingStand brewingStand;
	private final ControlBrewing controlBrewing;

	public BrewingPane(BrewingStand brewingStand, IngredientCounter ingredientCounter, PotionCounter potionCounter, ControlBrewing controlBrewing) {
		this.ingredientCounter = ingredientCounter;
		this.potionCounter = potionCounter;
		this.brewingStand = brewingStand;
		this.controlBrewing = controlBrewing;
		brewingStand.setBrewingPane(this);

		Image backgroundImage = new Image(ClassLoader.getSystemResource("Images/Brewing_pane.png").toString());
		BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true));

		this.setBackground(new Background(bgImage));
		this.setPrefSize(416, 320);
		this.setMinSize(416, 320);
		this.setMaxSize(416, 320);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(10));
		this.setSpacing(10);
		
		Text inventoryLabel = new Text("POTIONS");
		inventoryLabel.setFont(FontRect.REGULAR.getFont(24));
		GridPane ingredientGrid = createInventoryGrid(ingredientAllCells, ingredientCounter.getIngredientCounter());
		
		Text potionLabel = new Text("POTIONS");
		potionLabel.setFont(FontRect.REGULAR.getFont(24));
		GridPane potionGrid = createInventoryGrid(potionAllCells, potionCounter.getPotionCounter());

		this.getChildren().addAll(inventoryLabel, ingredientGrid, potionLabel, potionGrid);
	}

	private GridPane createInventoryGrid(ArrayList<InventorySquare> cells, ArrayList<? extends Item> items) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(5);
		grid.setHgap(5);

		int index = 0;
		for (int row = 0; row < 2; row++) {
			for (int col = 0; col < 7; col++) {
				InventorySquare square = new InventorySquare(col, row, "Brewing");
				square.setPrefSize(48, 48);
				cells.add(square);
				grid.add(square, col, row);

				if (index < items.size()) {
					Item item = items.get(index++);
					setupSquareWithItem(square, item);
					if (item instanceof Ingredient ingredient) {
						square.setOnMouseClicked(e -> {
							handleIngredientClick(ingredient, square);
						});
					}
				}
			}
		}
		return grid;
	}

	private void setupSquareWithItem(InventorySquare square, Item item) {
		ImageView imageView = new ImageView(item.getItemImage().getImage());
		imageView.setFitWidth(35);
		imageView.setFitHeight(35);
		square.setAlignment(Pos.CENTER);
		square.getChildren().add(imageView);

		Text amountText = new Text(String.valueOf(item.getAmount()));
		amountText.setFont(FontRect.REGULAR.getFont(16));
		amountText.setStyle("-fx-fill: white; -fx-font-size: 16;");
		StackPane.setAlignment(amountText, Pos.BOTTOM_RIGHT);
		square.getChildren().add(amountText);
		
		Tooltip tooltip = new Tooltip(item.getName());
		tooltip.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 12;");
		tooltip.setShowDelay(Duration.millis(300));
		tooltip.setHideDelay(Duration.millis(100));
		Tooltip.install(square, tooltip);
	}

	private void handleIngredientClick(Ingredient ingredient, InventorySquare square) {
		if (ingredient.getAmount() <= 0)
			return;

		if (brewingStand.containsIngredient(ingredient)) {
			brewingStand.removeIngredient(ingredient);
		} else if (brewingStand.hasAvailableCell()) {
			brewingStand.addIngredient(ingredient);
			ingredient.setAmount(ingredient.getAmount() - 1);
		}
		refreshInventory();
		GameController.getInventoryPane().refreshInventory();
		updateAmountDisplay(square, ingredient.getAmount());
		SoundController.getInstance().playEffectSound("Click_ingredient");
	}

	private void updateAmountDisplay(InventorySquare square, int amount) {
		square.getChildren().removeIf(node -> node instanceof Text);
		Text newAmountText = new Text(String.valueOf(amount));
		newAmountText.setFont(FontRect.REGULAR.getFont(16));
		newAmountText.setStyle("-fx-fill: white; -fx-font-size: 16;");
		StackPane.setAlignment(newAmountText, Pos.BOTTOM_RIGHT);
		square.getChildren().add(newAmountText);
	}

	public void returnIngredient(Ingredient ingredient) {
		ingredient.setAmount(ingredient.getAmount() + 1);
		refreshInventory();
		GameController.getInventoryPane().refreshInventory();
	}

	public void refreshInventory() {
		for (InventorySquare square : ingredientAllCells) {
			square.getChildren().clear();
		}
		for (InventorySquare square : potionAllCells) {
			square.getChildren().clear();
		}

		// ingredients
		int index = 0;
		for (Ingredient ingredient : ingredientCounter.getIngredientCounter()) {
			if (index >= ingredientAllCells.size())
				break;

			InventorySquare square = ingredientAllCells.get(index++);
			setupSquareWithItem(square, ingredient);
		}

		// potions
		index = 0;
		for (Potion potion : potionCounter.getPotionCounter()) {
			if (index >= potionAllCells.size())
				break;

			InventorySquare square = potionAllCells.get(index++);
			setupSquareWithItem(square, potion);
		}
	}

	public ArrayList<Ingredient> getBrewIngredient() {
		return brewIngredient;
	}

	public ControlBrewing getControlBrewing() {
		return controlBrewing;
	}
}
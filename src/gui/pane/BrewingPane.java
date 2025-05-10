package gui.pane;

import java.util.ArrayList;

import Font.FontRect;
import Inventory.IngredientCounter;
import Inventory.PotionCounter;
import entity.base.Ingredient;
import entity.base.Item;
import entity.base.Potion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Duration;
import logic.game.GameController;

public class BrewingPane extends VBox {
	private static final int GRID_ROWS = 2;
	private static final int GRID_COLS = 7;
	private static final int SQUARE_SIZE = 48;
	private static final int IMAGE_SIZE = 35;

	private final ArrayList<InventorySquare> ingredientCells = new ArrayList<>();
	private final ArrayList<InventorySquare> potionCells = new ArrayList<>();
	private final ArrayList<Ingredient> craftIngredient = new ArrayList<>();

	private final IngredientCounter ingredientCounter;
	private final PotionCounter potionCounter;
	private final BrewingStand brewingStand;
	private ControlBrewing controlBrewing;

	public BrewingPane(BrewingStand brewingStand, IngredientCounter ingredientCounter, PotionCounter potionCounter,
			ControlBrewing controlBrewing) {
		this.ingredientCounter = ingredientCounter;
		this.potionCounter = potionCounter;
		this.brewingStand = brewingStand;
		this.controlBrewing = controlBrewing;
		brewingStand.setBrewingPane(this);

		initializePane();
		setupContent();
	}

	private void initializePane() {
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
	}

	private void setupContent() {
		Text inventoryLabel = createSectionLabel("INVENTORY", 24);
		GridPane ingredientGrid = createInventoryGrid(ingredientCells, ingredientCounter.getIngredientCounter(), true);

		Text potionLabel = createSectionLabel("POTIONS", 24);
		GridPane potionGrid = createInventoryGrid(potionCells, potionCounter.getPotionCounter(), false);

		this.getChildren().addAll(inventoryLabel, ingredientGrid, potionLabel, potionGrid);
	}

	private Text createSectionLabel(String text, int fontSize) {
		Text label = new Text(text);
		label.setFont(FontRect.REGULAR.getFont(fontSize));
		return label;
	}

	private GridPane createInventoryGrid(ArrayList<InventorySquare> cells, ArrayList<? extends Item> items,
			boolean isIngredient) {
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

					if (isIngredient && item instanceof Ingredient ingredient) {
						setupIngredientClickHandler(square, ingredient);
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

		Text capacityText = new Text(String.valueOf(item.getCapacity()));
		capacityText.setFont(FontRect.REGULAR.getFont(16));
		capacityText.setStyle("-fx-fill: white; -fx-font-size: 16;");
		StackPane.setAlignment(capacityText, Pos.BOTTOM_RIGHT);
		square.getChildren().add(capacityText);

		createAndAttachTooltip(square, item.getName());
	}

	private void createAndAttachTooltip(InventorySquare square, String text) {
		Tooltip tooltip = new Tooltip(text);
		tooltip.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 12;");
		tooltip.setShowDelay(Duration.millis(300));
		tooltip.setHideDelay(Duration.millis(100));
		Tooltip.install(square, tooltip);
	}

	private void setupIngredientClickHandler(InventorySquare square, Ingredient ingredient) {
		square.setOnMouseClicked(e -> handleIngredientClick(ingredient, square));
	}

	private void handleIngredientClick(Ingredient ingredient, InventorySquare square) {
		if (ingredient.getCapacity() <= 0)
			return;

		if (brewingStand.containsIngredient(ingredient)) {
			brewingStand.removeIngredient(ingredient);
		} else if (brewingStand.hasAvailableSlot()) {
			brewingStand.addIngredient(ingredient);
			ingredient.setCapacity(ingredient.getCapacity() - 1);
		}
		refreshInventory();
		GameController.getInventoryPane().refreshInventory();
		updateCapacityDisplay(square, ingredient.getCapacity());
	}

	private void updateCapacityDisplay(InventorySquare square, int capacity) {
		square.getChildren().removeIf(node -> node instanceof Text);
		Text newCapacityText = new Text(String.valueOf(capacity));
		newCapacityText.setFont(FontRect.REGULAR.getFont(16));
		newCapacityText.setStyle("-fx-fill: white; -fx-font-size: 16;");
		StackPane.setAlignment(newCapacityText, Pos.BOTTOM_RIGHT);
		square.getChildren().add(newCapacityText);
	}

	public void returnIngredient(Ingredient ingredient) {
		ingredient.setCapacity(ingredient.getCapacity() + 1);
		refreshInventory();
	}

	public void refreshInventory() {
		for (InventorySquare square : ingredientCells) {
			square.getChildren().clear();
		}
		for (InventorySquare square : potionCells) {
			square.getChildren().clear();
		}

		// ingredient
		int index = 0;
		for (Ingredient ingredient : ingredientCounter.getIngredientCounter()) {
			if (index >= ingredientCells.size())
				break;

			InventorySquare square = ingredientCells.get(index++);
			setupSquareWithItem(square, ingredient);
		}

		// potion
		index = 0;
		for (Potion potion : potionCounter.getPotionCounter()) {
			if (index >= potionCells.size())
				break;

			InventorySquare square = potionCells.get(index++);
			setupSquareWithItem(square, potion);
		}
	}

	public void addCraftedPotion(Potion potion) {
		potionCounter.addPotion(potion);
		refreshInventory();
		GameController.getInventoryPane().refreshInventory();
	}

	public ArrayList<Ingredient> getCraftIngredient() {
		return craftIngredient;
	}

	public ControlBrewing getControlBrewing() {
		return controlBrewing;
	}
}

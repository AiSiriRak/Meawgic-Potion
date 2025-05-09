package gui.pane;

import java.util.ArrayList;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class BrewingPane extends VBox {
	private final ArrayList<InventorySquare> ingredientCells;
	private final ArrayList<InventorySquare> potionCells;
	private ArrayList<Ingredient> craftIngredient;
	private IngredientCounter ingredientCounter;
	private PotionCounter potionCounter;
	private BrewingStand brewingStand;

	public BrewingPane(BrewingStand brewingStand) {
		this.ingredientCells = new ArrayList<>();
		this.potionCells = new ArrayList<>();
		this.ingredientCounter = new IngredientCounter();
		this.potionCounter = new PotionCounter();
		this.brewingStand = brewingStand;
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
		GridPane ingredientGrid = createItemGrid(ingredientCells, ingredientCounter.getIngredientCounter(), "Brewing");

		Text potionLabel = createSectionLabel("POTIONS", 16);
		GridPane potionGrid = createItemGrid(potionCells, potionCounter.getPotionCounter(), "Brewing");

		this.getChildren().addAll(inventoryLabel, ingredientGrid, potionLabel, potionGrid);
	}

	private Text createSectionLabel(String text, int fontSize) {
		Text label = new Text(text);
		label.setFont(new Font(fontSize));
		return label;
	}

	private GridPane createItemGrid(ArrayList<InventorySquare> cells, ArrayList<? extends Item> items, String type) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(5);
		grid.setHgap(5);

		int index = 0;
		for (int row = 0; row < 2; row++) {
			for (int col = 0; col < 7; col++) {
				InventorySquare square = createInventorySquare(col, row, type);
				cells.add(square);
				grid.add(square, col, row);

				if (index < items.size()) {
					Item item = items.get(index++);
					setupSquareWithItem(square, item);

					if (item instanceof Ingredient) {
						setupIngredientClickHandler(square, (Ingredient) item);
					}
				}
			}
		}
		return grid;
	}

	private void setupIngredientClickHandler(InventorySquare square, Ingredient ingredient) {
		square.setOnMouseClicked(e -> handleIngredientClick(ingredient, square));
	}

	 private void handleIngredientClick(Ingredient ingredient, InventorySquare square) {
	        if (ingredient.getCapacity() <= 0) return;

	        if (brewingStand.containsIngredient(ingredient)) {
	            brewingStand.removeIngredient(ingredient);
	        } else if (brewingStand.hasAvailableSlot()) {
	            brewingStand.addIngredient(ingredient);
	            ingredient.setCapacity(ingredient.getCapacity() - 1);
	        }

	        updateCapacityDisplay(square, ingredient.getCapacity());
	    }

	private void updateCapacityDisplay(InventorySquare square, int capacity) {
		square.getChildren().removeIf(node -> node instanceof Text);
		Text newCapacityText = new Text(String.valueOf(capacity));
		newCapacityText.setStyle("-fx-fill: white; -fx-font-size: 16;");
		StackPane.setAlignment(newCapacityText, Pos.BOTTOM_RIGHT);
		square.getChildren().add(newCapacityText);
	}

	public void returnIngredient(Ingredient ingredient) {
		ingredient.setCapacity(ingredient.getCapacity() + 1);
		refreshInventoryDisplay();
	}

	public void refreshInventoryDisplay() {
	    for (int i = 0; i < ingredientCells.size(); i++) {
	        if (i < ingredientCounter.getIngredientCounter().size()) {
	            Ingredient ing = ingredientCounter.getIngredientCounter().get(i);
	            updateCapacityDisplay(ingredientCells.get(i), ing.getCapacity());
	        }
	    }
	}
	
	private InventorySquare createInventorySquare(int col, int row, String type) {
		InventorySquare square = new InventorySquare(col, row, type);
		square.setPrefSize(48, 48);
		return square;
	}

	private void setupSquareWithItem(InventorySquare square, Item item) {
		ImageView imageView = item.getItemImage();
		imageView.setFitWidth(35);
		imageView.setFitHeight(35);
		square.setAlignment(Pos.CENTER);

		square.getChildren().add(imageView);

		Text capacityText = new Text(String.valueOf(item.getCapacity()));
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

	public ArrayList<Ingredient> getCraftIngredient() {
		return craftIngredient;
	}

	public void setCraftIngredient(ArrayList<Ingredient> craftIngredient) {
		this.craftIngredient = craftIngredient;
	}
	public void addCraftedPotion(Potion potion) {
	    for (InventorySquare square : potionCells) {
	        if (!square.getChildren().isEmpty()) {
	            for (javafx.scene.Node node : square.getChildren()) {
	                if (node instanceof ImageView imageView) {
	                    String existingUrl = imageView.getImage().getUrl();
	                    String newUrl = potion.getItemImage().getImage().getUrl();
	                    if (existingUrl.equals(newUrl)) {
	                        for (javafx.scene.Node textNode : square.getChildren()) {
	                            if (textNode instanceof Text text) {
	                                int current = Integer.parseInt(text.getText());
	                                text.setText(String.valueOf(current + 1));
	                                return;
	                            }
	                        }
	                    }
	                }
	            }
	        }
	    }

	    for (InventorySquare square : potionCells) {
	        if (square.getChildren().isEmpty()) {
	            ImageView view = potion.getItemImage();
	            view.setFitWidth(35);
	            view.setFitHeight(35);
	            square.getChildren().add(view);

	            Text capacityText = new Text(String.valueOf(potion.getCapacity()));
	            capacityText.setStyle("-fx-fill: white; -fx-font-size: 16;");
	            StackPane.setAlignment(capacityText, Pos.BOTTOM_RIGHT);
	            square.getChildren().add(capacityText);

	            createAndAttachTooltip(square, potion.getName());
	            break;
	        }
	    }
	}


} 
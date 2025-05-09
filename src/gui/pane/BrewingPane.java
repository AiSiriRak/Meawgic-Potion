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
import javafx.scene.text.Font;
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

    public BrewingPane(BrewingStand brewingStand, IngredientCounter ingredientCounter, PotionCounter potionCounter) {
    	this.ingredientCounter = ingredientCounter; 
        this.potionCounter = potionCounter;
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
        GridPane ingredientGrid = createItemGrid(ingredientCells, ingredientCounter.getIngredientCounter(), true);

        Text potionLabel = createSectionLabel("POTIONS", 16);
        GridPane potionGrid = createItemGrid(potionCells, potionCounter.getPotionCounter(), false);

        this.getChildren().addAll(inventoryLabel, ingredientGrid, potionLabel, potionGrid);
    }

    private Text createSectionLabel(String text, int fontSize) {
        Text label = new Text(text);
        label.setFont(FontRect.REGULAR.getFont(fontSize));
        return label;
    }

    private GridPane createItemGrid(ArrayList<InventorySquare> cells, ArrayList<? extends Item> items, boolean isIngredient) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(5);

        int index = 0;
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLS; col++) {
                InventorySquare square = new InventorySquare(col, row, "Brewing");
                square.setPrefSize(SQUARE_SIZE, SQUARE_SIZE);
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
        imageView.setFitWidth(IMAGE_SIZE);
        imageView.setFitHeight(IMAGE_SIZE);
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

    	    // Refresh other UIs
    	    this.refreshInventoryDisplay(); // Refresh BrewingPane
    	    GameController.getInventoryPane().refreshInventory(); 
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

    public void addCraftedPotion(Potion potion) {
        if (incrementExistingPotion(potion)) return;
        addNewPotionSquare(potion);
    }

    private boolean incrementExistingPotion(Potion potion) {
        for (InventorySquare square : potionCells) {
            for (javafx.scene.Node node : square.getChildren()) {
                if (node instanceof ImageView imageView) {
                    if (imageView.getImage().getUrl().equals(potion.getItemImage().getImage().getUrl())) {
                        for (javafx.scene.Node textNode : square.getChildren()) {
                            if (textNode instanceof Text text) {
                                int current = Integer.parseInt(text.getText());
                                text.setText(String.valueOf(current + 1));
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private void addNewPotionSquare(Potion potion) {
        for (InventorySquare square : potionCells) {
            if (square.getChildren().isEmpty()) {
                ImageView view = new ImageView(potion.getItemImage().getImage());
                view.setFitWidth(IMAGE_SIZE);
                view.setFitHeight(IMAGE_SIZE);
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

    public ArrayList<Ingredient> getCraftIngredient() {
        return craftIngredient;
    }
}
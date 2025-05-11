package gui.pane;

import java.util.ArrayList;

import Font.FontRect;
import Inventory.IngredientCounter;
import Inventory.PotionCounter;
import entity.base.Ingredient;
import entity.base.Item;
import entity.base.Potion;
import entity.base.Stone;
import gui.button.ExitButtton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Duration;
import logic.game.GameController;

public class InventoryPane extends StackPane {
	private final ArrayList<InventorySquare> poallCells = new ArrayList<>();
	private final ArrayList<InventorySquare> inallCells = new ArrayList<>();
	private IngredientCounter ingredientCounter;
	private PotionCounter potionCounter;

	public InventoryPane(IngredientCounter ingredientCounter, PotionCounter potionCounter) {

		this.ingredientCounter = ingredientCounter;
		this.potionCounter = potionCounter;
		VBox content = createContentBox();
		ExitButtton exitButton = createExitButton();

		AnchorPane container = new AnchorPane();
		container.setPrefSize(500, 400);

		AnchorPane.setTopAnchor(content, 150.0);
		AnchorPane.setLeftAnchor(content, 275.0);
		container.getChildren().addAll(content, exitButton);

		AnchorPane.setTopAnchor(exitButton, 135.0);
		AnchorPane.setRightAnchor(exitButton, 250.0);

		this.setAlignment(Pos.CENTER);
		this.getChildren().add(container);
	}

	private VBox createContentBox() {
	    VBox contentBox = new VBox(10);
	    contentBox.setPrefSize(416, 320);
	    contentBox.setMinSize(416, 320);
	    contentBox.setMaxSize(416, 320);
	    contentBox.setAlignment(Pos.CENTER);
	    contentBox.setPadding(new Insets(10));
	    contentBox.setBackground(createBackgroundImage("Inventory_pane.png"));

	    Text inventoryLabel = createTitleText("INVENTORY", 24);
	    GridPane ingredientGrid = createInventoryGrid(inallCells, ingredientCounter.getIngredientCounter());

	    Text potionLabel = createTitleText("POTIONS", 16);
	    GridPane potionGrid = createInventoryGrid(poallCells, potionCounter.getPotionCounter());

	    contentBox.getChildren().addAll(inventoryLabel, ingredientGrid, potionLabel, potionGrid);
	    return contentBox;
	}
	
	private void setupInventorySquare(InventorySquare square, Item item) {
	    ImageView imageView = item.getItemImage();
	    imageView.setFitWidth(35);
	    imageView.setFitHeight(35);
	    square.setAlignment(Pos.CENTER);
	    square.getChildren().add(imageView);

	    Text capacityText = new Text(String.valueOf(item.getCapacity()));
	    capacityText.setFont(FontRect.REGULAR.getFont(14));
	    capacityText.setStyle("-fx-fill: white; -fx-font-size: 16;");
	    StackPane.setAlignment(capacityText, Pos.BOTTOM_RIGHT);
	    square.getChildren().add(capacityText);
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
	            cellList.add(square);
	            grid.add(square, col, row);

	            if (index < items.size()) {
	                Item item = items.get(index++);
	                setupInventorySquare(square, item);

	                Tooltip tooltip = new Tooltip(item.getName());
	                tooltip.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 12;");
	                tooltip.setShowDelay(Duration.millis(300));
	                tooltip.setHideDelay(Duration.millis(100));
	                Tooltip.install(square, tooltip);

	                if (item instanceof Stone) {
	                    Stone stone = (Stone) item;
	                    square.setOnMouseClicked(e -> {
	                        GameController.getInventoryPane().addIngredient(stone);
	                        GameController.coin.decreaseCoin(5);
	                    });
	                }
	            }
	        }
	    }
	    return grid;
	}

	private Text createTitleText(String text, int fontSize) {
		Text title = new Text(text);
		title.setFont(FontRect.BOLD.getFont(fontSize));
		return title;
	}

	private Background createBackgroundImage(String filename) {
		Image image = new Image(ClassLoader.getSystemResource("Images/" + filename).toString());
		BackgroundImage bgImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true));
		return new Background(bgImage);
	}

	private ExitButtton createExitButton() {
		ExitButtton exitButton = new ExitButtton();
		exitButton.setOnMouseEntered(e -> {
			exitButton.setScaleX(1.08);
			exitButton.setScaleY(1.08);
		});

		exitButton.setOnMouseExited(e -> {
			exitButton.setScaleX(1);
			exitButton.setScaleY(1);
		});
		exitButton.setOnMouseClicked(e -> this.setVisible(false));
		return exitButton;
	}

	public void addPotion(Potion potion) {
		for (Potion p : potionCounter.getPotionCounter()) {
			if (p.getName().equals(potion.getName())) {
				p.setCapacity(p.getCapacity() + 1);
				break;
			}
		}
		refreshInventory();
	}

	public void addIngredient(Ingredient ingredient) {
		for (Ingredient i : ingredientCounter.getIngredientCounter()) {
			if (i.getName().equals(ingredient.getName())) {
				i.setCapacity(i.getCapacity() + 1);
				break;
			}
		}
		refreshInventory();

	}

	public void refreshInventory() {
		for (InventorySquare square : inallCells) {
			square.getChildren().clear();
		}
		for (InventorySquare square : poallCells) {
			square.getChildren().clear();
		}

		// ingredient
		int index = 0;
		for (Ingredient ingredient : ingredientCounter.getIngredientCounter()) {
			if (index >= inallCells.size())
				break;

			InventorySquare square = inallCells.get(index++);
			setupSquareWithItem(square, ingredient);
		}

		// potion
		index = 0;
		for (Potion potion : potionCounter.getPotionCounter()) {
			if (index >= poallCells.size())
				break;

			InventorySquare square = poallCells.get(index++);
			setupSquareWithItem(square, potion);
		}
	}

	private void setupSquareWithItem(InventorySquare square, Item item) {
		ImageView imageView = new ImageView(item.getItemImage().getImage());
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

	public PotionCounter getPotionCounter() {
		return potionCounter;

	}

	public IngredientCounter getIngredientCounter() {
		return ingredientCounter;
	}

}
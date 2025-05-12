package gui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import entity.base.Ingredient;
import entity.base.Potion;
import entity.data.PotionData;
import entity.data.PotionRecipeData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import logic.game.GameController;
import logic.object.Pot;

public class BrewingStand extends VBox {
	private final ArrayList<InventorySquare> allCells = new ArrayList<>();
	private final ArrayList<Ingredient> ingredientsInCells = new ArrayList<>();
	private BrewingPane brewingPane;
	private InventorySquare outputCell;
	private Potion brewedPotion;
	private Pot associatedPot;

	public BrewingStand(Pot associatedPot) {
		this.associatedPot = associatedPot;

		Image backgroundImage = new Image(ClassLoader.getSystemResource("Images/Brewing_stand.png").toString());
		BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true));

		this.setBackground(new Background(bgImage));
		this.setPrefSize(416, 160);
		this.setMinSize(416, 160);
		this.setMaxSize(416, 160);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(10));
		this.setSpacing(21);

		setupCells();
	}

	private void setupCells() {
		GridPane ingredientGrid = new GridPane();
		ingredientGrid.setAlignment(Pos.CENTER);
		ingredientGrid.setVgap(10);
		ingredientGrid.setHgap(20);

		for (int col = 0; col < 3; col++) {
			InventorySquare square = new InventorySquare(col, 0, "Brewing");
			square.setPrefSize(48, 48);
			allCells.add(square);
			ingredientGrid.add(square, col, 0);
		}

		outputCell = new InventorySquare(0, 0, "Brewing");
		outputCell.setPrefSize(48, 48);
		GridPane potionPane = new GridPane();
		potionPane.setAlignment(Pos.CENTER);
		potionPane.add(outputCell, 0, 0);

		this.getChildren().addAll(ingredientGrid, potionPane);
	}

	public void addIngredient(Ingredient ingredient) {
		if (!hasAvailableCell())
			return;

		int cellIndex = ingredientsInCells.size();
		InventorySquare cell = allCells.get(cellIndex);

		ImageView view = new ImageView(ingredient.getItemImage().getImage());
		view.setFitWidth(35);
		view.setFitHeight(35);
		cell.getChildren().add(view);

		ingredientsInCells.add(ingredient);
		System.out.println("Ingredient added to BrewingStand: " + ingredient.getName());
		rebuildCellClickHandlers();

		displayBrewedPotion();
	}

	public void removeIngredient(Ingredient ingredient) {
		int index = ingredientsInCells.indexOf(ingredient);

		ingredientsInCells.remove(index);
		allCells.get(index).getChildren().clear();

		ingredient.setAmount(ingredient.getAmount() + 1);

		shiftIngredients();

		if (brewingPane != null) {
			brewingPane.refreshInventory();
		}

		rebuildCellClickHandlers();
		outputCell.getChildren().clear();
		brewedPotion = null;
		displayBrewedPotion();
	}

	private void shiftIngredients() {
		for (InventorySquare cell : allCells) {
			cell.getChildren().clear();
		}

		for (int i = 0; i < ingredientsInCells.size(); i++) {
			Ingredient ing = ingredientsInCells.get(i);
			InventorySquare cell = allCells.get(i);

			ImageView view = new ImageView(ing.getItemImage().getImage());
			view.setFitWidth(35);
			view.setFitHeight(35);
			cell.getChildren().add(view);
		}
	}

	private void rebuildCellClickHandlers() {
		for (int i = 0; i < ingredientsInCells.size(); i++) {
			Ingredient ing = ingredientsInCells.get(i);
			InventorySquare cell = allCells.get(i);
			cell.setOnMouseClicked(e -> removeIngredient(ing));
		}
	}

	public boolean brewable() {
		if (ingredientsInCells.size() != 3)
			return false;

		Set<String> currentIngredients = new HashSet<>();
		for (Ingredient ing : ingredientsInCells) {
			currentIngredients.add(ing.getName());
		}

		return PotionRecipeData.getPotionByIngredients(currentIngredients).isPresent();
	}

	public void brewPotion() {
		if (!brewable())
			return;

		Set<String> currentIngredients = new HashSet<>();
		for (Ingredient ing : ingredientsInCells) {
			currentIngredients.add(ing.getName());
		}

		Optional<PotionData> potionDataOpt = PotionRecipeData.getPotionByIngredients(currentIngredients);
		Potion matchedPotion = potionDataOpt.get().getItem();
		completeBrewing(matchedPotion);
		associatedPot.startTiming(matchedPotion.getDuration());

	}

	private void displayBrewedPotion() {
		if (brewedPotion != null || outputCell == null || ingredientsInCells.size() != 3)
			return;

		Set<String> currentIngredients = new HashSet<>();
		for (Ingredient ing : ingredientsInCells) {
			currentIngredients.add(ing.getName());
		}

		Optional<PotionData> potionDataOpt = PotionRecipeData.getPotionByIngredients(currentIngredients);
		if (potionDataOpt.isPresent()) {
			Potion matchedPotion = potionDataOpt.get().getItem();
			outputCell.getChildren().clear();
			ImageView potionView = new ImageView(matchedPotion.getItemImage().getImage());
			potionView.setFitWidth(35);
			potionView.setFitHeight(35);
			outputCell.getChildren().add(potionView);

			brewedPotion = matchedPotion;
		}
	}

	private void completeBrewing(Potion potion) {
		associatedPot.setPotion(potion);

		ingredientsInCells.clear();
		for (InventorySquare cell : allCells) {
			cell.getChildren().clear();
			cell.setOnMouseClicked(null);
		}

		this.brewedPotion = potion;

		brewingPane.refreshInventory();
		GameController.getInventoryPane().refreshInventory();
	}

	public void resetIngredients() {
		if (brewingPane != null) {
			for (Ingredient ingredient : new ArrayList<>(ingredientsInCells)) {
				brewingPane.returnIngredient(ingredient);
			}
		}
		
		ingredientsInCells.clear();

		for (InventorySquare cell : allCells) {
			cell.getChildren().clear();
			cell.setOnMouseClicked(null);
		}

		outputCell.getChildren().clear();
		brewedPotion = null;
	}
	
	public boolean hasAvailableCell() {
		return ingredientsInCells.size() < 3;
	}

	public boolean containsIngredient(Ingredient ingredient) {
		return ingredientsInCells.contains(ingredient);
	}

	public void setBrewingPane(BrewingPane pane) {
		this.brewingPane = pane;
	}
	
}
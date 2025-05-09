package gui.pane;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import entity.base.Ingredient;
import entity.base.Potion;
import entity.data.PotionData;
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
	private final ArrayList<InventorySquare> slots;
	private final ArrayList<Ingredient> ingredientsInSlots;
	private BrewingPane brewingPane;
	private InventorySquare outputSlot;
	private Potion craftedPotion;
	private Pot associatedPot;

	public BrewingStand(Pot associatedPot) {
		this.slots = new ArrayList<>();
		this.ingredientsInSlots = new ArrayList<>();
		this.associatedPot = associatedPot;
		initializeUI();
	}

	private void initializeUI() {
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

		setupSlots();
	}

	private void setupSlots() {
		GridPane ingredientGrid = new GridPane();
		ingredientGrid.setAlignment(Pos.CENTER);
		ingredientGrid.setVgap(10);
		ingredientGrid.setHgap(20);

		for (int col = 0; col < 3; col++) {
			InventorySquare slot = createSlot(col);
			slots.add(slot);
			ingredientGrid.add(slot, col, 0);
		}

		outputSlot = createSlot(0);
		GridPane potionPane = new GridPane();
		potionPane.setAlignment(Pos.CENTER);
		potionPane.add(outputSlot, 0, 0);

		this.getChildren().addAll(ingredientGrid, potionPane);
	}

	private InventorySquare createSlot(int col) {
		InventorySquare slot = new InventorySquare(col, 0, "Brewing");
		slot.setPrefSize(48, 48);
		return slot;
	}

	public void setBrewingPane(BrewingPane pane) {
		this.brewingPane = pane;
	}

	public boolean hasAvailableSlot() {
		return ingredientsInSlots.size() < 3;
	}

	public boolean containsIngredient(Ingredient ingredient) {
		return ingredientsInSlots.contains(ingredient);
	}

	public void addIngredient(Ingredient ingredient) {
		if (!hasAvailableSlot())
			return;

		int slotIndex = ingredientsInSlots.size();
		InventorySquare slot = slots.get(slotIndex);

		ImageView view = new ImageView(ingredient.getItemImage().getImage());
		view.setFitWidth(35);
		view.setFitHeight(35);
		slot.getChildren().add(view);

		ingredientsInSlots.add(ingredient);
		System.out.println("Ingredient added to BrewingStand: " + ingredient.getClass().getSimpleName()); // Added print
		rebuildSlotClickHandlers();

		tryDisplayCraftedPotion();
	}

	public void removeIngredient(Ingredient ingredient) {
		int index = ingredientsInSlots.indexOf(ingredient);
		if (index == -1)
			return;

		ingredientsInSlots.remove(index);
		slots.get(index).getChildren().clear();

		ingredient.setCapacity(ingredient.getCapacity() + 1);

		shiftIngredientsLeft();

		if (brewingPane != null) {
			brewingPane.refreshInventory();
		}

		rebuildSlotClickHandlers();
		outputSlot.getChildren().clear();
		craftedPotion = null;
		tryDisplayCraftedPotion();
	}

	private void shiftIngredientsLeft() {
		for (InventorySquare slot : slots) {
			slot.getChildren().clear();
		}

		for (int i = 0; i < ingredientsInSlots.size(); i++) {
			Ingredient ing = ingredientsInSlots.get(i);
			InventorySquare slot = slots.get(i);

			ImageView view = new ImageView(ing.getItemImage().getImage());
			view.setFitWidth(35);
			view.setFitHeight(35);
			slot.getChildren().add(view);
		}
	}

	private void rebuildSlotClickHandlers() {
		for (int i = 0; i < ingredientsInSlots.size(); i++) {
			Ingredient ing = ingredientsInSlots.get(i);
			InventorySquare slot = slots.get(i);
			slot.setOnMouseClicked(e -> removeIngredient(ing));
		}
	}

	public class PotionRecipeBook {
		public static final Map<Set<String>, PotionData> RECIPES = Map.of(Set.of("NetherWart", "Carrot", "RedStone"),
				PotionData.NIGHT_VISION, Set.of("NetherWart", "MagmaCream", "RedStone"), PotionData.FIRE_RESISTANCE,
				Set.of("NetherWart", "RabbitFoot", "GrowStone"), PotionData.LEAPING,
				Set.of("NetherWart", "Sugar", "RedStone"), PotionData.SWIFTNESS,
				Set.of("NetherWart", "Pufferfish", "RedStone"), PotionData.WATER_BREATHING,
				Set.of("NetherWart", "Watermelon", "GrowStone"), PotionData.HEALING,
				Set.of("NetherWart", "SpiderEye", "GrowStone"), PotionData.POISON,
				Set.of("NetherWart", "GhastTear", "RedStone"), PotionData.REGENERATION,
				Set.of("NetherWart", "BlazePowder", "GrowStone"), PotionData.STRENGTH);
	}

	public boolean craftable() {
		if (ingredientsInSlots.size() != 3)
			return false;

		Set<String> currentIngredients = new HashSet<>();
		for (Ingredient ing : ingredientsInSlots) {
			currentIngredients.add(ing.getName());
		}

		for (Map.Entry<Set<String>, PotionData> entry : PotionRecipeBook.RECIPES.entrySet()) {
			if (entry.getKey().equals(currentIngredients)) {
				return true;
			}
		}
		return false;
	}

	public void craftPotion() {
		if (!craftable())
			return;

		Set<String> currentIngredients = new HashSet<>();
		for (Ingredient ing : ingredientsInSlots) {
			currentIngredients.add(ing.getName());
		}

		for (Map.Entry<Set<String>, PotionData> entry : PotionRecipeBook.RECIPES.entrySet()) {
			if (entry.getKey().equals(currentIngredients)) {
				Potion matchedPotion = entry.getValue().getItem();
				completeCrafting(matchedPotion);
				associatedPot.startTiming(matchedPotion.getDuration());
				break;
			}
		}
	}

	private void tryDisplayCraftedPotion() {
		if (craftedPotion != null || outputSlot == null || ingredientsInSlots.size() != 3)
			return;

		Set<String> currentIngredients = new HashSet<>();
		for (Ingredient ing : ingredientsInSlots) {
			currentIngredients.add(ing.getName());
		}

		for (Map.Entry<Set<String>, PotionData> entry : PotionRecipeBook.RECIPES.entrySet()) {
			if (entry.getKey().equals(currentIngredients)) {
				Potion matchedPotion = entry.getValue().getItem();

				// Display it but don't complete crafting yet
				outputSlot.getChildren().clear();
				ImageView potionView = new ImageView(matchedPotion.getItemImage().getImage());
				potionView.setFitWidth(35);
				potionView.setFitHeight(35);
				outputSlot.getChildren().add(potionView);

				craftedPotion = matchedPotion;
				break;
			}
		}
	}

	private void completeCrafting(Potion potion) {
		associatedPot.setPotion(potion);

		ingredientsInSlots.clear();
		for (InventorySquare slot : slots) {
			slot.getChildren().clear();
			slot.setOnMouseClicked(null);
		}

		ImageView potionView = new ImageView(potion.getItemImage().getImage());
		potionView.setFitWidth(35);
		potionView.setFitHeight(35);
		outputSlot.getChildren().add(potionView);

		this.craftedPotion = potion;

		brewingPane.refreshInventory();
		GameController.getInventoryPane().refreshInventory();
	}

	public void resetIngredients() {
		for (Ingredient ingredient : new ArrayList<>(ingredientsInSlots)) {
			if (brewingPane != null) {
				brewingPane.returnIngredient(ingredient);
			}
			outputSlot.getChildren().clear();
			craftedPotion = null;
		}

		ingredientsInSlots.clear();
		for (InventorySquare slot : slots) {
			slot.getChildren().clear();
			slot.setOnMouseClicked(null);
		}
		outputSlot.getChildren().clear();
	}

}
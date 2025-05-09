package gui.pane;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import entity.base.Ingredient;
import entity.base.Potion;
import entity.potion.FireResistance;
import entity.potion.Healing;
import entity.potion.Leaping;
import entity.potion.NightVision;
import entity.potion.Poison;
import entity.potion.Regeneration;
import entity.potion.Strength;
import entity.potion.Swiftness;
import entity.potion.WaterBreathing;
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

public class BrewingStand extends VBox {
private final ArrayList<InventorySquare> slots;
private final ArrayList<Ingredient> ingredientsInSlots;
private BrewingPane brewingPane;
private InventorySquare outputSlot;
private Potion craftedPotion;

public BrewingStand() {
    this.slots = new ArrayList<>();
    this.ingredientsInSlots = new ArrayList<>();
    initializeUI();
}

private void initializeUI() {
    Image backgroundImage = new Image(ClassLoader.getSystemResource("Images/Brewing_stand.png").toString());
    BackgroundImage bgImage = new BackgroundImage(
        backgroundImage, 
        BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT, 
        BackgroundPosition.CENTER,
        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
    );
    
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
    if (!hasAvailableSlot()) return;

    int slotIndex = ingredientsInSlots.size();
    InventorySquare slot = slots.get(slotIndex);

    ImageView view = new ImageView(ingredient.getItemImage().getImage());
    view.setFitWidth(35);
    view.setFitHeight(35);
    slot.getChildren().add(view);

    ingredientsInSlots.add(ingredient);
    rebuildSlotClickHandlers();
}


public void removeIngredient(Ingredient ingredient) {
    int index = ingredientsInSlots.indexOf(ingredient);
    if (index == -1) return;

    ingredientsInSlots.remove(index);
    slots.get(index).getChildren().clear();

    ingredient.setCapacity(ingredient.getCapacity() + 1);

    shiftIngredientsLeft();

    if (brewingPane != null) {
        brewingPane.refreshInventoryDisplay();
    }

    rebuildSlotClickHandlers();
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


public void craftable() {
    if (!outputSlot.getChildren().isEmpty() || craftedPotion != null) {
        return;
    }

    if (ingredientsInSlots.size() != 3) return;

    Set<String> current = new HashSet<>();
    for (Ingredient ing : ingredientsInSlots) {
        current.add(ing.getClass().getSimpleName());
    }

    Map<Set<String>, Potion> recipes = Map.of(
        Set.of("NetherWart", "Carrot", "RedStone"), new NightVision(),
        Set.of("NetherWart", "MagmaCream", "RedStone"), new FireResistance(),
        Set.of("NetherWart", "RabbitFoot", "GrowStone"), new Leaping(),
        Set.of("NetherWart", "Sugar", "RedStone"), new Swiftness(),
        Set.of("NetherWart", "Pufferfish", "RedStone"), new WaterBreathing(),
        Set.of("NetherWart", "Watermelon", "GrowStone"), new Healing(),
        Set.of("NetherWart", "SpiderEye", "GrowStone"), new Poison(),
        Set.of("NetherWart", "GhastTear", "RedStone"), new Regeneration(),
        Set.of("NetherWart", "BlazePowder", "GrowStone"), new Strength()
    );

    Potion matchedPotion = null;
    for (Map.Entry<Set<String>, Potion> entry : recipes.entrySet()) {
        if (current.equals(entry.getKey())) {
            matchedPotion = entry.getValue();
            break;
        }
    }

    if (matchedPotion != null) {
        ingredientsInSlots.clear();
        for (InventorySquare slot : slots) {
            slot.getChildren().clear();
            slot.setOnMouseClicked(null);
        }

        outputSlot.getChildren().clear();
        ImageView view = matchedPotion.getItemImage();
        view.setFitWidth(35);
        view.setFitHeight(35);
        outputSlot.getChildren().add(view);

        this.craftedPotion = matchedPotion;

        outputSlot.setOnMouseClicked(e -> {
            if (brewingPane != null && craftedPotion != null) {
                brewingPane.addCraftedPotion(craftedPotion);
                outputSlot.getChildren().clear();
                outputSlot.setOnMouseClicked(null);
                craftedPotion = null;
            }
        });
    }
}





public void resetIngredients() {
    for (Ingredient ingredient : new ArrayList<>(ingredientsInSlots)) {
        if (brewingPane != null) {
            brewingPane.returnIngredient(ingredient);
        }
    }

    ingredientsInSlots.clear();
    for (InventorySquare slot : slots) {
        slot.getChildren().clear();
        slot.setOnMouseClicked(null); 
    }
}


}

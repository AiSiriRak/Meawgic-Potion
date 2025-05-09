package Inventory;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

import entity.base.Ingredient;
import entity.base.Item;

import entity.data.BasisData;
import entity.data.StoneData;

public class IngredientCounter {
	private ArrayList<Ingredient> ingredientCounter;
	private Item item;

	// Add these properties to track capacity
	private final IntegerProperty currentCount = new SimpleIntegerProperty(0);
	private final IntegerProperty maxCapacity = new SimpleIntegerProperty(12);

	public IngredientCounter() {
		this.ingredientCounter = new ArrayList<>();


		this.ingredientCounter.add(StoneData.REDSTONE.getItem());
		this.ingredientCounter.add(StoneData.GROWSTONE.getItem());
		this.ingredientCounter.add(BasisData.NETHER_WART.getItem());
		this.ingredientCounter.add(BasisData.WATERMELON.getItem());
		this.ingredientCounter.add(BasisData.CARROT.getItem());
		this.ingredientCounter.add(BasisData.SUGAR.getItem());
		this.ingredientCounter.add(BasisData.RABBIT_FOOT.getItem());
		this.ingredientCounter.add(BasisData.PUFFERFISH.getItem());
		this.ingredientCounter.add(BasisData.SPIDER_EYE.getItem());
		this.ingredientCounter.add(BasisData.MAGMA_CREAM.getItem());
		this.ingredientCounter.add(BasisData.GHAST_TEAR.getItem());
		this.ingredientCounter.add(BasisData.BLAZE_POWDER.getItem());
	}

	// Ingredient list accessors
	public ArrayList<Ingredient> getIngredientCounter() {
		return ingredientCounter;
	}

	public void setIngredientCounter(ArrayList<Ingredient> ingredientCounter) {
		this.ingredientCounter = ingredientCounter;
	}

	// Item accessors
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	// Capacity logic
	public boolean addIngredient() {
		if (currentCount.get() < maxCapacity.get()) {
			currentCount.set(currentCount.get() + 1);
			return true;
		}
		return false;
	}

	public boolean removeIngredient() {
		if (currentCount.get() > 0) {
			currentCount.set(currentCount.get() - 1);
			return true;
		}
		return false;
	}

	// Property accessors for UI binding
	public IntegerProperty currentCountProperty() {
		return currentCount;
	}

	public IntegerProperty maxCapacityProperty() {
		return maxCapacity;
	}

	public int getCurrentCount() {
		return currentCount.get();
	}

	public void setCurrentCount(int count) {
		this.currentCount.set(count);
	}

	public int getMaxCapacity() {
		return maxCapacity.get();
	}

	public void setMaxCapacity(int capacity) {
		this.maxCapacity.set(capacity);
	}
}

package Inventory;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

import entity.base.Crop;
import entity.base.Ingredient;
import entity.base.Item;

import entity.data.CropData;
import entity.data.StoneData;

public class IngredientCounter {
	private ArrayList<Ingredient> ingredientCounter;
	private Item item;

	// Add these properties to track amount
	private final IntegerProperty currentCount = new SimpleIntegerProperty(0);
	private final IntegerProperty maxCapacity = new SimpleIntegerProperty(12);

	public IngredientCounter() {
		this.ingredientCounter = new ArrayList<>();

		this.ingredientCounter.add(StoneData.REDSTONE.getItem());
		this.ingredientCounter.add(StoneData.GROWSTONE.getItem());
		this.ingredientCounter.add(CropData.NETHER_WART.getItem());
		this.ingredientCounter.add(CropData.WATERMELON.getItem());
		this.ingredientCounter.add(CropData.CARROT.getItem());
		this.ingredientCounter.add(CropData.SUGAR.getItem());
		this.ingredientCounter.add(CropData.RABBIT_FOOT.getItem());
		this.ingredientCounter.add(CropData.PUFFERFISH.getItem());
		this.ingredientCounter.add(CropData.SPIDER_EYE.getItem());
		this.ingredientCounter.add(CropData.MAGMA_CREAM.getItem());
		this.ingredientCounter.add(CropData.GHAST_TEAR.getItem());
		this.ingredientCounter.add(CropData.BLAZE_POWDER.getItem());
	}

	public ArrayList<Ingredient> getIngredientCounter() {
		return ingredientCounter;
	}

	public void setIngredientCounter(ArrayList<Ingredient> ingredientCounter) {
		this.ingredientCounter = ingredientCounter;
	}

	public ArrayList<Crop> getBasisCounter() {
		ArrayList<Crop> basis = new ArrayList<>();
		for (Ingredient i : ingredientCounter) {
			if (i instanceof Crop) {
				basis.add((Crop) i);
			}
		}
		return basis;
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

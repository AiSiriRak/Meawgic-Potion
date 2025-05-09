package Inventory;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

import entity.base.Ingredient;
import entity.base.Item;
import entity.ingredient.*;

public class IngredientCounter {
	private ArrayList<Ingredient> ingredientCounter;
	private Item item;

	// Add these properties to track capacity
	private final IntegerProperty currentCount = new SimpleIntegerProperty(0);
	private final IntegerProperty maxCapacity = new SimpleIntegerProperty(12);

	public IngredientCounter() {
		this.ingredientCounter = new ArrayList<>();

		this.ingredientCounter.add(new RedStone());
		this.ingredientCounter.add(new GrowStone());
		this.ingredientCounter.add(new NetherWart());
		this.ingredientCounter.add(new Watermelon());
		this.ingredientCounter.add(new Carrot());
		this.ingredientCounter.add(new Sugar());
		this.ingredientCounter.add(new RabbitFoot());
		this.ingredientCounter.add(new Pufferfish());
		this.ingredientCounter.add(new SpiderEye());
		this.ingredientCounter.add(new MagmaCream());
		this.ingredientCounter.add(new GhastTear());
		this.ingredientCounter.add(new BlazePowder());
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

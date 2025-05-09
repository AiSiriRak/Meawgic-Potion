package Inventory;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

import entity.base.Item;
import entity.base.Potion;
import entity.potion.*;

public class PotionCounter {
	private ArrayList<Potion> potionCounter;
	private Item item;

	// Capacity tracking
	private final IntegerProperty currentCount = new SimpleIntegerProperty(0);
	private final IntegerProperty maxCapacity = new SimpleIntegerProperty(12);

	public PotionCounter() {
		this.potionCounter = new ArrayList<>();

		this.potionCounter.add(new NightVision());
		this.potionCounter.add(new FireResistance());
		this.potionCounter.add(new Leaping());
		this.potionCounter.add(new Swiftness());
		this.potionCounter.add(new WaterBreathing());
		this.potionCounter.add(new Healing());
		this.potionCounter.add(new Poison());
		this.potionCounter.add(new Regeneration());
		this.potionCounter.add(new Strength());
	}

	// Accessors for potion list
	public ArrayList<Potion> getPotionCounter() {
		return potionCounter;
	}

	public void setPotionCounter(ArrayList<Potion> potionCounter) {
		this.potionCounter = potionCounter;
	}

	// Accessors for item
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	// Capacity logic
	public boolean addPotion() {
		if (currentCount.get() < maxCapacity.get()) {
			currentCount.set(currentCount.get() + 1);
			return true;
		}
		return false;
	}

	public boolean removePotion() {
		if (currentCount.get() > 0) {
			currentCount.set(currentCount.get() - 1);
			return true;
		}
		return false;
	}

	// Property accessors
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

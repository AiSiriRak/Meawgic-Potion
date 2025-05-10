package Inventory;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

import entity.base.Item;
import entity.base.Potion;

import entity.data.PotionData;

public class PotionCounter {
	private ArrayList<Potion> potionCounter;
	private Item item;

	// Capacity tracking
	private final IntegerProperty currentCount = new SimpleIntegerProperty(0);
	private final IntegerProperty maxCapacity = new SimpleIntegerProperty(12);

	public PotionCounter() {

		this.potionCounter = new ArrayList<>();

		this.potionCounter.add(PotionData.NIGHT_VISION.getItem());
		this.potionCounter.add(PotionData.FIRE_RESISTANCE.getItem());
		this.potionCounter.add(PotionData.LEAPING.getItem());
		this.potionCounter.add(PotionData.SWIFTNESS.getItem());
		this.potionCounter.add(PotionData.WATER_BREATHING.getItem());
		this.potionCounter.add(PotionData.HEALING.getItem());
		this.potionCounter.add(PotionData.POISON.getItem());
		this.potionCounter.add(PotionData.REGENERATION.getItem());
		this.potionCounter.add(PotionData.STRENGTH.getItem());
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
    public void addPotion(Potion potion) {
        for (Potion p : potionCounter) {
            if (p.getName().equals(potion.getName())) {
                p.setAmount(p.getAmount() + 1);
                return;
            }
        }
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

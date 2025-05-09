package Inventory;

import java.util.ArrayList;

import entity.base.Item;
import entity.base.Potion;
import entity.data.PotionData;

public class PotionCounter {
	private ArrayList<Potion> PotionCounter;
	private Item item;

	public PotionCounter() {
		this.PotionCounter = new ArrayList<>();

		this.PotionCounter.add(PotionData.NIGHT_VISION.getItem());
		this.PotionCounter.add(PotionData.FIRE_RESISTANCE.getItem());
		this.PotionCounter.add(PotionData.LEAPING.getItem());
		this.PotionCounter.add(PotionData.SWIFTNESS.getItem());
		this.PotionCounter.add(PotionData.WATER_BREATHING.getItem());
		this.PotionCounter.add(PotionData.HEALING.getItem());
		this.PotionCounter.add(PotionData.POISON.getItem());
		this.PotionCounter.add(PotionData.REGENERATION.getItem());
		this.PotionCounter.add(PotionData.STRENGTH.getItem());
	}

	public ArrayList<Potion> getPotionCounter() {
		return PotionCounter;
	}

	public void setPotionCounter(ArrayList<Potion> potionCounter) {
		PotionCounter = potionCounter;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}

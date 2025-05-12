package Inventory;

import java.util.ArrayList;

import entity.base.Potion;

import entity.data.PotionData;

public class PotionCounter {
	private ArrayList<Potion> potionCounter;

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
	
	public void reset() {
		for (Potion p : potionCounter) {
			p.setAmount(0);
		}
	}

	public ArrayList<Potion> getPotionCounter() {
		return potionCounter;
	}
}

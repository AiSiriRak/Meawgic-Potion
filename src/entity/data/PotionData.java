package entity.data;

import entity.base.Potion;

public enum PotionData {
	NIGHT_VISION("NightVision", 80, 60), 
	FIRE_RESISTANCE("FireResistance", 120, 120), 
	LEAPING("Leaping", 100, 90),
	SWIFTNESS("Swiftness", 80, 60), 
	WATER_BREATHING("WaterBreathing", 100, 90), 
	HEALING("Healing", 80, 60),
	POISON("Poison", 100, 90), 
	REGENERATION("Regeneration", 120, 120), 
	STRENGTH("Strength", 120, 120);

	private final Potion potion;

	PotionData(String name, int sellPrice, int duration) {
		this.potion = new Potion(name, sellPrice, duration);
	}

	public Potion getItem() {
		return potion;
	}

}

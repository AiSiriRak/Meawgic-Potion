package entity.data;

import entity.base.Potion;

public enum PotionData {
	NIGHT_VISION("NightVision", 0, 80, 60),
	FIRE_RESISTANCE("FireResistance", 0, 120, 120),
	LEAPING("Leaping", 0, 100, 90),
	SWIFTNESS("Swiftness", 0, 80, 60),
	WATER_BREATHING("WaterBreathing", 0, 100, 90),
	HEALING("Healing", 0, 80, 60),
	POISON("Poison", 0, 100, 90),
	REGENERATION("Regeneration", 0, 120, 120),
	STRENGTH("Strength", 0, 120, 120);

	private final Potion potion;

	PotionData(String name, int capacity, int sellPrice, int duration) {
		this.potion = new Potion(name, capacity, sellPrice, duration);
	}

	public Potion getItem() {
		return potion;
	}

}

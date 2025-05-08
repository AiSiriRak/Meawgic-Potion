package Inventory;

import java.util.ArrayList;

import entity.base.Ingredient;
import entity.base.Item;
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

public class PotionCounter {
	private ArrayList<Potion> PotionCounter;
	private Item item;

	public PotionCounter() {
		this.PotionCounter = new ArrayList<>();
		
		this.PotionCounter.add(new NightVision());
		this.PotionCounter.add(new FireResistance());
		this.PotionCounter.add(new Leaping());
		this.PotionCounter.add(new Swiftness());
		this.PotionCounter.add(new WaterBreathing());
		this.PotionCounter.add(new Healing());
		this.PotionCounter.add(new Poison());
		this.PotionCounter.add(new Regeneration());
		this.PotionCounter.add(new Strength());
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

package entity.base;

import java.util.ArrayList;

public class Potion extends Item {
	private int sellPrice;
	private int duration;
	private ArrayList<Ingredient> ingredients;

	public Potion(String name, int sellPrice, int duration) {
		super(name);
		this.setSellPrice(sellPrice);
		this.setDuration(duration);
	}

	public int getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

}

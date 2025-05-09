package entity.base;

import java.util.ArrayList;
import java.util.List;

public class Potion extends Item{
	private String name;
	private int sellPrice;
	private int duration;
	private boolean isCraft;
	private ArrayList<Ingredient> ingredients;
	private Potion potion;

	public Potion(String name, int sellPrice, int duration) {
		super(name, 1);
		this.setSellPrice(sellPrice);
		this.setDuration(duration);
		this.setCraft(false);
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

	public boolean isCraft() {
		return isCraft;
	}

	public void setCraft(boolean isCraft) {
		this.isCraft = isCraft;
	}


	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}


	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	 public void setPotion(Potion potion) {
	        this.potion = potion;
	    }
	    
	    public Potion getPotion() {
	        return potion;
	    }

}

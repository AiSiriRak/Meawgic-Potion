package Inventory;

import java.util.ArrayList;

import entity.base.Ingredient;
import entity.base.Item;
import entity.data.BasisData;
import entity.data.StoneData;

public class IngredientCounter {
	private ArrayList<Ingredient> ingredientCounter;
	private Item item;

	public IngredientCounter() {
		this.ingredientCounter = new ArrayList<>();

		this.ingredientCounter.add(StoneData.REDSTONE.getItem());
		this.ingredientCounter.add(StoneData.GROWSTONE.getItem());
		this.ingredientCounter.add(BasisData.NETHER_WART.getItem());
		this.ingredientCounter.add(BasisData.WATERMELON.getItem());
		this.ingredientCounter.add(BasisData.CARROT.getItem());
		this.ingredientCounter.add(BasisData.SUGAR.getItem());
		this.ingredientCounter.add(BasisData.RABBIT_FOOT.getItem());
		this.ingredientCounter.add(BasisData.PUFFERFISH.getItem());
		this.ingredientCounter.add(BasisData.SPIDER_EYE.getItem());
		this.ingredientCounter.add(BasisData.MAGMA_CREAM.getItem());
		this.ingredientCounter.add(BasisData.GHAST_TEAR.getItem());
		this.ingredientCounter.add(BasisData.BLAZE_POWDER.getItem());
	}

	public ArrayList<Ingredient> getIngredientCounter() {
		return ingredientCounter;
	}

	public void setIngredientCounter(ArrayList<Ingredient> ingredientCounter) {
		this.ingredientCounter = ingredientCounter;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}

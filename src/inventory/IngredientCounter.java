package inventory;

import java.util.ArrayList;

import entity.base.Crop;
import entity.base.Ingredient;
import entity.data.CropData;
import entity.data.StoneData;

public class IngredientCounter {
	private ArrayList<Ingredient> ingredientCounter;

	public IngredientCounter() {
		this.ingredientCounter = new ArrayList<>();

		this.ingredientCounter.add(StoneData.REDSTONE.getItem());
		this.ingredientCounter.add(StoneData.GROWSTONE.getItem());
		this.ingredientCounter.add(CropData.NETHER_WART.getItem());
		this.ingredientCounter.add(CropData.WATERMELON.getItem());
		this.ingredientCounter.add(CropData.CARROT.getItem());
		this.ingredientCounter.add(CropData.SUGAR.getItem());
		this.ingredientCounter.add(CropData.RABBIT_FOOT.getItem());
		this.ingredientCounter.add(CropData.PUFFERFISH.getItem());
		this.ingredientCounter.add(CropData.SPIDER_EYE.getItem());
		this.ingredientCounter.add(CropData.MAGMA_CREAM.getItem());
		this.ingredientCounter.add(CropData.GHAST_TEAR.getItem());
		this.ingredientCounter.add(CropData.BLAZE_POWDER.getItem());
	}
	
	public void reset() {
		for (Ingredient i : ingredientCounter) {
			i.setAmount(0);
		}
	}

	public ArrayList<Crop> getCropCounter() {
		ArrayList<Crop> crop = new ArrayList<>();
		for (Ingredient i : ingredientCounter) {
			if (i instanceof Crop) {
				crop.add((Crop) i);
			}
		}
		return crop;
	}

	public ArrayList<Ingredient> getIngredientCounter() {
		return ingredientCounter;
	}

}

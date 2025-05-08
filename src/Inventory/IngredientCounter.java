package Inventory;

import java.util.ArrayList;

import entity.base.Ingredient;
import entity.base.Item;
import entity.ingredient.BlazePowder;
import entity.ingredient.Carrot;
import entity.ingredient.GhastTear;
import entity.ingredient.GrowStone;
import entity.ingredient.MagmaCream;
import entity.ingredient.NetherWart;
import entity.ingredient.Pufferfish;
import entity.ingredient.RabbitFoot;
import entity.ingredient.RedStone;
import entity.ingredient.SpiderEye;
import entity.ingredient.Sugar;
import entity.ingredient.Watermelon;

public class IngredientCounter {
	private ArrayList<Ingredient> ingredientCounter;
	private Item item;

	public IngredientCounter() {
		this.ingredientCounter = new ArrayList<>();
		
		this.ingredientCounter.add(new RedStone());
		this.ingredientCounter.add(new GrowStone());
		this.ingredientCounter.add(new NetherWart());
		this.ingredientCounter.add(new Watermelon());
		this.ingredientCounter.add(new Carrot());
		this.ingredientCounter.add(new Sugar());
		this.ingredientCounter.add(new RabbitFoot());
		this.ingredientCounter.add(new Pufferfish());
		this.ingredientCounter.add(new SpiderEye());
		this.ingredientCounter.add(new MagmaCream());
		this.ingredientCounter.add(new GhastTear());
		this.ingredientCounter.add(new BlazePowder());
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

package entity.potion;

import java.util.ArrayList;
import java.util.List;

import entity.base.Potion;
import entity.ingredient.BlazePowder;
import entity.ingredient.Carrot;
import entity.ingredient.GrowStone;
import entity.ingredient.NetherWart;
import entity.ingredient.RedStone;

public class Strength extends Potion{

	public Strength() {
		super("Strength",120,120);
		this.setIngredients(new ArrayList<>(List.of(new NetherWart(), new BlazePowder(), new GrowStone())));
	}

}

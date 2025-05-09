package entity.potion;

import java.util.ArrayList;
import java.util.List;

import entity.base.Ingredient;
import entity.base.Potion;
import entity.ingredient.Carrot;
import entity.ingredient.MagmaCream;
import entity.ingredient.NetherWart;
import entity.ingredient.RedStone;

public class FireResistance extends Potion{

	public FireResistance() {
		super("FireResistance",120,120);
		this.setIngredients(new ArrayList<>(List.of(new NetherWart(), new MagmaCream(), new RedStone())));
	}

}

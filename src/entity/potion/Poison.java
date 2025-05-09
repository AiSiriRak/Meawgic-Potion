package entity.potion;

import java.util.ArrayList;
import java.util.List;

import entity.base.Potion;
import entity.ingredient.Carrot;
import entity.ingredient.GrowStone;
import entity.ingredient.NetherWart;
import entity.ingredient.RedStone;
import entity.ingredient.SpiderEye;

public class Poison extends Potion{

	public Poison() {
		super("Poison",100,90);
		this.setIngredients(new ArrayList<>(List.of(new NetherWart(), new SpiderEye(), new GrowStone())));
	}

}

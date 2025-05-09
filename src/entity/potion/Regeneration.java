package entity.potion;

import java.util.ArrayList;
import java.util.List;

import entity.base.Potion;
import entity.ingredient.Carrot;
import entity.ingredient.GhastTear;
import entity.ingredient.NetherWart;
import entity.ingredient.RedStone;

public class Regeneration extends Potion{

	public Regeneration() {
		super("Regeneration",120,120);
		this.setIngredients(new ArrayList<>(List.of(new NetherWart(), new GhastTear(), new RedStone())));
	}

}

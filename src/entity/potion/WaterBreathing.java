package entity.potion;

import java.util.ArrayList;
import java.util.List;

import entity.base.Potion;
import entity.ingredient.Carrot;
import entity.ingredient.NetherWart;
import entity.ingredient.Pufferfish;
import entity.ingredient.RedStone;

public class WaterBreathing extends Potion{

	public WaterBreathing() {
		super("WaterBreathing",100,90);
		this.setIngredients(new ArrayList<>(List.of(new NetherWart(), new Pufferfish(), new RedStone())));
	}

}

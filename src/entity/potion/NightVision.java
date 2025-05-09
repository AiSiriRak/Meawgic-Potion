package entity.potion;

import java.util.ArrayList;
import java.util.List;

import entity.base.Potion;
import entity.ingredient.Carrot;
import entity.ingredient.NetherWart;
import entity.ingredient.RedStone;

public class NightVision extends Potion{

	public NightVision() {
		super("NightVision", 80, 60);
		this.setIngredients(new ArrayList<>(List.of(new NetherWart(), new Carrot(), new RedStone())));
	}

}

package entity.potion;

import java.util.ArrayList;
import java.util.List;

import entity.base.Potion;
import entity.ingredient.Carrot;
import entity.ingredient.GrowStone;
import entity.ingredient.NetherWart;
import entity.ingredient.RedStone;
import entity.ingredient.Watermelon;

public class Healing extends Potion{

	public Healing() {
		super("Healing",80,60);
		this.setIngredients(new ArrayList<>(List.of(new NetherWart(), new Watermelon(), new GrowStone())));
		// TODO Auto-generated constructor stub
	}

}

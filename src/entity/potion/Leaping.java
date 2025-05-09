package entity.potion;

import java.util.ArrayList;
import java.util.List;

import entity.base.Potion;
import entity.ingredient.Carrot;
import entity.ingredient.GrowStone;
import entity.ingredient.NetherWart;
import entity.ingredient.RabbitFoot;
import entity.ingredient.RedStone;

public class Leaping extends Potion{

	public Leaping() {
		super("Leaping",100,90);
		this.setIngredients(new ArrayList<>(List.of(new NetherWart(), new RabbitFoot(), new GrowStone())));
	}

}

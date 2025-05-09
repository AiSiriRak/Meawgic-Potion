package entity.potion;

import java.util.ArrayList;
import java.util.List;

import entity.base.Potion;
import entity.ingredient.Carrot;
import entity.ingredient.NetherWart;
import entity.ingredient.RedStone;
import entity.ingredient.Sugar;

public class Swiftness extends Potion{

	public Swiftness() {
		super("Swiftness",80,60);
		this.setIngredients(new ArrayList<>(List.of(new NetherWart(), new Sugar(), new RedStone())));
	}

}

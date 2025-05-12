package logic.components;

import java.util.ArrayList;

import gui.ControlBrewing;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import logic.game.GameController;
import logic.object.Door;
import logic.object.GameObject;
import logic.object.Pot;

public class InsideMap extends Map {

	private static ArrayList<GameObject> gameObjectList = new ArrayList<>();

	public InsideMap() {
		super(new Image(ClassLoader.getSystemResource("Images/Inside_Base.png").toString()),
				new Rectangle2D(262, 418, 556, 306), 508, 600, gameObjectList);
		this.player.setDirection("up");
		this.setObject();
	}

	public void setObject() {
		GameObject pot1 = new Pot("Pot - 1", 320, 480);
		GameObject pot2 = new Pot("Pot - 2", 632, 480);
		ControlBrewing controlBrewing1 = new ControlBrewing(GameController.getSharedIngredientCounter(),
				GameController.getSharedPotionCounter(), (Pot) pot1);
		ControlBrewing controlBrewing2 = new ControlBrewing(GameController.getSharedIngredientCounter(),
				GameController.getSharedPotionCounter(), (Pot) pot2);
		gameObjectList.clear();
		gameObjectList.add(pot1);
		gameObjectList.add(pot2);
		((Pot) pot1).setControlBrewing(controlBrewing1);
		((Pot) pot2).setControlBrewing(controlBrewing2);
		GameController.addControlBrewing(controlBrewing1);
		GameController.addControlBrewing(controlBrewing2);

		GameObject door = new Door("Door", 508, 732);
		gameObjectList.add(door);
	}

}
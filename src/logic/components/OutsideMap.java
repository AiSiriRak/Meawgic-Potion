package logic.components;

import java.util.ArrayList;

import logic.object.Crop;
import logic.object.*;

public class OutsideMap extends Map {

	private static ArrayList<GameObject> gameObjectList = new ArrayList<>();

	public OutsideMap() {
		super(1536, 1152, "Outside_Base", 576, 320, gameObjectList);
		this.setObject();
	}

	public void setObject() {
		GameObject crop1 = new Crop("Crop - 1", 768, 384);
		GameObject crop2 = new Crop("Crop - 2", 1088, 384);
		GameObject crop3 = new Crop("Crop - 3", 768, 704);
		GameObject crop4 = new Crop("Crop - 4", 1088, 704);
		gameObjectList.add(crop1);
		gameObjectList.add(crop2);
		gameObjectList.add(crop3);
		gameObjectList.add(crop4);

		GameObject house = new House("House", 448, 0);
		gameObjectList.add(house);

		GameObject pond = new Pond("Pond", 128, 512);
		gameObjectList.add(pond);
	}

}

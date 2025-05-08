package logic.components;

import java.util.ArrayList;

public class OutsideMap extends Map {

	private static ArrayList<GameObject> gameObjectList = new ArrayList<>();

	public OutsideMap() {
		super(1536, 1152, "Outside_Base", 576, 320, gameObjectList);
		this.setObject();
	}

	public void setObject() {
		GameObject crop1 = new Crop(768, 384);
		GameObject crop2 = new Crop(1088, 384);
		gameObjectList.add(crop1);
		gameObjectList.add(crop2);
	}

}

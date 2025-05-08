package logic.components;

import java.util.ArrayList;

public class InsideMap extends Map {

	private static ArrayList<GameObject> gameObjectList = new ArrayList<>();

	public InsideMap() {
		super(900, 768, "Inside_Base", 402, 660, gameObjectList);
		this.setObject();
	}

	public void setObject() {
	}

}

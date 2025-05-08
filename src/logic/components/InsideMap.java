package logic.components;

import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import logic.object.GameObject;

public class InsideMap extends Map {

	private static ArrayList<GameObject> gameObjectList = new ArrayList<>();

	public InsideMap() {
		super(1080, 960, "Inside_Base", new Rectangle2D(262, 418, 556, 306), 508, 736, gameObjectList);

		this.setObject();
	}

	public void setObject() {
	}

}

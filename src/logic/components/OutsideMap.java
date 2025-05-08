package logic.components;

import java.util.ArrayList;

import javafx.scene.image.Image;
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
		((Crop) crop1).setItem(new Image(ClassLoader.getSystemResource("Images/100.png").toString()));
		((Crop) crop1).changeStage(3);
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

		GameObject shop = new Shop("Shop", 256, 128);
		gameObjectList.add(shop);

		ArrayList<GameObject> tree = new ArrayList<>();
		tree.add(new Tree("Tree", 668, 261));
		tree.add(new Tree("Tree", 21, 59));
		tree.add(new Tree("Tree", 132, 251));
		tree.add(new Tree("Tree", 340, 115));
		tree.add(new Tree("Tree", 393, 5));
		tree.add(new Tree("Tree", 768, 36));
		tree.add(new Tree("Tree", 857, 103));
		tree.add(new Tree("Tree", 981, 19));
		tree.add(new Tree("Tree", 1121, 115));
		tree.add(new Tree("Tree", 381, 307));
		tree.add(new Tree("Tree", 1264, 36));
		tree.add(new Tree("Tree", 1388, 186));
		tree.add(new Tree("Tree", 1374, 415));
		tree.add(new Tree("Tree", 1423, 638));
		tree.add(new Tree("Tree", 1374, 926));
		tree.add(new Tree("Tree", 1202, 926));
		tree.add(new Tree("Tree", 997, 896));
		tree.add(new Tree("Tree", 668, 926));
		tree.add(new Tree("Tree", 327, 586));
		tree.add(new Tree("Tree", 422, 835));
		tree.add(new Tree("Tree", 24, 696));
		tree.add(new Tree("Tree", 15, 940));
		tree.add(new Tree("Tree", 144, 894));
		tree.add(new Tree("Tree", 289, 940));

		gameObjectList.addAll(tree);

	}

}

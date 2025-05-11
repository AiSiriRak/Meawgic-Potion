package logic.components;

import java.util.ArrayList;

import entity.data.*;
import gui.PlantPane;
import javafx.geometry.Rectangle2D;
import logic.object.CropPlot;
import logic.game.GameController;
import logic.object.*;

public class OutsideMap extends Map {

	private static ArrayList<GameObject> gameObjectList = new ArrayList<>();

	public OutsideMap() {
		super(1536, 1152, "Outside_Base", new Rectangle2D(0, 0, 1536, 1152), 576, 320, gameObjectList);
		this.player.setDirection("down");
		this.setObject();
	}

	public void setObject() {
		setCrop("Crop - 1", 768, 384);
		setCrop("Crop - 2", 1088, 384);
		setCrop("Crop - 3", 768, 704);
		setCrop("Crop - 4", 1088, 704);

		House house = new House("House", 448, 0);
		gameObjectList.add(house);

		Pond pond = new Pond("Pond", 128, 512);
		gameObjectList.add(pond);

		Shop shop = new Shop("Shop", 256, 128);
		gameObjectList.add(shop);

		ArrayList<Tree> tree = new ArrayList<>();
		tree.add(new Tree("Tree", 15, 940));
		tree.add(new Tree("Tree", 21, 59));
		tree.add(new Tree("Tree", 24, 696));
		tree.add(new Tree("Tree", 132, 251));
		tree.add(new Tree("Tree", 144, 894));
		tree.add(new Tree("Tree", 289, 940));
		tree.add(new Tree("Tree", 327, 586));
		tree.add(new Tree("Tree", 340, 115));
		tree.add(new Tree("Tree", 381, 307));
		tree.add(new Tree("Tree", 393, 5));
		tree.add(new Tree("Tree", 422, 835));
		tree.add(new Tree("Tree", 668, 261));
		tree.add(new Tree("Tree", 668, 926));
		tree.add(new Tree("Tree", 768, 36));
		tree.add(new Tree("Tree", 857, 103));
		tree.add(new Tree("Tree", 981, 19));
		tree.add(new Tree("Tree", 997, 896));
		tree.add(new Tree("Tree", 1121, 115));
		tree.add(new Tree("Tree", 1202, 926));
		tree.add(new Tree("Tree", 1264, 36));
		tree.add(new Tree("Tree", 1374, 415));
		tree.add(new Tree("Tree", 1374, 926));
		tree.add(new Tree("Tree", 1388, 186));
		tree.add(new Tree("Tree", 1423, 638));

		gameObjectList.addAll(tree);
	}

	private void setCrop(String name, int x, int y) {
		CropPlot crop = new CropPlot(name, x, y);
		PlantPane plantPane = new PlantPane(crop);
		gameObjectList.add(crop);
		crop.setPlantPane(plantPane);
		GameController.addPlantPane(plantPane);

	}

}

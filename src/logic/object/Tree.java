package logic.object;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class Tree extends GameObject {

	public Tree(String name, double x, double y) {
		super(name, x, y, new Rectangle2D(x + 40, y + 160, 44, 32));
		this.setImage(new Image(ClassLoader.getSystemResource("Images/Tree.png").toString()));
	}

}

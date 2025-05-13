package logic.object;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import logic.game.GameController;

public class Shop extends GameObject implements Interactable {
	private final Rectangle2D interactArea;

	public Shop(String name, double x, double y) {
		super(name, x, y, new Rectangle2D(x + 9, y + 115, 49, 13));
		this.setImage(new Image(ClassLoader.getSystemResource("Images/Shop.png").toString()));
		this.interactArea = new Rectangle2D(x, y + 128, 64, 64);
	}

	@Override
	public Rectangle2D getInteractArea() {
		return this.interactArea;
	}

	@Override
	public void interact() {
		GameController.getShopPane().refresh();
		GameController.getShopPane().setVisible(true);

	}

	@Override
	public boolean getCanInteract() {
		return true;
	}

}

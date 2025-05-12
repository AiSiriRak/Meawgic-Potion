package entity.base;

import java.net.URL;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Item {
	private String name;
	private int amount;
	private ImageView itemImage;

	protected Item(String name) {
		this.name = name;
		this.setAmount(0);
		this.itemImage = new ImageView(ClassLoader.getSystemResource("Images/"+name+".png").toString());
	}

	public ImageView getItemImage() {
		return itemImage;
	}

	public void setItemImage(ImageView itemImage) {
		this.itemImage = itemImage;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = Math.max(amount, 0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
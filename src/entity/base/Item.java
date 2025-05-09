package entity.base;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Item {
	private String name;
	private int capacity;
	private ImageView itemImage;

	protected Item(String name, int capacity) {
		this.name = name;
		this.setCapacity(capacity);
		this.itemImage = new ImageView(ClassLoader.getSystemResource("Images/" + name + ".png").toString());
		this.itemImage.setSmooth(false);
	}

	public ImageView getItemImage() {
		return itemImage;
	}

	public void setItemImage(ImageView itemImage) {
		this.itemImage = itemImage;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		if (capacity < 0)
			this.capacity = 0;
		else
			this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

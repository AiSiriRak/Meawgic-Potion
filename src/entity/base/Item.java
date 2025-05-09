package entity.base;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Item {
    private String name;
    private int capacity;
    private Image itemImage;

    protected Item(String name, int capacity) {
        this.name = name;
        this.setCapacity(10);
        this.itemImage = new Image(ClassLoader.getSystemResource("Images/" + name + ".png").toString());
    }

    public ImageView getItemImage() {
        return new ImageView(itemImage);
    }

    public void setItemImage(Image itemImage) {
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

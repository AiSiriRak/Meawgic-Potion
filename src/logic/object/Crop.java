package logic.object;

import java.lang.classfile.instruction.SwitchCase;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Crop extends GameObject implements Interactable {
	protected Rectangle2D interactArea;
	private Image item;
	private int currentStage;

	public Crop(String name, double x, double y) {
		super(name, x, y, new Rectangle2D(x + 0, y + 10, 192, 182));
		this.setImage(new Image(ClassLoader.getSystemResource("Images/Crop_0.png").toString()));
		this.interactArea = new Rectangle2D(x + 64, y + 192, 64, 64);
		this.currentStage = 0;
		this.item = null;

	}

	@Override
	public Rectangle2D getInteractArea() {
		return this.interactArea;
	}

	@Override
	public void interact() {
		System.out.println("Interact with " + this.name);

		if (this.currentStage != 3) {
			changeStage(this.currentStage + 1);
		} else {
			changeStage(0);
		}

	}

	public void interactAreaRender(GraphicsContext gc, double camX, double camY) {

		gc.setFill(Color.GREEN);
		gc.fillRect(interactArea.getMinX() - camX, interactArea.getMinY() - camY, interactArea.getWidth(),
				interactArea.getHeight());

	}

	public Image getItem() {
		return this.item;
	}

	public void setItem(Image item) {
		this.item = item;
	}

	public void changeStage(int stage) {
		this.currentStage = stage;

		Canvas canvas = new Canvas(192, 192);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		Image img = null;
		switch (this.currentStage) {
		case 0:
			img = new Image(ClassLoader.getSystemResource("Images/Crop_0.png").toString());
			break;
		case 1:
			img = new Image(ClassLoader.getSystemResource("Images/Crop_1.png").toString());
			break;
		case 2:
			img = new Image(ClassLoader.getSystemResource("Images/Crop_2.png").toString());
			break;
		case 3:
			img = new Image(ClassLoader.getSystemResource("Images/Crop_3.png").toString());
			break;
		}
		gc.drawImage(img, 0, 0);
		if (this.currentStage != 0) {
			gc.drawImage(this.item, 72, 121);
			if (this.currentStage == 3)
				gc.drawImage(this.item, 72, 27);
		}

		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);
		WritableImage combined = new WritableImage(192, 192);
		canvas.snapshot(params, combined);

		this.setImage(combined);
	}

}

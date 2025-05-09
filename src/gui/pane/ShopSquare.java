package gui.pane;

import Font.FontRect;
import entity.base.Basis;
import entity.base.Item;
import entity.base.Potion;
import entity.data.BasisData;
import entity.data.PotionData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ShopSquare extends StackPane {
	private ImageView frame;
	private boolean isEnoughCoin;

	private VBox itemDisplay;
	private Potion item;
	private int quantity;

	public ShopSquare() {
		this.frame = new ImageView(ClassLoader.getSystemResource("Images/Shop_Frame.png").toString());
		this.isEnoughCoin = true;

		setMouseAction();
		setupNewGoods();
		update();
	}

	private void setMouseAction() {
		if (this.isEnoughCoin) {
			setOnMouseEntered(e -> {
				setScaleX(1.05);
				setScaleY(1.05);
			});
			setOnMouseExited(e -> {
				setScaleX(1);
				setScaleY(1);
			});
			setOnMouseClicked(e -> {
				buy();
				System.out.println("Brought!");
			});
		}

	}

	private void update() {
		getChildren().clear();
		getChildren().addAll(this.frame, this.itemDisplay);

	}

	private void buy() {
		setupNewGoods();
	}

	private void setupNewGoods() {
		this.item = PotionData.FIRE_RESISTANCE.getItem();
		this.quantity = (int) (Math.random() * 5) + 1;

		this.itemDisplay = new VBox(20);
		this.itemDisplay.setAlignment(Pos.CENTER);

		HBox upper = new HBox(15);
		upper.setAlignment(Pos.CENTER);

		ImageView itemImg = new ImageView(
				ClassLoader.getSystemResource("Images/" + this.item.getName() + ".png").toString());
		itemImg.setScaleX(2);
		itemImg.setScaleY(2);

		Text quantityTxt = new Text("x " + this.quantity);
		quantityTxt.setFont(FontRect.REGULAR.getFont(24));
		quantityTxt.setFill(Color.web("#34022A"));

		upper.getChildren().addAll(itemImg, quantityTxt);

		HBox lower = new HBox(10);
		lower.setAlignment(Pos.CENTER);

		Rectangle coin = new Rectangle(20, 20);

		Text price = new Text("" + this.item.getSellPrice() * this.quantity);
		price.setFont(FontRect.REGULAR.getFont(24));
		price.setFill(Color.web("#34022A"));
		lower.getChildren().addAll(coin, price);

		this.itemDisplay.getChildren().addAll(upper, lower);

		update();
	}

}

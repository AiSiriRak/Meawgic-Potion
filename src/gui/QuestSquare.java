package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Font.FontRect;
import entity.base.Crop;
import entity.base.Item;
import entity.base.Potion;
import entity.data.CropData;
import entity.data.PotionData;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.game.GameController;
import logic.game.SoundController;

public class QuestSquare extends StackPane {
	private final ImageView frame;
	private final ImageView coin;
	private boolean isEnoughItem;

	private VBox itemDisplay;
	private Item item;
	private int sellPrice;
	private String itemNameDisplay;
	private int quantity;

	public QuestSquare() {
		this.frame = new ImageView(ClassLoader.getSystemResource("Images/Shop_Frame.png").toString());
		this.coin = new ImageView(ClassLoader.getSystemResource("Images/Coin.png").toString());
		this.coin.setScaleX(0.625);
		this.coin.setScaleY(0.625);
		this.isEnoughItem = true;
		setupNewGood();
		update();
	}

	public void update() {
		checkIsEnoughItem();
		setMouseAction();
		getChildren().clear();
		getChildren().addAll(this.frame, this.itemDisplay);

	}

	private void setMouseAction() {
		if (this.isEnoughItem) {
			setOnMouseEntered(e -> {
				setScaleX(1.05);
				setScaleY(1.05);
			});
			setOnMouseExited(e -> {
				setScaleX(1);
				setScaleY(1);
			});
			setOnMouseClicked(e -> {
				sell();
				System.out.println("Sold!");
			});
		} else {
			setOnMouseEntered(null);
			setOnMouseExited(null);
			setOnMouseClicked(null);
		}

	}

	private void sell() {
		if (item instanceof Potion) {
			for (Potion p : GameController.getInventoryPane().getPotionCounter().getPotionCounter()) {
				if (p.getName().equals(item.getName())) {
					p.setAmount(p.getAmount() - quantity);
					break;
				}
			}
		} else {
			for (Crop b : GameController.getInventoryPane().getIngredientCounter().getCropCounter()) {
				if (b.getName().equals(item.getName())) {
					b.setAmount(b.getAmount() - quantity);
					break;
				}
			}
		}

		GameController.coin.increaseCoin(sellPrice * quantity);
		GameController.updateCoinDisplay();
		GameController.getInventoryPane().refreshInventory();
		SoundController.getInstance().playEffectSound("Sell");
		setupNewGood();
	}

	private void setupNewGood() {
		this.item = getRandomItem();
		this.sellPrice = (this.item instanceof Potion) ? ((Potion) this.item).getSellPrice()
				: ((Crop) this.item).getSellPrice();
		this.itemNameDisplay = (this.item instanceof Potion) ? this.item.getName() + "\nPotion" : this.item.getName();
		this.quantity = (int) (Math.random() * 3) + 1;

		this.itemDisplay = new VBox(-2);
		this.itemDisplay.setAlignment(Pos.CENTER);

		Text itemName = new Text(this.itemNameDisplay);
		itemName.setFont(FontRect.REGULAR.getFont(12));
		itemName.setFill(Color.web("#7A3E58"));
		itemName.setTextAlignment(TextAlignment.CENTER);

		HBox upper = new HBox();
		upper.setAlignment(Pos.CENTER);
		upper.setTranslateX(-7);

		ImageView itemImg = new ImageView(
				ClassLoader.getSystemResource("Images/" + this.item.getName() + ".png").toString());
		itemImg.setScaleX(0.7);
		itemImg.setScaleY(0.7);

		Text quantityTxt = new Text("x " + this.quantity);
		quantityTxt.setFont(FontRect.REGULAR.getFont(24));
		quantityTxt.setFill(Color.web("#34022A"));

		upper.getChildren().addAll(itemImg, quantityTxt);

		HBox lower = new HBox(6);
		lower.setAlignment(Pos.CENTER);

		Text price = new Text("" + (this.sellPrice * this.quantity));
		price.setFont(FontRect.REGULAR.getFont(18));
		price.setFill(Color.web("#34022A"));
		lower.getChildren().addAll(this.coin, price);

		this.itemDisplay.getChildren().addAll(itemName, upper, lower);

		update();
	}

	private Item getRandomItem() {
		List<Item> allItems = new ArrayList<>();

		for (PotionData p : PotionData.values()) {
			allItems.add(p.getItem());
		}

		for (CropData b : CropData.values()) {
			allItems.add(b.getItem());
		}

		int index = new Random().nextInt(allItems.size());
		return allItems.get(index);
	}

	private void checkIsEnoughItem() {
		if (this.item.getAmount() >= this.quantity) {
			this.isEnoughItem = true;
			setOpacity(1);
		} else {
			this.isEnoughItem = false;
			setOpacity(0.5);
		}
	}
}

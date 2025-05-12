package gui;

import java.util.ArrayList;

import Font.FontRect;
import Inventory.IngredientCounter;
import entity.base.Crop;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.game.GameController;
import logic.game.SoundController;
import logic.object.CropPlot;

public class PlantPane extends StackPane {
	private final ArrayList<InventorySquare> ingredientAllCells = new ArrayList<>();
	private CropPlot associatedCrop;

	public PlantPane(CropPlot associatedCrop) {
		this.associatedCrop = associatedCrop;
		VBox contentBox = new VBox(10);
		Image image = new Image(ClassLoader.getSystemResource("Images/Inventory_pane.png").toString());
		BackgroundImage bgImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true));
		contentBox.setPrefSize(272, 209);
		contentBox.setMinSize(272, 209);
		contentBox.setMaxSize(272, 209);
		contentBox.setAlignment(Pos.CENTER);
		contentBox.setPadding(new Insets(10));
		contentBox.setBackground(new Background(bgImage));
		
		Text inventoryLabel = new Text("SEED");
		inventoryLabel.setFont(FontRect.BOLD.getFont(24));
		GridPane ingredientGrid = createSeedGrid(ingredientAllCells);

		contentBox.getChildren().addAll(inventoryLabel, ingredientGrid);
		GameButton exitButton = new GameButton("Exit", "Click_ingredient");
		exitButton.setOnMouseClicked(e -> this.setVisible(false));

		StackPane.setAlignment(contentBox, Pos.CENTER);
		exitButton.setTranslateX(180);
		exitButton.setTranslateY(-180);
		StackPane.setMargin(exitButton, new Insets(170, 100, 0, 0));

		this.getChildren().addAll(contentBox, exitButton);
		this.setPickOnBounds(false);
	}

	private GridPane createSeedGrid(ArrayList<InventorySquare> cellList) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(5);
		grid.setVgap(5);
		grid.setPickOnBounds(true);
		grid.setMouseTransparent(false);

		ArrayList<Crop> ingredients = new IngredientCounter().getCropCounter();

		int index = 0;
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 4; col++) {
				InventorySquare square = new InventorySquare(col, row, "Inventory");
				square.setPrefSize(48, 48);
				square.setPickOnBounds(true);
				square.setMouseTransparent(false);
				cellList.add(square);
				grid.add(square, col, row);

				if (index < ingredients.size()) {
					Crop ingredient = ingredients.get(index++);
					ImageView imageView = new ImageView(ingredient.getItemImage().getImage());
					imageView.setFitWidth(35);
					imageView.setFitHeight(35);
					imageView.setPickOnBounds(false);
					imageView.setMouseTransparent(true);

					Text priceText = new Text(String.valueOf(ingredient.getBuyPrice()));
					priceText.setFont(FontRect.REGULAR.getFont(14));
					priceText.setFill(Color.WHITE);

					ImageView coinImage = new ImageView(ClassLoader.getSystemResource("Images/Coin.png").toString());
					coinImage.setFitWidth(14);
					coinImage.setPreserveRatio(true);
					coinImage.setMouseTransparent(true);

					HBox priceContainer = new HBox(2, coinImage, priceText);
					priceContainer.setAlignment(Pos.BOTTOM_RIGHT);
					priceContainer.setMouseTransparent(true);

					StackPane contentStack = new StackPane();
					contentStack.getChildren().addAll(imageView, priceContainer);
					contentStack.setMouseTransparent(true);

					square.getChildren().add(contentStack);

					Tooltip tooltip = new Tooltip(ingredient.getName());
					Tooltip.install(square, tooltip);

					square.setOnMouseClicked(e -> {
						System.out.println("Attempting to purchase: " + ingredient.getName());
						if (GameController.coin.decreaseCoin(ingredient.getBuyPrice())) {
							System.out.println("Purchase successful!");
							associatedCrop.setItem(ingredient);
							associatedCrop.changeStage(1);
							SoundController.getInstance().playEffectSound("Buy");
							this.setVisible(false);
						} else {
							SoundController.getInstance().playEffectSound("Wrong");
						}
					});
				}
			}
		}
		return grid;
	}

	public void show() {
		System.out.println("PlantPane is showing!");
		this.setVisible(true);
		this.toFront();
	}
	
}

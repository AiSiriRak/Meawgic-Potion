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
	private final ArrayList<InventorySquare> inallCells = new ArrayList<>();
	private CropPlot associatedCrop;

	public PlantPane(CropPlot associatedCrop) {
		this.associatedCrop = associatedCrop;
		VBox content = createContentBox();
		GameButton exitButton = createExitButton();

		StackPane.setAlignment(content, Pos.CENTER);
		exitButton.setTranslateX(180);
		exitButton.setTranslateY(-180);
		StackPane.setMargin(exitButton, new Insets(170, 100, 0, 0));

		this.getChildren().addAll(content, exitButton);
		this.setPickOnBounds(false);
	}

	private Background createBackgroundImage(String filename) {
		Image image = new Image(ClassLoader.getSystemResource("Images/" + filename).toString());
		BackgroundImage bgImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true));
		return new Background(bgImage);
	}

	private Text createTitleText(String text, int fontSize) {
		Text title = new Text(text);
		title.setFont(FontRect.BOLD.getFont(fontSize));
		return title;
	}

	private VBox createContentBox() {
		VBox contentBox = new VBox(10);
		contentBox.setPrefSize(272, 209);
		contentBox.setMinSize(272, 209);
		contentBox.setMaxSize(272, 209);
		contentBox.setAlignment(Pos.CENTER);
		contentBox.setPadding(new Insets(10));
		contentBox.setBackground(createBackgroundImage("Inventory_pane.png"));

		Text inventoryLabel = createTitleText("SEED", 24);
		GridPane ingredientGrid = createInventoryGrid(inallCells);

		contentBox.getChildren().addAll(inventoryLabel, ingredientGrid);
		return contentBox;
	}

	private GridPane createInventoryGrid(ArrayList<InventorySquare> cellList) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(5);
		grid.setVgap(5);
		grid.setPickOnBounds(true);
		grid.setMouseTransparent(false);

		ArrayList<Crop> ingredients = new IngredientCounter().getBasisCounter();

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

					ImageView coinView = new ImageView(ClassLoader.getSystemResource("Images/coin.png").toString());
					coinView.setFitWidth(14);
					coinView.setPreserveRatio(true);
					coinView.setMouseTransparent(true);

					HBox priceContainer = new HBox(2, coinView, priceText);
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
							SoundController.getInstance().playEffectSound("buy");
							this.setVisible(false);
						} else {
							SoundController.getInstance().playEffectSound("Wrong");
						}
						e.consume();
					});
				}
			}
		}
		return grid;
	}

	private GameButton createExitButton() {
		GameButton exitButton = new GameButton("Exit");
		exitButton.setOnMouseEntered(e -> {
			exitButton.setScaleX(1.08);
			exitButton.setScaleY(1.08);
		});

		exitButton.setOnMouseExited(e -> {
			exitButton.setScaleX(1);
			exitButton.setScaleY(1);
		});
		exitButton.setOnMouseClicked(e -> this.setVisible(false));
		return exitButton;
	}

	public void show() {
		System.out.println("PlantPane is showing!");
		this.setVisible(true);
		this.toFront();

	}

	public CropPlot getAssociatedCrop() {
		return this.associatedCrop;
	}
}

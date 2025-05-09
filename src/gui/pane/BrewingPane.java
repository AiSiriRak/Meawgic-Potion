package gui.pane;

import java.util.ArrayList;

import Font.FontRect;
import Inventory.IngredientCounter;
import Inventory.PotionCounter;
import entity.base.Ingredient;
import entity.base.Potion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class BrewingPane extends VBox {
	private ArrayList<InventorySquare> poallCells;
	private ArrayList<InventorySquare> inallCells;
	private IngredientCounter ingredientCounter;
	private PotionCounter potionCounter;

	public BrewingPane() {
		this.inallCells = new ArrayList<>();
		this.poallCells = new ArrayList<>();

		Image backgroundImage = new Image(ClassLoader.getSystemResource("Images/" + "Brewing_pane.png").toString());
		BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true));
		this.setBackground(new Background(bgImage));
		this.setPrefSize(416, 320);
		this.setMinSize(416, 320);
		this.setMaxSize(416, 320);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(10));
		this.setSpacing(10);

		Text Inventory = new Text("INVENTORY");
		Inventory.setFont(FontRect.REGULAR.getFont(24));

		if (ingredientCounter == null) {
			ingredientCounter = new IngredientCounter();
		}
		if (potionCounter == null) {
			potionCounter = new PotionCounter();
		}
		int index = 0;

		ArrayList<Ingredient> ingredients = ingredientCounter.getIngredientCounter();
		ArrayList<Potion> potions = potionCounter.getPotionCounter();

		GridPane ingredient = new GridPane();
		ingredient.setAlignment(Pos.CENTER);
		ingredient.setVgap(5);
		ingredient.setHgap(5);
		for (int row = 0; row < 2; row++) {
			for (int col = 0; col < 7; col++) {
				InventorySquare s = new InventorySquare(col, row, "Brewing");
				s.setPrefSize(48, 48);
				inallCells.add(s);
				ingredient.add(s, col, row);
				if (index < ingredients.size()) {
					Ingredient ingredientss = ingredients.get(index++);
					ImageView imageView = ingredientss.getItemImage();
					imageView.setFitWidth(35);
					imageView.setFitHeight(35);
					s.setAlignment(Pos.CENTER);

					s.getChildren().add(imageView);
					Text capacityText = new Text(String.valueOf(ingredientss.getCapacity()));
					capacityText.setStyle("-fx-fill: white; -fx-font-size: 16;");

					StackPane.setAlignment(capacityText, Pos.BOTTOM_RIGHT);
					s.getChildren().add(capacityText);

					Tooltip tooltip = new Tooltip(ingredientss.getName());
					tooltip.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 12;");
					tooltip.setShowDelay(Duration.millis(300));
					tooltip.setHideDelay(Duration.millis(100));
					Tooltip.install(s, tooltip);

				}
			}
		}

		Text Potions = new Text("POTIONS");
		Potions.setFont(FontRect.REGULAR.getFont(16));

		index = 0;
		GridPane potion = new GridPane();
		potion.setAlignment(Pos.CENTER);
		potion.setVgap(5);
		potion.setHgap(5);
		for (int row = 0; row < 2; row++) {
			for (int col = 0; col < 7; col++) {
				InventorySquare s = new InventorySquare(col, row, "Brewing");
				s.setPrefSize(48, 48);
				poallCells.add(s);
				potion.add(s, col, row);
				if (index < potions.size()) {
					Potion potionss = potions.get(index++);
					ImageView imageView = potionss.getItemImage();
					imageView.setFitWidth(35);
					imageView.setFitHeight(35);
					s.setAlignment(Pos.CENTER);

					s.getChildren().add(imageView);
					Text capacityText = new Text(String.valueOf(potionss.getCapacity()));
					capacityText.setStyle("-fx-fill: white; -fx-font-size: 16;");

					StackPane.setAlignment(capacityText, Pos.BOTTOM_RIGHT);
					s.getChildren().add(capacityText);

					Tooltip tooltip = new Tooltip(potionss.getName());
					tooltip.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 12;");
					tooltip.setShowDelay(Duration.millis(300));
					tooltip.setHideDelay(Duration.millis(100));
					Tooltip.install(s, tooltip);
				}
			}
		}

		this.getChildren().addAll(Inventory, ingredient, Potions, potion);

//        ImageView exitButton = new ImageView(ClassLoader.getSystemResource("Exit_btn.png").toString());
//        exitButton.setFitWidth(48);
//        exitButton.setPreserveRatio(true);
//        exitButton.setSmooth(true);

//        AnchorPane container = new AnchorPane();
//        container.setPrefSize(500, 400);

//        AnchorPane.setTopAnchor(allContent, 200.0);
//        AnchorPane.setLeftAnchor(allContent, 58.0);  // Adjust for centering
//        container.getChildren().add(allContent);

//        AnchorPane.setTopAnchor(exitButton, 125.0);
//        AnchorPane.setRightAnchor(exitButton, 10.0);
//        container.getChildren().add(exitButton);

	}
}
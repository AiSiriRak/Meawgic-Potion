package gui.pane;

import java.util.ArrayList;

import Font.FontRect;
import Inventory.IngredientCounter;
import Inventory.PotionCounter;
import entity.base.Basis;
import entity.base.Ingredient;
import entity.base.Potion;
import gui.button.ExitButtton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import logic.game.Coin;
import logic.game.GameController;
import logic.object.Crop;

public class PlantPane extends StackPane {
	private final ArrayList<InventorySquare> inallCells = new ArrayList<>();
	private Coin coin;
	private Crop associatedCrop;

	public PlantPane(Crop associatedCrop) {
		this.associatedCrop = associatedCrop;
		VBox content = createContentBox();
		ExitButtton exitButton = createExitButton();

		AnchorPane container = new AnchorPane();
		container.setPrefSize(500, 400);
		
		AnchorPane.setTopAnchor(exitButton, 170.0);
		AnchorPane.setRightAnchor(exitButton, 100.0);
		container.getChildren().add(exitButton);

		PlantPane.setAlignment(content,Pos.CENTER);
		this.getChildren().addAll(content,container);
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

	    ArrayList<Basis> ingredients = new IngredientCounter().getBasisCounter();
	    
	    int index = 0;
	    for (int row = 0; row < 3; row++) {
	        for (int col = 0; col < 4; col++) {
	            InventorySquare square = new InventorySquare(col, row, "Inventory");
	            square.setPrefSize(48, 48);
	            cellList.add(square);
	            grid.add(square, col, row);

	            if (index < ingredients.size()) {
	                Basis ingredient = ingredients.get(index++);
	                ImageView imageView = ingredient.getItemImage();
	                imageView.setFitWidth(35);
	                imageView.setFitHeight(35);
	                square.setAlignment(Pos.CENTER);

	               
	                
	                Text priceText = new Text(String.valueOf(ingredient.getBuyPrice()));
	                priceText.setFont(FontRect.REGULAR.getFont(14));
	                priceText.setStyle("-fx-fill: white; -fx-font-size: 14;");
	                
	                ImageView coinView = new ImageView(ClassLoader.getSystemResource("Images/Brewing_frame.png").toString());
	                coinView.setFitWidth(14);
	                coinView.setPreserveRatio(true);
	                coinView.setSmooth(true);
	                
	                HBox container = new HBox();
	                container.getChildren().addAll(coinView, priceText);
	                container.setAlignment(Pos.BOTTOM_RIGHT);
	                StackPane imageStack = new StackPane();
	                imageStack.setAlignment(Pos.CENTER);
	                imageStack.getChildren().add(imageView);

	                StackPane.setAlignment(container, Pos.BOTTOM_RIGHT);
	                imageStack.getChildren().add(container);

	                square.getChildren().add(imageStack);

	                Tooltip tooltip = new Tooltip(ingredient.getName());
	                tooltip.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 12;");
	                tooltip.setShowDelay(Duration.millis(300));
	                tooltip.setHideDelay(Duration.millis(100));
	                Tooltip.install(square, tooltip);
	                
	                square.setOnMouseClicked(e -> {
	                    System.out.println("Square clicked!");
	                    if (GameController.coin.tryPurchase(ingredient)) {
	                        System.out.println("Purchase successful!");
	                        associatedCrop.setItem(ingredient);
	                        associatedCrop.changeStage(1);
	                        this.setVisible(false);
	                    } else {
	                        System.out.println("Not enough coins!");
	                    }
	                });
	            }
	        }
	    }
	    return grid;
	}

	private ExitButtton createExitButton() {
		ExitButtton exitButton = new ExitButtton();
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
}

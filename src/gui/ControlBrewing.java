package gui;

import Inventory.IngredientCounter;
import Inventory.PotionCounter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.game.SoundController;
import logic.object.Pot;

public class ControlBrewing extends AnchorPane {
	private Pot associatedPot;
	private BrewingStand brewingStand;
	private BrewingPane brewingPane;

	public ControlBrewing(IngredientCounter ingredientCounter, PotionCounter potionCounter, Pot pot) {
		this.associatedPot = pot;
		this.brewingStand = new BrewingStand(pot);
		this.brewingPane = new BrewingPane(brewingStand, ingredientCounter, potionCounter, this);
		setupUI();
	}

	private void setupUI() {
		VBox contentBox = new VBox();
		contentBox.setPadding(new Insets(10));
		contentBox.setPrefSize(960, 600);
		contentBox.setAlignment(Pos.CENTER);
		contentBox.getChildren().addAll(brewingStand, brewingPane);
		
		
		GameButton resetButton = new GameButton("ResetIngredient", 150, "Click_ingredient");
		resetButton.setOnMouseClicked(e -> brewingStand.resetIngredients());

		GameButton craftButton = new GameButton("Craft",150, "Click_ingredient");
		craftButton.setOnMouseClicked(e -> {
			if (brewingStand.craftable()) {
				brewingStand.craftPotion();
				brewingStand.resetIngredients();
				associatedPot.changeStage(1);
				this.setVisible(false);
				SoundController.getInstance().playEffectSound("Sell");
			}
			else {
				SoundController.getInstance().playEffectSound("Wrong");
			}
		});

		HBox controlBox = new HBox(10, craftButton, resetButton);
		controlBox.setAlignment(Pos.CENTER);
		contentBox.getChildren().add(controlBox);

		GameButton exitButton = new GameButton("Exit", "Click_ingredient");
		exitButton.setOnMouseClicked(e -> this.setVisible(false));

		this.setPrefSize(500, 400);
		AnchorPane.setTopAnchor(contentBox, 10.0);
		AnchorPane.setLeftAnchor(contentBox, 35.0);
		AnchorPane.setTopAnchor(exitButton, 25.0);
		AnchorPane.setRightAnchor(exitButton, 255.0);

		this.getChildren().addAll(contentBox, exitButton);
	}

	public void show() {
		this.setVisible(true);
		this.toFront();
		brewingPane.refreshInventory();
	}

	public Pot getAssociatedPot() {
		return associatedPot;
	}
}
package gui;

import inventory.IngredientCounter;
import inventory.PotionCounter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import logic.game.SoundController;
import logic.object.Pot;

public class ControlBrewing extends StackPane {
	private Pot associatedPot;
	private BrewingStand brewingStand;
	private BrewingPane brewingPane;

	public ControlBrewing(IngredientCounter ingredientCounter, PotionCounter potionCounter, Pot pot) {
		this.associatedPot = pot;
		this.brewingStand = new BrewingStand(pot);
		this.brewingPane = new BrewingPane(brewingStand, ingredientCounter, potionCounter, this);
		
		VBox contentBox = new VBox();
		contentBox.setPadding(new Insets(10));
		contentBox.setPrefSize(960, 600);
		contentBox.setAlignment(Pos.CENTER);
		contentBox.getChildren().addAll(brewingStand, brewingPane);
		
		
		GameButton resetButton = new GameButton("ResetIngredient", 150, "Click_ingredient");
		resetButton.setOnMouseClicked(e -> brewingStand.resetIngredients());

		GameButton brewButton = new GameButton("Brew",150, "Click_ingredient");
		brewButton.setOnMouseClicked(e -> {
			if (brewingStand.brewable()) {
				brewingStand.brewPotion();
				brewingStand.resetIngredients();
				associatedPot.changeStage(1);
				this.setVisible(false);
				SoundController.getInstance().playEffectSound("Sell");
			}
			else {
				SoundController.getInstance().playEffectSound("Wrong");
			}
		});

		HBox controlBox = new HBox(10, brewButton, resetButton);
		controlBox.setAlignment(Pos.CENTER);
		contentBox.getChildren().add(controlBox);

		GameButton exitButton = new GameButton("Exit", "Click_ingredient");
		exitButton.setOnMouseClicked(e -> {
			brewingStand.resetIngredients();
			this.setVisible(false);          
		});
		exitButton.setTranslateX(200);
		exitButton.setTranslateY(-255);
		
		this.getChildren().addAll(contentBox, exitButton);
	}

	public void show() {
		this.setVisible(true);
		this.toFront();
		brewingPane.refreshInventory();
	}

}
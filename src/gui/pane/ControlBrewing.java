package gui.pane;

import Inventory.IngredientCounter;
import Inventory.PotionCounter;
import entity.base.Potion;
import gui.button.ExitButtton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import logic.object.Pot;

public class ControlBrewing extends AnchorPane {
    private Pot associatedPot;
    private BrewingStand brewingStand;
    private BrewingPane brewingPane;
    
    public ControlBrewing(IngredientCounter ingredientCounter, PotionCounter potionCounter) {
        this.brewingStand = new BrewingStand();
        this.brewingPane = new BrewingPane(brewingStand, ingredientCounter, potionCounter);
        setupUI();
    }


    private void setupUI() {
        VBox contentBox = new VBox();
        contentBox.setPadding(new Insets(10));
        contentBox.setPrefSize(960, 600);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.getChildren().addAll(brewingStand, brewingPane);

        Button resetButton = new Button("Reset Ingredients");
        resetButton.setOnAction(e -> brewingStand.resetIngredients());

        Button craftButton = new Button("Craft Potion");
        craftButton.setOnAction(e -> this.setVisible(false));

        VBox controlBox = new VBox(10, craftButton, resetButton);
        controlBox.setAlignment(Pos.CENTER);
        contentBox.getChildren().add(controlBox);

        ExitButtton exitButton = new ExitButtton();
        exitButton.setOnMouseClicked(e -> this.setVisible(false));

        this.setPrefSize(500, 400);
        AnchorPane.setTopAnchor(contentBox, 10.0);
        AnchorPane.setLeftAnchor(contentBox, 35.0);
        AnchorPane.setTopAnchor(exitButton, 55.0);
        AnchorPane.setRightAnchor(exitButton, 260.0);

        this.getChildren().addAll(contentBox, exitButton);
    }

    
//    public ControlBrewing() {
//    	
//    	    
//    	    this.brewingStand = new BrewingStand();
//    	    this.brewingPane = new BrewingPane(brewingStand);
//
//        VBox contentBox = new VBox();
//        contentBox.setPadding(new Insets(10));
//        contentBox.setPrefSize(960, 600);
//        contentBox.setAlignment(Pos.CENTER);
//        contentBox.getChildren().addAll(brewingStand, brewingPane);
//
//        Button resetButton = new Button("Reset Ingredients");
//        resetButton.setOnAction(e -> brewingStand.resetIngredients());
//
//        Button craftButton = new Button("Craft Potion");
//        craftButton.setOnAction(e -> {
//                this.setVisible(false);
//        });
//
//        VBox controlBox = new VBox(10, craftButton, resetButton);
//        controlBox.setAlignment(Pos.CENTER);
//        contentBox.getChildren().add(controlBox);
//
//        ExitButtton exitButton = new ExitButtton();
//        exitButton.setOnMouseClicked(e -> this.setVisible(false));
//
//        this.setPrefSize(500, 400);
//        AnchorPane.setTopAnchor(contentBox, 10.0);
//        AnchorPane.setLeftAnchor(contentBox, 35.0);
//        AnchorPane.setTopAnchor(exitButton, 55.0);
//        AnchorPane.setRightAnchor(exitButton, 260.0);
//
//        this.getChildren().addAll(contentBox, exitButton);
//    }
//    
//    public void resetAfterCrafting() {
//        brewingStand.resetIngredients();
//    }
}
package gui.pane;

import gui.button.ExitButtton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ControlBrewing extends AnchorPane {
    public ControlBrewing() {
        BrewingStand brewingStand = new BrewingStand();
        BrewingPane brewingPane = new BrewingPane(brewingStand);

        VBox contentBox = new VBox();
        contentBox.setPadding(new Insets(10));
        contentBox.setPrefSize(960, 600);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.getChildren().addAll(brewingStand, brewingPane);

        Button resetButton = new Button("Reset Ingredients");
        resetButton.setOnAction(e -> {
            brewingStand.resetIngredients();
        });

        Button craftButton = new Button("Craft Potion");
        craftButton.setOnAction(e -> {
            brewingStand.craftable();
        });

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
}
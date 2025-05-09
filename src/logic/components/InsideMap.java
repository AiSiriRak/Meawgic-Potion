package logic.components;

import java.util.ArrayList;

import gui.button.ExitButtton;
import gui.pane.BrewingPane;
import gui.pane.BrewingStand;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import logic.object.Door;
import logic.object.GameObject;
import logic.object.Pot;

public class InsideMap extends Map {

	private static ArrayList<GameObject> gameObjectList = new ArrayList<>();

	public InsideMap() {
		super(1080, 960, "Inside_Base", new Rectangle2D(262, 418, 556, 306), 508, 600, gameObjectList);

		this.setObject();
		initializeBrewingInterface();
	}

	public void setObject() {
		GameObject pot1 = new Pot("Pot - 1", 320, 480);
		GameObject pot2 = new Pot("Pot - 2", 632, 480);
		gameObjectList.add(pot1);
		gameObjectList.add(pot2);
		GameObject door = new Door("Door", 508, 668);
		gameObjectList.add(door);
		
	}
	
    private void initializeBrewingInterface() {
        VBox brewingContent = createBrewingContent();
        AnchorPane brewingContainer = createBrewingContainer(brewingContent);
        setupBrewingUI(brewingContainer);
    }

    private VBox createBrewingContent() {
        VBox contentBox = new VBox();
        contentBox.setPadding(new Insets(10));
        contentBox.setPrefSize(960, 600);
        contentBox.setAlignment(Pos.CENTER);
        
        BrewingStand brewingStand = new BrewingStand();
        BrewingPane brewingPane = new BrewingPane();
        contentBox.getChildren().addAll(brewingStand, brewingPane);
        
        return contentBox;
    }
    private AnchorPane createBrewingContainer(VBox content) {
        AnchorPane container = new AnchorPane();
        container.setPrefSize(500, 400);
        container.getChildren().add(content);
        AnchorPane.setTopAnchor(content, 10.0);
        AnchorPane.setLeftAnchor(content, 35.0);
        return container;
    }

    private void setupBrewingUI(AnchorPane container) {
        ExitButtton exitButton = createExitButton(container);
        container.getChildren().add(exitButton);
        AnchorPane.setTopAnchor(exitButton, 55.0);
        AnchorPane.setRightAnchor(exitButton, 260.0);
        container.setVisible(true);
    }

    private ExitButtton createExitButton(AnchorPane container) {
        ExitButtton exitButton = new ExitButtton();
        exitButton.setOnMouseClicked(e -> container.setVisible(false));
        return exitButton;
    }
}

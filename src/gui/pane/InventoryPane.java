package gui.pane;

import java.util.ArrayList;

import gui.button.ExitButtton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InventoryPane extends StackPane {
    private final ArrayList<InventorySquare> poallCells = new ArrayList<>();
    private final ArrayList<InventorySquare> inallCells = new ArrayList<>();

    public InventoryPane() {
        VBox content = createContentBox();
        ExitButtton exitButton = createExitButton();

        AnchorPane container = new AnchorPane();
        container.setPrefSize(500, 400);

        AnchorPane.setTopAnchor(content, 150.0);
        AnchorPane.setLeftAnchor(content, 275.0);
        container.getChildren().addAll(content, exitButton);

        AnchorPane.setTopAnchor(exitButton, 135.0);
        AnchorPane.setRightAnchor(exitButton, 250.0);

        this.setAlignment(Pos.CENTER);
        this.getChildren().add(container);
    }

    private VBox createContentBox() {
        VBox contentBox = new VBox(10);
        contentBox.setPrefSize(416, 320);
        contentBox.setMinSize(416, 320);
        contentBox.setMaxSize(416, 320);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(10));
        contentBox.setBackground(createBackgroundImage("Inventory_pane.png"));

        Text inventoryLabel = createTitleText("INVENTORY", 24);
        GridPane ingredientGrid = createInventoryGrid(inallCells);

        Text potionLabel = createTitleText("POTIONS", 16);
        GridPane potionGrid = createInventoryGrid(poallCells);

        contentBox.getChildren().addAll(inventoryLabel, ingredientGrid, potionLabel, potionGrid);
        return contentBox;
    }

    private GridPane createInventoryGrid(ArrayList<InventorySquare> cellList) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 7; col++) {
                InventorySquare square = new InventorySquare(col, row, "Inventory");
                square.setPrefSize(48, 48);
                cellList.add(square);
                grid.add(square, col, row);
            }
        }
        return grid;
    }

    private Text createTitleText(String text, int fontSize) {
        Text title = new Text(text);
        title.setFont(new Font(fontSize));
        return title;
    }

    private Background createBackgroundImage(String filename) {
        Image image = new Image(ClassLoader.getSystemResource("Images/" + filename).toString());
        BackgroundImage bgImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
        );
        return new Background(bgImage);
    }

    private ExitButtton createExitButton() {
        ExitButtton exitButton = new ExitButtton();
        exitButton.setOnMouseClicked(e -> this.setVisible(false));
        return exitButton;
    }
}

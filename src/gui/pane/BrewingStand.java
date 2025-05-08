package gui.pane;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class BrewingStand extends VBox {
	private ArrayList<InventorySquare> allCells;

	public BrewingStand() {
		this.allCells = new ArrayList<>();
		Image backgroundImage = new Image(ClassLoader.getSystemResource("Images/"+"Brewing_stand.png").toString());
		BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true));
		this.setBackground(new Background(bgImage));
		this.setPrefSize(416, 160);
		this.setMinSize(416, 160);
		this.setMaxSize(416, 160);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(10));
		this.setSpacing(20);
		
		GridPane ingredient = new GridPane();
		ingredient.setAlignment(Pos.CENTER);
		ingredient.setVgap(5);
		ingredient.setHgap(25);
		for (int col = 0; col < 3; col++) {
			InventorySquare s = new InventorySquare(col, 0, "Brewing");
			s.setPrefSize(48, 48);
			allCells.add(s);
			ingredient.add(s, col, 0);
		}
		
		GridPane potionPane = new GridPane();
        potionPane.setAlignment(Pos.CENTER);

        InventorySquare potion = new InventorySquare(0, 0, "Brewing");
        potion.setPrefSize(48, 48);
        potionPane.add(potion, 0, 0);
		
		this.getChildren().addAll(potionPane,ingredient);
	}
}
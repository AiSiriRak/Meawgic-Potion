package gui.pane;

import java.util.ArrayList;

import Font.FontRect;
import gui.button.ExitButtton;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.game.GameController;

public class ShopPane extends StackPane {

	private ImageView bg;
	private GridPane grid;

	public ShopPane() {

		bg = new ImageView(ClassLoader.getSystemResource("Images/Shop_Pane.png").toString());

		Text headText = new Text("Meawgic Shop");
		headText.setFont(FontRect.BOLD.getFont(52));
		headText.setFill(Color.web("#FAF5DF"));
		setAlignment(headText, Pos.TOP_CENTER);
		headText.setTranslateX(8);
		headText.setTranslateY(30);

		this.grid = new GridPane();
		this.grid.setHgap(28);
		this.grid.setVgap(6);
		this.grid.setAlignment(Pos.CENTER);
		this.grid.setTranslateY(-10);

		this.grid.add(new ShopSquare(), 0, 0);
		this.grid.add(new ShopSquare(), 1, 0);
		this.grid.add(new ShopSquare(), 0, 1);
		this.grid.add(new ShopSquare(), 1, 1);

		ExitButtton exitButton = getExitButton();
		setAlignment(exitButton, Pos.BOTTOM_CENTER);

		getChildren().addAll(bg, headText, grid, exitButton);
	}

	private ExitButtton getExitButton() {
		ExitButtton exitButton = new ExitButtton();
		exitButton.setTranslateY(-90);
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
}

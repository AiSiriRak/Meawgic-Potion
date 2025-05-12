package gui;

import Font.FontRect;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.game.GameController;

public class ShopPane extends StackPane {

	private final ImageView bg;
	private GridPane questGrid;

	public ShopPane() {

		bg = new ImageView(ClassLoader.getSystemResource("Images/Shop_Pane.png").toString());

		// Setup Header
		Text headText = setupHeadText();

		// Setup Grid
		setUpQuestGrid();

		// Get Exit Button
		GameButton exitButton = getExitButton();
		setAlignment(exitButton, Pos.BOTTOM_CENTER);

		getChildren().addAll(bg, headText, questGrid, exitButton);
	}

	public void refresh() {
		for (Node questSquare : this.questGrid.getChildren()) {
			((QuestSquare) questSquare).update();
		}
	}

	private GameButton getExitButton() {
		GameButton exitButton = new GameButton("Exit", "Click_ingredient");
		exitButton.setOnMouseClicked(e -> this.setVisible(false));
		exitButton.setTranslateY(-90);
		return exitButton;
	}

	private Text setupHeadText() {
		Text headText = new Text("Meawgic Shop");
		headText.setFont(FontRect.BOLD.getFont(52));
		headText.setFill(Color.web("#FAF5DF"));
		setAlignment(headText, Pos.TOP_CENTER);
		headText.setTranslateX(8);
		headText.setTranslateY(30);

		return headText;
	}

	private void setUpQuestGrid() {
		this.questGrid = new GridPane();
		this.questGrid.setHgap(28);
		this.questGrid.setVgap(6);
		this.questGrid.setAlignment(Pos.CENTER);
		this.questGrid.setTranslateY(-10);

		this.questGrid.add(new QuestSquare(), 0, 0);
		this.questGrid.add(new QuestSquare(), 1, 0);
		this.questGrid.add(new QuestSquare(), 0, 1);
		this.questGrid.add(new QuestSquare(), 1, 1);
	}

}

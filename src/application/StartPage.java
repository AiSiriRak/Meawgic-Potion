package application;

import gui.GameButton;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.game.GameController;

public class StartPage {
	private final Scene scene;

	public StartPage(Stage primaryStage) {

		// Set Root
		VBox root = new VBox(100);
		root.setAlignment(Pos.CENTER);
		root.setBackground(new Background(new BackgroundImage(
				new Image(ClassLoader.getSystemResource("Images/Start_BG.png").toString()), null, null, null, null)));

		// Set Game Logo
		ImageView gameLogo = new ImageView(ClassLoader.getSystemResource("Images/Game_Logo.png").toString());

		// Set Button HBox
		HBox btn = new HBox(200);
		btn.setAlignment(Pos.CENTER);

		GameButton startButton = new GameButton("StartGame",200, "Opening_game");
		startButton.setOnMouseClicked(e -> {
			primaryStage.setScene(GameController.getScene());
		});

		GameButton exitButton = new GameButton("ExitGame", 200, "Opening_game");
		exitButton.setOnMouseClicked(e -> primaryStage.close());

		btn.getChildren().addAll(startButton, exitButton);

		// Add All Nodes
		root.getChildren().addAll(gameLogo, btn);

		scene = new Scene(root, GameController.SCREEN_WIDTH, GameController.SCREEN_HEIGHT);
	}

	public Scene getScene() {
		return scene;
	}

}
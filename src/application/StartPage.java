package application;

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
		VBox root = new VBox(100);
		root.setAlignment(Pos.CENTER);
		root.setBackground(new Background(new BackgroundImage(
				new Image(ClassLoader.getSystemResource("Images/Start_BG.png").toString()), null, null, null, null)));
//		root.setBackground(new Background(new BackgroundFill(Color.web("#FAF5DF"), null, null)));

		HBox btn = new HBox(200);
		btn.setAlignment(Pos.CENTER);

		ImageView gameLogo = new ImageView(ClassLoader.getSystemResource("Images/Game_Logo.png").toString());

		ImageView startButton = new ImageView(ClassLoader.getSystemResource("Images/StartGame_btn.png").toString());
		startButton.setFitWidth(200);
		startButton.setPreserveRatio(true);
		startButton.setSmooth(true);
		startButton.setOnMouseEntered(e -> {
			startButton.setScaleX(1.08);
			startButton.setScaleY(1.08);
		});

		startButton.setOnMouseExited(e -> {
			startButton.setScaleX(1);
			startButton.setScaleY(1);
		});
		startButton.setOnMouseClicked(e -> {
			primaryStage.setScene(GameController.getScene());
//            GameController.setupScene();
		});

		ImageView exitButton = new ImageView(ClassLoader.getSystemResource("Images/ExitGame_btn.png").toString());
		exitButton.setFitWidth(200);
		exitButton.setPreserveRatio(true);
		exitButton.setSmooth(true);
		exitButton.setOnMouseEntered(e -> {
			exitButton.setScaleX(1.08);
			exitButton.setScaleY(1.08);
		});

		exitButton.setOnMouseExited(e -> {
			exitButton.setScaleX(1);
			exitButton.setScaleY(1);
		});
		exitButton.setOnMouseClicked(e -> primaryStage.close());

		btn.getChildren().addAll(startButton, exitButton);
		root.getChildren().addAll(gameLogo, btn);

		scene = new Scene(root, 960, 600);
	}

	public Scene getScene() {
		return scene;
	}
}
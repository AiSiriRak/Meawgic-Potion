package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.game.GameController;
import logic.game.SoundController;

public class Main extends Application {
	private static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		
		Main.primaryStage = primaryStage;
		GameController.setupScene();
		SoundController.getInstance().playMusic();

		//Set Stage
		StartPage startPage = new StartPage(primaryStage);
		Scene startScene = startPage.getScene();
		primaryStage.setScene(startScene);
		primaryStage.setTitle("Meowgic Potion");
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				Platform.exit();
				System.exit(0);
			}
		});
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

}


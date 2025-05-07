package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.game.GameController;

public class main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		GameController.setupScene();
		primaryStage.setScene(GameController.getScene());
		primaryStage.setTitle("Meawgic Potion");
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				Platform.exit();
				System.exit(0);
			}
		});
		primaryStage.setResizable(false);
	}

	public static void main(String[] args) {
		launch(args);
	}

}

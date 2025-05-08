package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import logic.game.GameController;

public class StartPage {
    private final Scene scene;

    public StartPage(Stage primaryStage) {
        VBox root = new VBox(100);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.VIOLET, null, null)));
        
        HBox btn = new HBox(200);
        btn.setAlignment(Pos.CENTER);
        
        Rectangle r = new Rectangle(600, 200);
        
        ImageView startButton = new ImageView(ClassLoader.getSystemResource("Images/StartGame_btn.png").toString());
        startButton.setFitWidth(200);
        startButton.setPreserveRatio(true);
        startButton.setSmooth(true);
        startButton.setOnMouseClicked(e -> {
        	primaryStage.setScene(GameController.getScene());
//            GameController.setupScene();
        });
        
        ImageView exitButton = new ImageView(ClassLoader.getSystemResource("Images/ExitGame_btn.png").toString());
        exitButton.setFitWidth(200);
        exitButton.setPreserveRatio(true);
        exitButton.setSmooth(true);
        exitButton.setOnMouseClicked(e -> primaryStage.close());
        
        btn.getChildren().addAll(startButton, exitButton);
        root.getChildren().addAll(r, btn);
        
        scene = new Scene(root, 960, 600);
    }

    public Scene getScene() {
        return scene;
    }
}
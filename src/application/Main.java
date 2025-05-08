package application;

import entity.ingredient.NetherWart;
import gui.button.ExitButtton;
import gui.pane.BrewingPane;
import gui.pane.BrewingStand;
import gui.pane.InventoryPane;
import gui.pane.SettingPane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.game.GameController;

public class Main extends Application {
	private static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		// ===================== run here =======================

		Main.primaryStage = primaryStage;
		GameController.setupScene();

		StartPage startPage = new StartPage(primaryStage);
		Scene startScene = startPage.getScene();
		primaryStage.setScene(startScene);
		primaryStage.setTitle("Meowgic Potions");
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				Platform.exit();
				System.exit(0);
			}
		});
		primaryStage.setResizable(false);

		// =================== end here ========================
		
//		HBox root = new HBox();
//		root.setPadding(new Insets(10));
//		root.setSpacing(10);
//		root.setPrefSize(960, 600);
//		root.setAlignment(Pos.CENTER);
//		
//		NetherWart n = new NetherWart();
//		root.getChildren().add(n.getItemImage());

//		VBox contentBox = new VBox();
//		contentBox.setPadding(new Insets(10));
////		root.setSpacing(10);
//		contentBox.setPrefSize(960, 600);
//		contentBox.setAlignment(Pos.CENTER);
//		
//		BrewingStand brewingStand = new BrewingStand();
//		contentBox.getChildren().addAll(brewingStand);
//		BrewingPane brewingPane = new BrewingPane();
//		contentBox.getChildren().addAll(brewingPane);
//		ExitButtton exitButton = new ExitButtton();
//		
//		AnchorPane container = new AnchorPane();
//		container.setPrefSize(500, 400);
//
//		AnchorPane.setTopAnchor(contentBox, 10.0);
//		AnchorPane.setLeftAnchor(contentBox, 35.0);
//		container.getChildren().add(contentBox);
//
//		AnchorPane.setTopAnchor(exitButton, 55.0);
//		AnchorPane.setRightAnchor(exitButton, 260.0);
//		container.getChildren().add(exitButton);
//		exitButton.setOnMouseClicked(e -> container.setVisible(false));

//		SettingPane settingPane = new SettingPane(primaryStage);
//		root.getChildren().addAll(settingPane);

//		InventoryPane inventoryPane = new InventoryPane();
//		root.getChildren().addAll(inventoryPane);
////		SettingPane settingPane = new SettingPane();
////		root.getChildren().addAll(settingPane);
//
//		Scene scene = new Scene(container);
//		primaryStage.setScene(scene);
//		primaryStage.setTitle("Meowgic Potions");
//		primaryStage.show();

//		
//
//		
//		GameController.setupScene();
//		primaryStage.setScene(GameController.getScene());
//		primaryStage.setTitle("Meawgic Potion");
//		primaryStage.show();
//		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//			@Override
//			public void handle(WindowEvent e) {
//				Platform.exit();
//				System.exit(0);
//			}
//		});
//		primaryStage.setResizable(false);
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}

//package application;
//
//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.event.EventHandler;
//import javafx.stage.Stage;
//import javafx.stage.WindowEvent;
//import logic.game.GameController;
//
//public class Main extends Application {
//
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		GameController.setupScene();
//		primaryStage.setScene(GameController.getScene());
//		primaryStage.setTitle("Meawgic Potion");
//		primaryStage.show();
//		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//			@Override
//			public void handle(WindowEvent e) {
//				Platform.exit();
//				System.exit(0);
//			}
//		});
//		primaryStage.setResizable(false);
//	}
//
//	public static void main(String[] args) {
//		launch(args);
//	}
//
//}
//package application;
//
//import gui.inventory.InventoryPane;
//import javafx.application.Application;
//import javafx.beans.binding.Bindings;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundFill;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//
//public class Main extends Application {
//	private Stage primaryStage;
//	private Scene openingScene;
//	private GamePage gamePage;
//
//	@Override
//	public void start(Stage primaryStage) throws Exception {     
//		HBox root = new HBox();
//        root.setPadding(new Insets(10));
//        root.setSpacing(10);
//        root.setPrefSize(960, 600);
//        root.setAlignment(Pos.CENTER);
//        
//        InventoryPane inventoryPane = new InventoryPane();
//        root.getChildren().addAll(inventoryPane);
////		this.primaryStage = primaryStage;
////        this.gamePage = new GamePage(this);
////        
////        createOpeningPage();
////        primaryStage.setScene(openingScene);
////        primaryStage.setTitle("Meowgic Potions");
////        primaryStage.show();
//        Scene scene = new Scene(root);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Meowgic Potions");
//        primaryStage.show();
//	}
//	
//	 private void createOpeningPage() {
//		 VBox root = new VBox(100);
//	        root.setAlignment(Pos.CENTER);
//	        root.setBackground(new Background(new BackgroundFill(Color.VIOLET, null, null)));
//	        
//	        HBox btn = new HBox();
//	        btn.setAlignment(Pos.CENTER);
//	        btn.setSpacing(200);;
//	        
//	        Rectangle r = new Rectangle();
//	        r.setWidth(600);
//	        r.setHeight(200);
//	        
//	        ImageView startButton = new ImageView(ClassLoader.getSystemResource("StartGame_btn.png").toString());
//	        startButton.setFitWidth(200);
//	        startButton.setPreserveRatio(true);
//	        startButton.setSmooth(true);
//	        startButton.setOnMouseClicked(e -> primaryStage.setScene(gamePage.getScene()));
//	        
//	        ImageView exitButton = new ImageView(ClassLoader.getSystemResource("ExitGame_btn.png").toString());
//	        exitButton.setFitWidth(200);
//	        exitButton.setPreserveRatio(true);
//	        exitButton.setSmooth(true);
//	        exitButton.setOnMouseClicked(e -> primaryStage.close());
//	          
//	        btn.getChildren().addAll(startButton,exitButton);
//	           
//			root.getChildren().addAll(r, btn);
//			
//			openingScene = new Scene(root, 960, 600);
//	    }
//	
//	public void returnToMenu() {
//        primaryStage.setScene(openingScene);
//    }
//
//
//	public static void main(String[] args) {
//		launch(args);
//	}
//
//}
//
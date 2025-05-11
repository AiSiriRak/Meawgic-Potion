package gui;

import Font.FontRect;
import application.StartPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.game.GameController;
import logic.game.SoundController;

public class SettingPane extends StackPane {
	private Stage primaryStage;
	private boolean soundEnabled;
	private boolean effectsEnabled;

	public SettingPane(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.soundEnabled = true;
		this.effectsEnabled = true;

		VBox contentBox = createContentBox();
		GameButton exitButton = new GameButton("Exit", "Click_ingredient");
		exitButton.setTranslateX(180);
		exitButton.setTranslateY(-100);
		exitButton.setOnMouseClicked(e -> this.setVisible(false));

		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(contentBox,exitButton);
	}

	private VBox createContentBox() {
		Image image = new Image(ClassLoader.getSystemResource("Images/Setting_pane.png").toString());
		BackgroundImage bg = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true));
		
		VBox contentBox = new VBox(5);
		contentBox.setPrefSize(384, 320);
		contentBox.setMinSize(384, 224);
		contentBox.setMaxSize(384, 224);
		contentBox.setAlignment(Pos.CENTER);
		contentBox.setPadding(new Insets(10));
		contentBox.setBackground(new Background(bg));

		Text title = new Text("Game Options");
		title.setFont(FontRect.REGULAR.getFont(35));

		VBox soundBox = new VBox(10, createToggleSetting("Sound", true), createToggleSetting("Effect", false));
		soundBox.setAlignment(Pos.CENTER);

		HBox buttonBox = new HBox(20);
		
		GameButton resetGame = new GameButton("ResetGame", 96, "Click_ingredient");
		resetGame.setOnMouseClicked(e -> {
			GameController.resetGame();
		});
		GameButton homeButton = new GameButton("Home", 96, "Click_ingredient");
		homeButton.setOnMouseClicked(e -> {
			returnToStartPage();
			this.setVisible(false);
		});

		buttonBox.getChildren().addAll(resetGame, homeButton);
		buttonBox.setAlignment(Pos.CENTER);

		contentBox.getChildren().addAll(title, soundBox, buttonBox);
		return contentBox;
	}
	
	private HBox createToggleSetting(String label, boolean isSound) {
		Text text = new Text(label);
		text.setFont(FontRect.REGULAR.getFont(20));
		ImageView toggleBtn = new ImageView(new Image(ClassLoader.getSystemResource("Images/On_btn.png").toString()));
		toggleBtn.setFitWidth(96);
		toggleBtn.setPreserveRatio(true);
		toggleBtn.setSmooth(true);

		toggleBtn.setOnMouseClicked(e -> {
			if (isSound)
				toggleSound(toggleBtn);
			else
				toggleEffect(toggleBtn);
		});

		HBox settingBox = new HBox(20, text, toggleBtn);
		settingBox.setAlignment(Pos.CENTER);
		return settingBox;
	}

	private void toggleSound(ImageView soundBtn) {
		soundEnabled = !soundEnabled;
		soundBtn.setImage(new Image(soundEnabled ? ClassLoader.getSystemResource("Images/On_btn.png").toString()
				: ClassLoader.getSystemResource("Images/Off_btn.png").toString()));
		SoundController.getInstance().setMusicEnabled(soundEnabled);
	}

	private void toggleEffect(ImageView effectBtn) {
		effectsEnabled = !effectsEnabled;
		effectBtn.setImage(new Image(effectsEnabled ? ClassLoader.getSystemResource("Images/On_btn.png").toString()
				: ClassLoader.getSystemResource("Images/Off_btn.png").toString()));
		SoundController.getInstance().setEffectsEnabled(effectsEnabled);
	}

	private void returnToStartPage() {
		StartPage startPage = new StartPage(primaryStage);
		primaryStage.setScene(startPage.getScene());
	}

}

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

public class SettingPane extends StackPane {
	private Stage primaryStage;
	private boolean soundEnabled = true;
	private boolean effectsEnabled = true;

	public SettingPane(Stage primaryStage) {
		this.primaryStage = primaryStage;

		VBox contentBox = createContentBox();
		GameButton exitButton = createExitButton();
		AnchorPane container = createContainer(contentBox, exitButton);

		this.setAlignment(Pos.CENTER);
		this.getChildren().add(container);
	}

	private VBox createContentBox() {
		VBox contentBox = new VBox(10);
		contentBox.setPrefSize(384, 320);
		contentBox.setMinSize(384, 224);
		contentBox.setMaxSize(384, 224);
		contentBox.setAlignment(Pos.CENTER);
		contentBox.setPadding(new Insets(10));
		contentBox.setBackground(createBackgroundImage("Setting_pane.png"));

		Text title = new Text("Game Options");
		title.setFont(FontRect.REGULAR.getFont(35));

		VBox soundBox = new VBox(20, createToggleSetting("Sound", true), createToggleSetting("Effect", false));
		soundBox.setAlignment(Pos.CENTER);

		HBox buttonBox = new HBox(20, createIconButton("ResetGame_btn.png", GameController::resetGame),
				createIconButton("Home_btn.png", () -> {
					returnToStartPage();
					this.setVisible(false);
				}));
		buttonBox.setAlignment(Pos.CENTER);

		contentBox.getChildren().addAll(title, soundBox, buttonBox);
		return contentBox;
	}

	private HBox createToggleSetting(String label, boolean isSound) {
		Text text = new Text(label);
		text.setFont(FontRect.REGULAR.getFont(16));
		ImageView toggleBtn = new ImageView(new Image(getImagePath("On_btn.png")));
		toggleBtn.setFitWidth(64);
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

	private ImageView createIconButton(String imageName, Runnable action) {
		ImageView button = new ImageView(new Image(getImagePath(imageName)));
		button.setFitWidth(96);
		button.setPreserveRatio(true);
		button.setSmooth(true);
		button.setOnMouseClicked(e -> action.run());
		return button;
	}

	private GameButton createExitButton() {
		GameButton exitButton = new GameButton("Exit");

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

	private AnchorPane createContainer(VBox contentBox, GameButton exitButton) {
		AnchorPane container = new AnchorPane();
		container.setPrefSize(500, 400);

		AnchorPane.setTopAnchor(contentBox, 200.0);
		AnchorPane.setLeftAnchor(contentBox, 275.0);
		container.getChildren().add(contentBox);

		AnchorPane.setTopAnchor(exitButton, 185.0);
		AnchorPane.setRightAnchor(exitButton, 280.0);
		container.getChildren().add(exitButton);

		return container;
	}

	private Background createBackgroundImage(String filename) {
		Image image = new Image(getImagePath(filename));
		BackgroundImage bg = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true));
		return new Background(bg);
	}

	private String getImagePath(String filename) {
		return ClassLoader.getSystemResource("Images/" + filename).toString();
	}

	private void toggleSound(ImageView soundBtn) {
		soundEnabled = !soundEnabled;
		soundBtn.setImage(new Image(getImagePath(soundEnabled ? "On_btn.png" : "Off_btn.png")));
		System.out.println("Sound " + (soundEnabled ? "enabled" : "disabled"));
	}

	private void toggleEffect(ImageView effectBtn) {
		effectsEnabled = !effectsEnabled;
		effectBtn.setImage(new Image(getImagePath(effectsEnabled ? "On_btn.png" : "Off_btn.png")));
		System.out.println("Effect " + (effectsEnabled ? "enabled" : "disabled"));
	}

	private void returnToStartPage() {
		StartPage startPage = new StartPage(primaryStage);
		primaryStage.setScene(startPage.getScene());
	}

	// Getters and setters
	public boolean isSoundEnabled() {
		return soundEnabled;
	}

	public void setSoundEnabled(boolean soundEnabled) {
		this.soundEnabled = soundEnabled;
	}

	public boolean isEffectsEnabled() {
		return effectsEnabled;
	}

	public void setEffectsEnabled(boolean effectsEnabled) {
		this.effectsEnabled = effectsEnabled;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
}

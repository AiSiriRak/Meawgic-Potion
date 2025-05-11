package gui.pane;

import Font.FontRect;
import application.StartPage;
import gui.button.ExitButtton;
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
    private boolean soundEnabled = true;
    private boolean effectsEnabled = true;

    public SettingPane(Stage primaryStage) {
        this.primaryStage = primaryStage;

        VBox contentBox = createContentBox();
        ExitButtton exitButton = createExitButton();
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

        HBox soundSetting = createToggleSetting("Sound", true);
        HBox effectSetting = createToggleSetting("Effect", false);
        VBox soundBox = new VBox(20, soundSetting, effectSetting);
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
        ImageView toggleBtn = new ImageView(new Image(ClassLoader.getSystemResource("Images/On_btn.png" ).toString()));
        toggleBtn.setFitWidth(64);
        toggleBtn.setPreserveRatio(true);
        toggleBtn.setSmooth(true);

        if ((isSound && !soundEnabled) || (!isSound && !effectsEnabled)) {
            toggleBtn.setImage(new Image(ClassLoader.getSystemResource("Images/Off_btn.png" ).toString()));
        }

        toggleBtn.setOnMouseClicked(e -> {
            if (isSound) {
                toggleSound(toggleBtn);
            } else {
                toggleEffect(toggleBtn);
            }
        });

        HBox settingBox = new HBox(20, text, toggleBtn);
        settingBox.setAlignment(Pos.CENTER);
        return settingBox;
    }

    private ImageView createIconButton(String imageName, Runnable action) {
        ImageView button = new ImageView(ClassLoader.getSystemResource("Images/"+imageName).toString());
        button.setFitWidth(96);
        button.setPreserveRatio(true);
        button.setSmooth(true);
        button.setOnMouseClicked(e -> action.run());
        return button;
    }

    private ExitButtton createExitButton() {
        ExitButtton exitButton = new ExitButtton();

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

    private AnchorPane createContainer(VBox contentBox, ExitButtton exitButton) {
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
        Image image = new Image(ClassLoader.getSystemResource("Images/"+filename ).toString());
        BackgroundImage bg = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true));
        return new Background(bg);
    }

    private void toggleSound(ImageView soundBtn) {
        System.out.println("toggleSound called. Current soundEnabled: " + soundEnabled);
        soundEnabled = !soundEnabled;
        System.out.println("toggleSound. New soundEnabled: " + soundEnabled);
        soundBtn.setImage(new Image(soundEnabled ? ClassLoader.getSystemResource("Images/On_btn.png" ).toString():ClassLoader.getSystemResource("Images/Off_btn.png" ).toString()));
        System.out.println("toggleSound. Calling SoundController.setMusicEnabled(" + soundEnabled + ")");
        SoundController.getInstance().setMusicEnabled(soundEnabled);
    }

    private void toggleEffect(ImageView effectBtn) {
        System.out.println("toggleEffect called. Current effectsEnabled: " + effectsEnabled);
        effectsEnabled = !effectsEnabled;
        System.out.println("toggleEffect. New effectsEnabled: " + effectsEnabled);
        effectBtn.setImage(new Image(effectsEnabled ? ClassLoader.getSystemResource("Images/On_btn.png" ).toString():ClassLoader.getSystemResource("Images/Off_btn.png" ).toString()));
        System.out.println("toggleEffect. Calling SoundController.setEffectsEnabled(" + effectsEnabled + ")");
        SoundController.getInstance().setEffectsEnabled(effectsEnabled);
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
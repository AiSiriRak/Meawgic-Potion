package logic.game;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardController {
	private boolean upPressed = false;
	private boolean downPressed = false;
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	private boolean EPressed = false;

	public KeyboardController() {
		keyboardSetup();
	}

	public void keyboardSetup() {
		GameController.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {

				if (e.getCode() == KeyCode.W) {
					upPressed = true;
				}
				if (e.getCode() == KeyCode.S) {
					downPressed = true;
				}
				if (e.getCode() == KeyCode.A) {
					leftPressed = true;
				}
				if (e.getCode() == KeyCode.D) {
					rightPressed = true;
				}
				if (e.getCode() == KeyCode.E) {
					EPressed = true;

				}
			}

		});

		GameController.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {

				if (e.getCode() == KeyCode.W) {
					upPressed = false;
				}
				if (e.getCode() == KeyCode.S) {
					downPressed = false;
				}
				if (e.getCode() == KeyCode.A) {
					leftPressed = false;
				}
				if (e.getCode() == KeyCode.D) {
					rightPressed = false;
				}
				if (e.getCode() == KeyCode.E) {
					EPressed = false;
				}
			}
		});
	}

	public boolean isUpPressed() {
		return upPressed;
	}

	public boolean isDownPressed() {
		return downPressed;
	}

	public boolean isLeftPressed() {
		return leftPressed;
	}

	public boolean isRightPressed() {
		return rightPressed;
	}

	public boolean isEPressed() {
		return EPressed;
	}

}

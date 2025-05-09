package logic.object;

import javafx.geometry.Rectangle2D;

public interface Interactable {
	Rectangle2D getInteractArea();

	void interact();

}

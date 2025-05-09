package gui.button;

public class ExitButtton extends GameButton {

	public ExitButtton() {
		super("Exit");
		// TODO Auto-generated constructor stub
		this.setOnMouseClicked(e -> this.setVisible(false));
	}

}

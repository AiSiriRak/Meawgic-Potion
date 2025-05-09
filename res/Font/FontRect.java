package Font;

import javafx.scene.text.Font;

public enum FontRect {
	REGULAR("MinecraftRegular-Bmg3.otf"), BOLD("MinecraftBold-nMK1.otf"), ITALIC("MinecraftItalic-R8Mo.otf"),
	BOLDITALIC("MinecraftBoldItalic-1y1e.otf");

	private final String fontPath;

	FontRect(String fontPath) {
		this.fontPath = fontPath;

	}

	public Font getFont(int fontSize) {
		return Font.loadFont(ClassLoader.getSystemResource("Font/" + this.fontPath).toString(), fontSize);

	}
}

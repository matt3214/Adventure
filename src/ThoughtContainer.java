import org.newdawn.slick.Color;

public class ThoughtContainer extends Container {

	private String message = "";

	public ThoughtContainer() {
		super(ContainerType.THOUGHTS);
		init();
	}

	private void init() {

	}

	public void draw() {
		GLib.translate(size.width / 9, size.height / 9, 0);
		GLib.drawRect(0, 0, 7 * size.width / 9, 7 * size.height / 9,
				Game.textureStore.getTexture("blank"));
		GLib.setColor(Color.black);
		GLib.drawRect(2, 2, 7 * size.width / 9 - 4, 7 * size.height / 9 - 4,
				Game.textureStore.getTexture("blank"));
		GLib.setColor(Color.white);
		GLib.drawRect(2, 2, 7 * size.width / 9 - 4, 7 * size.height / 9 - 4,
				Game.textureStore.getTexture("thoughtsBG"));
		GLib.setColor(Color.white);
		StringBuilder sb = new StringBuilder(message);
		int i = 0;
		while (i + 39 < sb.length() && (i = sb.lastIndexOf(" ", i + 39)) != -1) {
			sb.replace(i, i + 1, "\n");
		}
		GLib.drawString(new Vec2(3, 3), sb.toString(), Strings.DESCRIPTORTEXT);
	}

	public void printMessage(String message) {
		this.message = message;
	}

	public void update() {

	}

}

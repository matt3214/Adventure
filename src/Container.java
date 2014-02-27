import java.awt.Dimension;

import org.newdawn.slick.Color;

public class Container {

	protected ContainerType type;
	protected Vec2 position;
	protected Dimension size;
	protected Dimension screenSize = GLib.screenDimension();

	public Container(ContainerType type) {
		double x = 0;
		double y = 0;
		int width = 0;
		int height = 0;

		switch (type) {
		case INVENTORY:
			x = Strings.padding;
			y = 2 * screenSize.height / 3;
			width = ((screenSize.width / 3) - Strings.padding);
			height = (int) ((screenSize.height / 3) - Strings.padding);
			break;
		case ACTIONS:
			x = screenSize.width / 3 + Strings.padding;
			y = Strings.padding;
			width = ((screenSize.width / 3) - Strings.padding*2);
			height = (2*(screenSize.height / 3) - Strings.padding*2);
			break;
		case MAP:
			x = 2 * screenSize.width / 3;
			y = 2 * screenSize.height / 3;
			width = ((screenSize.width / 3) - Strings.padding);
			height = ((screenSize.height / 3) - Strings.padding);
			break;
		case OBSERVATIONS:
			x = Strings.padding;
			y = Strings.padding;
			width = ((screenSize.width / 3) - Strings.padding);
			height =  (2*(screenSize.height / 3) - Strings.padding*2);
			break;
		case THOUGHTS:
			break;
		default:
			break;
		}

		position = new Vec2(x, y);
		size = new Dimension(width, height);
		this.type = type;
	}

	public void paint() {
		GLib.push();
		GLib.translate((int) position.x, (int) position.y, 0);
		GLib.drawRect(0, 0, size.width, size.height,
				Game.textureStore.getTexture("containerBG"));

		GLib.setColor(Color.black);
		String text = type.text;
		GLib.drawString(new Vec2(Strings.padding, 0), text.substring(0, 1)
				.toUpperCase() + text.substring(1, text.length()) + ":", false);
		GLib.setColor(Color.white);

		draw();
		GLib.pop();

	}

	public void draw() {
	}

}

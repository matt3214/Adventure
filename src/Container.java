import java.awt.Dimension;

import org.newdawn.slick.Color;

public class Container {

	protected ContainerType type;
	protected Vec2 position;
	protected Dimension size;
	protected Dimension screenSize=GLib.screenDimension();
	public Container(ContainerType type) {
		double x = 0;
		double y = 0;
		int width = 0;
		int height = 0;
		

		switch (type) {
		case INVENTORY:
			x = Strings.padding;
			y = Strings.padding + 2*screenSize.height/3;
			width = 250;
			height = screenSize.height/3;
			break;
		}
		
		position = new Vec2(x, y);
		size = new Dimension(width,height);
		this.type = type;
	}

	public void paint() {
		GLib.push();
		GLib.translate((int) position.x, (int) position.y,0);
		GLib.setColor(Color.white);
		GLib.drawRect(0, 0, size.width, size.height,
				Game.textureStore.getTexture("containerBG"));
		String text = type.text;
		GLib.drawString(new Vec2(0, 0), text.substring(0,1).toUpperCase() + text.substring(1, text.length()) + ":");
		GLib.pop();

	}

}

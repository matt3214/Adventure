import org.newdawn.slick.Color;

public class StatusContainer extends Container {

	public StatusContainer() {
		super(ContainerType.STATUS);
	}

	public void update() {

	}

	protected void draw() {
		GLib.translate(size.width / 9, size.height / 8, 0);
		GLib.setColor(Color.black);

		GLib.drawString(new Vec2(0, -26), "Health: "+ Game.playerHealth, Strings.DESCRIPTORTEXT);
		GLib.setColor(Color.white);
		GLib.drawRect(0, 0, size.width / 6, size.width / 6,
				Game.textureStore.getTexture("health"));
		GLib.setColor(Color.red);
		GLib.drawRect(2 * size.width / 9, 0, 5 * size.width / 9,
				size.height / 8, Game.textureStore.getTexture("blank"));
		GLib.setColor(Color.black);
		GLib.drawRect(2 * size.width / 9 + 1, 1, 5 * size.width / 9 - 2,
				size.height / 8 - 2, Game.textureStore.getTexture("blank"));

		GLib.setColor(Color.red);
		GLib.drawRect(2 * size.width / 9 + 1, 1,
				Game.map(Game.playerHealth, 0, 100, 0, 5 * size.width / 9 - 2),
				size.height / 8 - 2, Game.textureStore.getTexture("blank"));

		GLib.setColor(Color.black);
		GLib.drawString(new Vec2(0, 174), "Hunger: " + Game.playerHunger, Strings.DESCRIPTORTEXT);

		GLib.setColor(Color.white);
		GLib.drawRect(0, 200, size.width / 6, size.width / 6,
				Game.textureStore.getTexture("hunger"));

		GLib.setColor(Color.blue);
		GLib.drawRect(2 * size.width / 9, 200, 5 * size.width / 9,
				size.height / 8, Game.textureStore.getTexture("blank"));
		GLib.setColor(Color.black);
		GLib.drawRect(2 * size.width / 9 + 1, 201, 5 * size.width / 9 - 2,
				size.height / 8 - 2, Game.textureStore.getTexture("blank"));

		GLib.setColor(Color.blue);
		GLib.drawRect(2 * size.width / 9 + 1, 201,
				Game.map(Game.playerHunger, 0, 100, 0, 5 * size.width / 9 - 2),
				size.height / 8 - 2, Game.textureStore.getTexture("blank"));

	}

}

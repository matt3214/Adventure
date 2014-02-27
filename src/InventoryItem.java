import org.newdawn.slick.opengl.Texture;

public class InventoryItem extends Observable {
	public Texture icon;

	public InventoryItem(String item) {
		super(item);
		icon = Game.textureStore.getTexture(item);

	}

	public void paint() {
		GLib.drawRect(0, 0, 16, 16, icon);
		GLib.drawString(new Vec2(0, 0), displayName, false);
	}

}

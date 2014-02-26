import org.newdawn.slick.opengl.Texture;


public class InventoryItem {
	public Texture icon;
	private String name;
	private String displayName;
	public String flavorText;
	
	public InventoryItem(String item){
		icon = Game.textureStore.getTexture(item);
		name = item;
		displayName = String.valueOf(item.charAt(0)).toUpperCase() + item.substring(1, item.length());
	}
	
	public void paint(){
		GLib.drawRect(0, 0, 16, 16, icon);
		GLib.drawString(new Vec2(0,0),displayName);
	}

	public String getName() {
		return displayName;
	}
}

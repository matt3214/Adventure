import java.awt.Dimension;
import java.util.Random;

import org.lwjgl.Sys;
import org.newdawn.slick.Color;

public class Game {
	private Dimension size;
	public static TextureStore textureStore;
	private static long last;
	public static Random rand = new Random();

	private Room[][] castle = new Room[10][6];

	InventoryContainer inventory;
	MapContainer map;

	public Game(Dimension size) {
		this.size = size;
		init();
	}

	private void init() {
		textureStore = new TextureStore();
		last = getTime();
		inventory = new InventoryContainer();
		initCastle();
		map = new MapContainer(castle);
	}

	private void initCastle() {
		
		int x = rand.nextInt(castle.length);
		int y = rand.nextInt(castle[0].length);
		
		for (int i = 0; i < castle.length; i++) {
			for (int j = 0; j < castle[0].length; j++) {
				castle[i][j] = new Room();
				if(i==x&&j==y){
					castle[i][j].discovered=true;
					castle[i][j].hasPlayer=true;
				}
			}
		}

		

	}

	public void update() {

		map.updateRooms(castle);

		last = getTime();

	}

	public void paint() {
		GLib.setColor(Color.black);
		GLib.fillScreen(null);
		GLib.setColor(Color.white);
		GLib.drawRect(0, 0, size.width, Strings.padding,
				textureStore.getTexture("borderHoriz"));
		GLib.drawRect(size.width - Strings.padding, 0, Strings.padding,
				size.height, textureStore.getTexture("borderVert"));
		GLib.drawRect(0, size.height - Strings.padding, size.width,
				Strings.padding, textureStore.getTexture("borderHoriz"));
		GLib.drawRect(0, 0, Strings.padding, size.height,
				textureStore.getTexture("borderVert"));

		inventory.paint();
		map.paint();
	}

	public static long getDelta() {
		long time = getTime();
		int delta = (int) (time - last);

		return delta;

	}

	public static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

}

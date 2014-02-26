import java.awt.Dimension;
import java.util.Random;

import org.lwjgl.Sys;
import org.newdawn.slick.Color;

public class Game {
	private Dimension size;
	public static TextureStore textureStore;
	private static long last;
	public static Random rand = new Random();

	private static Room[][] castle = new Room[10][6];
	private int playerX;
	private int playerY;

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

		playerX = rand.nextInt(castle.length);
		playerY = rand.nextInt(castle[0].length);
		System.out.println(playerX + " " + playerY);

		for (int i = 0; i < castle.length; i++) {
			for (int j = 0; j < castle[0].length; j++) {
				castle[i][j] = new Room();
				if (i == playerX && j == playerY) {
					castle[i][j].discovered = true;
					castle[i][j].hasPlayer = true;
				}
			}
		}

		for (int i = 0; i < 3; i++) {
			int x = rand.nextInt(castle.length);
			int y = rand.nextInt(castle[0].length);
			if (!castle[x][y].hasPlayer || castle[x][y].locked == true) {
				castle[x][y].locked = true;
				castle[x][y].golden = true;
			} else {
				i--;
			}
		}

	}

	public void update() {

		for (int i = 0; i < castle.length; i++) {
			for (int j = 0; j < castle[0].length; j++) {
				if (i == playerX && j == playerY) {
					castle[playerX][playerY].discovered = true;
					if (i != 0) {
						castle[playerX - 1][playerY].discovered = true;
					}
					if (i != castle.length - 1) {
						castle[playerX + 1][playerY].discovered = true;
					}
					if (j != 0) {
						castle[playerX][playerY - 1].discovered = true;
					}
					if (j != castle[0].length - 1) {
						castle[playerX][playerY + 1].discovered = true;
					}

					castle[playerX][playerY].hasPlayer = true;
				}
			}
		}

		map.updateRooms(castle);
		inventory.update();
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

	public static Room getRoom(int x, int y) {
		return castle[x][y];
	}

	private void playerMove() {

	}

	public static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

}

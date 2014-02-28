
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
	private static int playerX;
	private static int playerY;
	public static boolean debug = false;

	private static InventoryContainer inventory;
	private static MapContainer map;
	private static ActionContainer actions;
	private static ObservationsContainer observations;

	public Game(Dimension size) {
		this.size = size;
		init();
	}

	private void init() {
		textureStore = new TextureStore();
		last = getTime();
		inventory = new InventoryContainer();
		inventory.giveItem(new Note(Strings.storyLine));
		initCastle();
		map = new MapContainer(castle);
		actions = new ActionContainer();
		observations = new ObservationsContainer(castle[playerX][playerY]);
	}

	private void initCastle() {

		playerX = rand.nextInt(castle.length);
		playerY = rand.nextInt(castle[0].length);

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
		int x = rand.nextInt(castle.length);
		int y = rand.nextInt(castle[0].length);
		if (!castle[x][y].hasPlayer && castle[x][y].locked == false) {
			castle[x][y].escape = true;
			castle[x][y].crystalLocked = true;
		}
		
		for (int i = 0; i < castle.length; i++) {
			for (int j = 0; j < castle[0].length; j++) {
				castle[i][j].generateRoom(castle, i, j);
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
		observations.update(castle[playerX][playerY]);

		Observable item;

		if (inventory.selectedX != -1 && inventory.selectedY != -1) {
			item = inventory.getItem(inventory.selectedX, inventory.selectedY);
		} else {
			item = observations.getObservation(observations.selected);
		}

		actions.update(item);
		last = getTime();

	}

	public void paint() {
		GLib.push();
		GLib.translate(0, 0, -3);

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
		GLib.drawRect(size.width / 3, 0, Strings.padding, size.height,
				textureStore.getTexture("borderVert"));
		GLib.drawRect(2 * size.width / 3 - Strings.padding, 0, Strings.padding,
				size.height, textureStore.getTexture("borderVert"));
		GLib.drawRect(0, 2 * size.height / 3 - Strings.padding, size.width,
				Strings.padding, textureStore.getTexture("borderHoriz"));

		inventory.paint();
		map.paint();
		actions.paint();
		observations.paint();
		GLib.pop();
	}

	public static long getDelta() {
		long time = getTime();
		int delta = (int) (time - last);

		return delta;

	}

	public static Room getRoom(int x, int y) {
		return castle[x][y];
	}

	public static void movePlayer(int x, int y) {
		if(playerX<castle.length-1&&playerX>0){
			playerX += x;
		}
		if(playerY<castle[0].length-1&&playerY>0){
			playerY += y;
		}
		observations.selected = -1;
		inventory.selectedX = -1;
		inventory.selectedY = -1;
	}

	public static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

}

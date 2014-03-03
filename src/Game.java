import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

public class Game {
	private Dimension size;
	public static TextureStore textureStore;
	private static long last;
	public static Random rand = new Random();

	private static Room[][] castle = new Room[Strings.width][Strings.height];
	private static int playerX;
	private static int playerY;
	public static boolean debug = false;

	private static InventoryContainer inventory;
	private static MapContainer map;
	private static ActionContainer actions;
	private static ObservationsContainer observations;
	private static ThoughtContainer thoughts;
	private static StatusContainer status;

	public static int playerHealth = 100;
	public static int playerHunger = 100;

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
		thoughts = new ThoughtContainer();
		status = new StatusContainer();
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
			if (!castle[x][y].hasPlayer && castle[x][y].locked == false) {
				castle[x][y].locked = true;
				castle[x][y].golden = true;
			} else {
				i--;
			}
		}

		for (int i = 0; i < 1; i++) {

			int x = rand.nextInt(castle.length);
			int y = rand.nextInt(castle[0].length);
			if (castle[x][y].hasPlayer || castle[x][y].locked == true) {
				i--;
			} else {
				castle[x][y].escape = true;
				castle[x][y].crystalLocked = true;
			}
		}
		for (int i = 0; i < 3; i++) {
			int x = rand.nextInt(castle.length);
			int y = rand.nextInt(castle[0].length);
			if (!castle[x][y].hasPlayer && castle[x][y].locked == false
					&& castle[x][y].crystalLocked == false) {
				castle[x][y].hasKey = true;

			} else {
				i--;
			}
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
				} else {
					castle[i][j].hasPlayer = false;
				}
			}
		}
		Observable item;

		if (inventory.selectedX != -1 && inventory.selectedY != -1) {
			item = inventory.getItem(inventory.selectedX, inventory.selectedY);
		} else {
			item = observations.getObservation(observations.selected);
		}

		map.updateRooms(castle);
		actions.update(item);

		inventory.update();
		observations.update(castle[playerX][playerY]);

		thoughts.update();

		status.update();

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
		thoughts.paint();
		status.paint();

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

	public static Room getPlayerRoomPlus(int x, int y) {

		if (withinRange(playerX + x, castle.length - 1, 0)) {
			return castle[playerX + x][playerY];
		}
		if (withinRange(playerY + y, castle[0].length - 1, 0)) {
			return castle[playerX][playerY + y];
		}
		return null;
	}

	public static boolean withinRange(int value, int max, int min) {
		return (value >= min && value <= max);
	}

	public static void movePlayer(int x, int y) {
		if (x != 0) {

			if ((playerX == castle.length - 1 || x < 0)
					|| (playerX == 0 || x > 0)) {
				if (castle[playerX + x][playerY].isMovable()) {
					playerX += x;
				} else {
					printThought("You need a key to unlock that door!");
				}
			}
		}

		if (y != 0) {
			if ((playerY == castle[0].length - 1 || y < 0)
					|| (playerY == 0 || y > 0)) {
				if (castle[playerX][playerY + y].isMovable()) {
					playerY += y;
				} else {
					printThought("You need a key to unlock that door!");
				}
			}

		}

		observations.selected = -1;
		inventory.selectedX = -1;
		inventory.selectedY = -1;
		playerHunger -= 1;
		updateHealth();
	}

	public static int map(int x, int in_min, int in_max, int out_min,
			int out_max) {
		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}

	public static int constrain(int min, int max, int value) {
		if (value <= min) {
			value = min;
		} else if (value >= max) {
			value = max;
		}
		return value;
	}

	private static void updateHealth() {
		if (playerHunger >= 0 && playerHunger < 20) {
			playerHealth -= 5;
		} else if (playerHunger >= 20 && playerHunger < 40) {
			playerHealth -= 2;
		} else if (playerHunger >= 40 && playerHunger < 60) {
			playerHealth += 0;
		} else if (playerHunger >= 60 && playerHunger < 80) {
			playerHealth += 2;
		} else if (playerHunger >= 80 && playerHunger <= 100) {
			playerHealth += 5;
		}
		playerHealth = constrain(0, 100, playerHealth);

	}

	public static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public static void printThought(String actionText) {
		thoughts.printMessage(actionText);
	}

	public static void giveItem(InventoryItem itemToAdd) {
		inventory.giveItem(itemToAdd);
	}

	public static void removeItem(String item) {
		inventory.removeItem(item);
	}

	public static Room getPlayerRoom() {
		return castle[playerX][playerY];
	}

	public static void victory() {
		Display.destroy();
		JOptionPane.showMessageDialog(null,
				"Congratulations!!!\nYou've Won!!!!");
		System.exit(0);
	}

}

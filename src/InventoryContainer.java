import java.awt.Point;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class InventoryContainer extends Container {

	InventoryItem[][] items = new InventoryItem[3][3];
	InventoryButton[][] buttons = new InventoryButton[3][3];
	int itemWidth = 42;
	int itemHeight = 42;
	int padding = Strings.padding * 5;
	int selectedX = -1;
	int selectedY = -1;

	public InventoryContainer() {
		super(ContainerType.INVENTORY);
		init();
	}

	public void giveItem(InventoryItem item) {
		for (int i = 0; i < items[0].length; i++) {
			for (int j = 0; j < items.length; j++) {
				if (items[i][j] == null) {
					items[i][j] = item;
					return;
				}
			}
		}
	}

	private void init() {
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < items[0].length; j++) {
				items[i][j] = null;
				;
			}
		}

		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[0].length; j++) {
				buttons[i][j] = new InventoryButton(i * itemWidth + i * padding
						* 2.5, j * itemHeight + j * padding, itemWidth
						+ padding, itemHeight + padding);
			}
		}
	}

	Vec2 mousePos;

	public void update() {

		mousePos = Mouse.getPos().subtract(
				new Vec2(size.width / 9 + Strings.padding, 2
						* GLib.screenDimension().getHeight() / 3 + size.height
						/ 7 + Strings.padding));
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < items[0].length; j++) {

				if (buttons[i][j].hitbox.contains(new Point((int) mousePos.x,
						(int) mousePos.y))) {
					buttons[i][j].hovered = true;
					if (org.lwjgl.input.Mouse.isButtonDown(0)) {
						if (items[i][j] != null) {
							selectedX = i;
							selectedY = j;
						}

					}
				} else {
					buttons[i][j].hovered = false;

				}

			}

		}

	}

	private ArrayList<StringOnScreen> stringsToDraw = new ArrayList<StringOnScreen>();

	public void draw() {
		GLib.push();
		GLib.translate(size.width / 9, size.height / 7, 0);
		stringsToDraw.clear();
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < items[0].length; j++) {
				Texture buttonText = Game.textureStore.getTexture("unselected");
				InventoryButton button = buttons[i][j];
				if (button.hovered && items[i][j] != null) {
					buttonText = Game.textureStore.getTexture("hovered");
					stringsToDraw.add(new StringOnScreen(mousePos, items[i][j]
							.getName(), true));

				}
				if ((i == selectedX && j == selectedY) && items[i][j] != null) {
					buttonText = Game.textureStore.getTexture("selected");
				}

				GLib.drawRect(button.hitbox.x, button.hitbox.y,
						button.hitbox.width, button.hitbox.height, buttonText);
				if (items[i][j] != null) {

					GLib.drawRect(
							i * (itemWidth + padding * 2.5) + padding / 2, j
									* (itemHeight + padding) + padding / 2,
							itemWidth, itemHeight, items[i][j].icon);
				}

			}
		}
		drawStrings();

		GLib.pop();
	}

	private void drawStrings() {
		for (int i = 0; i < stringsToDraw.size(); i++) {
			stringsToDraw.get(i).draw();
		}
	}

	public Observable getItem(int x, int y) {
		if (x != -1 && y != -1) {
			return (items[x][y]);
		} else {
			return null;
		}

	}

	private class StringOnScreen {
		private Vec2 location;
		private String message;
		private boolean small;

		public StringOnScreen(Vec2 location, String message, boolean small) {
			this.location = location;
			this.message = message;
			this.small = small;
		}

		public void draw() {
			GLib.setColor(Color.black);
			GLib.drawString(location, message, small);
			GLib.setColor(Color.white);
		}
	}
}

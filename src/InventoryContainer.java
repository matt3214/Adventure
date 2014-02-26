import java.awt.Dimension;
import java.awt.Point;

import org.newdawn.slick.opengl.Texture;

public class InventoryContainer extends Container {

	InventoryItem[][] items = new InventoryItem[3][3];
	InventoryButton[][] buttons = new InventoryButton[3][3];
	int itemWidth = 32;
	int itemHeight = 32;
	int padding = Strings.padding * 5;

	public InventoryContainer() {
		super(ContainerType.INVENTORY);
		init();
	}

	private void init() {
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < items[0].length; j++) {
				items[i][j] = new Note(Strings.storyLine);
				;
			}
		}

		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[0].length; j++) {
				buttons[i][j] = new InventoryButton(
						i * itemWidth + i * padding, j * itemHeight + j
								* padding, itemWidth + padding, itemHeight
								+ padding);
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
					buttons[i][j].selected = true;
				} else {
					buttons[i][j].selected = false;
				}
			}
		}
	}

	public void draw() {
		GLib.push();
		GLib.translate(size.width / 9, size.height / 7, 0);

		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < items[0].length; j++) {
				Texture buttonText = Game.textureStore.getTexture("unselected");
				InventoryButton button = buttons[i][j];
				if (button.selected) {
					buttonText = Game.textureStore.getTexture("selected");
					if(items[i][j] instanceof Note){
						GLib.drawString(mousePos, ((Note)items[i][j]).message);
					}else{
						GLib.drawString(mousePos, items[i][j].getName());
					}
				}

				GLib.drawRect(button.hitbox.x, button.hitbox.y,
						button.hitbox.width, button.hitbox.height, buttonText);
				if (items[i][j] != null) {

					GLib.drawRect(i * (itemWidth + padding) + padding / 2, j
							* (itemHeight + padding) + padding / 2, itemWidth,
							itemHeight, items[i][j].icon);
				}

			}
		}
		GLib.pop();
	}
}

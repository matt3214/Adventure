import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class ActionContainer extends Container {

	public ActionContainer() {
		super(ContainerType.ACTIONS);
		init();
	}

	int padding = Strings.padding * 2;

	int buttonHeight = 32;
	int buttonWidth = 32;
	Action[] actions = new Action[10];
	InventoryButton[] buttons = new InventoryButton[10];

	private void init() {
		buttonWidth = size.width - Strings.padding * 2;
		for (int i = 0; i < actions.length; i++) {
			actions[i] = null;

		}

		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new InventoryButton(0, i * buttonHeight + i
					* Strings.padding, buttonWidth, buttonHeight
					+ Strings.padding);
		}
	}

	Vec2 mousePos;

	public void update(Observable object) {
		if (object != null) {
			Arrays.fill(actions, null);
			Action[] temp = object.actions.toArray(new Action[0]);
			for (int i = 0; i < temp.length; i++) {
				if (temp[i] != null) {
					actions[i] = temp[i];
				} else {
					actions[i] = null;
				}

			}
		}else{
			Arrays.fill(actions, null);
		}

		mousePos = Mouse.getPos().subtract(
				new Vec2(GLib.screenDimension().width / 3, size.height / 9));

		for (int i = 0; i < actions.length; i++) {

			if (buttons[i].hitbox.contains(new Point((int) mousePos.x,
					(int) mousePos.y))) {
				buttons[i].hovered = true;
				if (org.lwjgl.input.Mouse.isButtonDown(0)) {
					if (actions[i] != null) {
						actions[i].performAction();
					}

				}
			} else {
				buttons[i].hovered = false;

			}
		}
	}

	private ArrayList<StringOnScreen> stringsToDraw = new ArrayList<StringOnScreen>();

	public void draw() {
		GLib.push();
		GLib.translate(Strings.padding, size.height / 9, 0);
		stringsToDraw.clear();

		for (int i = 0; i < actions.length; i++) {
			Texture buttonText = Game.textureStore.getTexture("unselected");
			InventoryButton button = buttons[i];
			if (actions[i] != null) {
				stringsToDraw.add(new StringOnScreen(new Vec2(padding * 5, i
						* (buttonHeight + padding) + padding), actions[i]
						.actionText));

			}
			if (button.hovered) {
				buttonText = Game.textureStore.getTexture("hovered");
			}
			

			GLib.drawRect(button.hitbox.x, button.hitbox.y,
					button.hitbox.width, button.hitbox.height, buttonText);

		}
		drawStrings();

		GLib.pop();
	}

	private void drawStrings() {
		for (int i = 0; i < stringsToDraw.size(); i++) {
			stringsToDraw.get(i).draw();
		}
	}

	private class StringOnScreen {
		private Vec2 location;
		private String message;

		public StringOnScreen(Vec2 location, String message) {
			this.location = location;
			this.message = message;
		}

		public void draw() {
			GLib.setColor(Color.black);
			GLib.drawString(location, message, 2);
			GLib.setColor(Color.white);
		}
	}
}

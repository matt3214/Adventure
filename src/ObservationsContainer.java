import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class ObservationsContainer extends Container {

	private Room playerRoom = null;
	public int selected = -1;
	InventoryButton[] buttons = new InventoryButton[10];
	Observation[] observations = new Observation[10];
	int padding = Strings.padding * 2;
	int buttonHeight = 32;
	int buttonWidth = 32;

	public ObservationsContainer(Room playerRoom) {
		super(ContainerType.OBSERVATIONS);
		this.playerRoom = playerRoom;
		init();
	}

	private void init() {
		buttonWidth = size.width - Strings.padding * 2;
		for (int i = 0; i < observations.length; i++) {
			observations[i] = null;

		}

		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new InventoryButton(0, i * buttonHeight + i * padding,
					buttonWidth, buttonHeight + padding);
		}
	}

	Vec2 mousePos;

	public void update(Room newRoom) {
		playerRoom = newRoom;
		Arrays.fill(observations, null);
		Observation[] temp = playerRoom.observations
				.toArray(new Observation[0]);
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] != null) {
				observations[i] = temp[i];
			} else {
				observations[i] = null;
			}

		}
		mousePos = Mouse.getPos().subtract(
				new Vec2(Strings.padding, size.height / 9));

		if (org.lwjgl.input.Mouse.isButtonDown(0)
				&& (mousePos.y > 8 * size.height / 9)) {
			selected = -1;
		}

		for (int i = 0; i < observations.length; i++) {

			if (buttons[i].hitbox.contains(new Point((int) mousePos.x,
					(int) mousePos.y))) {
				buttons[i].hovered = true;
				if (org.lwjgl.input.Mouse.isButtonDown(0)) {
					if (observations[i] != null) {
						selected = i;
					}

				}
			} else {
				buttons[i].hovered = false;

			}

		}

	}

	public void setRoom(Room newRoom) {
		playerRoom = newRoom;
	}

	public Observable getObservation(int selected) {
		if (selected != -1) {
			return playerRoom.observations.get(0);
		} else {
			return null;
		}
	}

	private ArrayList<StringOnScreen> stringsToDraw = new ArrayList<StringOnScreen>();

	public void draw() {
		GLib.push();
		GLib.translate(Strings.padding, size.height / 9, 0);
		stringsToDraw.clear();

		for (int i = 0; i < observations.length; i++) {
			Texture buttonText = Game.textureStore.getTexture("unselected");
			InventoryButton button = buttons[i];
			if (observations[i] != null) {
				stringsToDraw.add(new StringOnScreen(new Vec2(padding * 5, i
						* (buttonHeight + padding) + padding), observations[i]
						.getName()));

			}
			if (button.hovered) {
				buttonText = Game.textureStore.getTexture("hovered");

			}
			if ((i == selected) && observations[i] != null) {
				buttonText = Game.textureStore.getTexture("selected");
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

import java.awt.Dimension;

import org.newdawn.slick.opengl.Texture;

public class MapContainer extends Container {

	public Room[][] castle;
	Dimension roomSize = new Dimension(33, 33);

	public MapContainer(Room[][] castle) {
		super(ContainerType.MAP);

		this.castle = castle;

	}

	public void updateRooms(Room[][] castle) {
		this.castle = castle;
	}

	public void draw() {
		GLib.push();
		GLib.translate(size.width / 10, size.height / 8, 0);
		Texture roomTexture = null;
		Texture iconTexture = null;
		for (int i = 0; i < castle.length; i++) {
			for (int j = 0; j < castle[0].length; j++) {
				roomTexture = null;
				iconTexture = null;

				Room room = castle[i][j];
				if (!room.discovered) {
					roomTexture = Game.textureStore.getTexture("undiscovered");
				} else if (room.discovered) {
					roomTexture = Game.textureStore.getTexture("empty");
					if (room.isLocked) {
						roomTexture = Game.textureStore.getTexture("locked");
					}
					//Icon checking
					if (room.hasPlayer) {
						iconTexture = Game.textureStore.getTexture("player");
					}

				}

				GLib.drawRect(i * roomSize.width, j * roomSize.height,
						roomSize.width, roomSize.height, roomTexture);
				if (iconTexture != null) {
					GLib.drawRect(i * roomSize.width, j * roomSize.height,
							roomSize.width, roomSize.height, iconTexture);
				}
			}
		}
		GLib.pop();

	}
}

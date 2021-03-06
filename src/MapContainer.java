import java.awt.Dimension;

import org.newdawn.slick.opengl.Texture;

public class MapContainer extends Container {

	public Room[][] castle;
	Dimension roomSize;

	public MapContainer(Room[][] castle) {
		super(ContainerType.MAP);

		this.castle = castle;
		roomSize =  new Dimension((4*size.width/5)/Strings.width ,(3*size.height/4)/Strings.height);
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
				if (!room.discovered&&!Game.debug) {
					roomTexture = Game.textureStore.getTexture("undiscovered");
				} else {
					roomTexture = Game.textureStore.getTexture("empty");
					if (room.golden) {
						roomTexture = Game.textureStore.getTexture("gold");
					}
					if (room.escape) {
						roomTexture = Game.textureStore.getTexture("final");
					}
					// Icon checking
					if (room.hasPlayer) {
						iconTexture = Game.textureStore.getTexture("player");
					}
					if (room.locked){
						iconTexture = Game.textureStore.getTexture("locked");
					}
					if (room.crystalLocked) {
						iconTexture = Game.textureStore.getTexture("crystalLocked");
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

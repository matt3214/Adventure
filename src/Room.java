import java.util.ArrayList;

public class Room {
	public boolean hasKey = false;
	public boolean locked = false;
	public boolean golden = false;
	public boolean escape = false;
	public boolean hasPlayer = false;
	public boolean discovered = false;
	public boolean crystalLocked = false;

	public ArrayList<Observation> observations = new ArrayList<Observation>();
	public Door[] doors = new Door[4];

	public Room() {

	}

	public void generateRoom(Room[][] castle, int x, int y) {
		for (int i = 0; i < doors.length; i++) {
			doors[i] = new Door(i);
		}

		if (x > 0) {
			observations.add(doors[3]);
		}
		if (x < castle.length - 1) {
			observations.add(doors[1]);
		}
		if (y > 0) {
			observations.add(doors[0]);
		}
		if (y < castle[0].length - 1) {
			observations.add(doors[2]);
		}
		
		if(locked){
			observations.add(new CrystalKey());
		}
		if(escape){
			observations.add(new Escape());
		}
		if (hasKey) {
			observations.add(new HiddenObject(new ItemKey()));
		}
		if(Game.rand.nextBoolean() && Game.rand.nextBoolean() && Game.rand.nextBoolean()){
			observations.add(new HiddenObject(new ItemFood()));
		}

	}

	public boolean isMovable() {
		if (locked || crystalLocked) {
			return false;
		}
		return true;
	}

	public void removeItem(String item) {
		for (int i = 0; i < observations.size(); i++) {
			if (observations.get(i).name.equals(item)) {
				observations.remove(i);
				return;
			}
		}
	}

}

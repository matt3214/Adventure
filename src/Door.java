public class Door extends Observation {

	public int direction;

	public Door(int direction) {
		super((direction == 0 ? "north" : direction == 1 ? "east"
				: direction == 2 ? "south" : "west") + " Door");
		this.direction = direction;
		init();
	}

	private void init() {
		actions.add(new MoveAction(direction));

	}

}

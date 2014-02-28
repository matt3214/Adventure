
public class Door extends Observation {

	private int direction;

	public Door(int direction) {
		super((direction == 0 ? "north" : direction == 1 ? "east"
				: direction == 2 ? "south" : "west") + " Door");
		this.direction = direction;
		init();
	}

	private void init() {
		
		
		actions.add(new MoveAction() {

			public void performAction() {
				int[] xs = { 0, 1, 0, -1 };
				int[] ys = { 1, 0, -1, 0 };
				Game.movePlayer(xs[direction], ys[direction]);
				
			}

		});

	}

}

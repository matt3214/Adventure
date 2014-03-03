
public class Key extends Observation {

	public Key() {
		super("key");
		init();
	}
	private void init(){
		actions.add(new InventoryAction(new ItemKey()));
	}

}

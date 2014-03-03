public class ItemKey extends InventoryItem {

	public ItemKey() {
		super("key");
		init();

	}

	private void init() {
		for (int i = 0; i < 4; i++) {
			actions.add(new UnlockAction(i,true));
		}
	}
	

}

public class Note extends InventoryItem {
	public String message = "";

	public Note(String note) {
		super("note");
		this.message = note;
		init();
	}

	private void init() {
		actions.add(new ReadAction(message));
	}
	
}


public class Note extends InventoryItem {
	public String message = "";
	public Note(String note){
		super("note");
		this.message = note;
	}
}

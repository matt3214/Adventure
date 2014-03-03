
public class ItemFood extends InventoryItem{
	
	public ItemFood() {
		super("food");
		this.name = "food";
		init();
	}
	
	
	private void init(){
		actions.add(new EatAction());
	}
	
}

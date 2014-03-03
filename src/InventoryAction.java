
public class InventoryAction extends Action {
	private InventoryItem itemToAdd;
	protected String actionText;
	
	public InventoryAction(InventoryItem itemToAdd){
		this.itemToAdd = itemToAdd;
		actionText = "Pick up " + itemToAdd.displayName;
	}
	
	@Override
	public void performAction() {
		super.performAction();
		Game.giveItem(itemToAdd);
		Game.getPlayerRoom().removeItem(itemToAdd.name);
	}
	
	@Override
	protected String getActionText() {
		return actionText;
	}
	
	
	
}

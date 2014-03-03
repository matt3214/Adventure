
public class ItemCrystalKey extends InventoryItem {

	public ItemCrystalKey() {
		super("crystal Key");
		init();
	}
	
	private void init(){
		for(int i=0; i<4;i++){
			actions.add(new UnlockAction(i,false));
		}
	}

}

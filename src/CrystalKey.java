
public class CrystalKey extends Observation {

	public CrystalKey() {
		super("crystal Key");
		init();
	}
	private void init(){
		actions.add(new InventoryAction(new ItemCrystalKey()));
	}
	
}

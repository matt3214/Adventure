public class InspectAction extends InventoryAction {

	protected String actiontext = "Inspect";
	private String inspectText = "";
	private String observationName;

	public InspectAction(String inspectText, String observationName, InventoryItem itemToFind) {
		super(itemToFind);
		this.inspectText = inspectText;
		this.observationName = observationName;
	}

	@Override
	public void performAction() {
		super.performAction();
		Game.printThought(inspectText);
		Game.getPlayerRoom().removeItem(observationName);

	}

	@Override
	protected String getActionText() {
		return actionText;
	}

}

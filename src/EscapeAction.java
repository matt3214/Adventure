public class EscapeAction extends Action {
	
	protected String actionText = "Escape!!!";
	
	@Override
	public void performAction() {
		super.performAction();
		Game.victory();
	}
	
	@Override
	protected String getActionText() {
		return actionText;
	}
}

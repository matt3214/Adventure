
public class Action implements IAction{
	protected String actionText;

	public void performAction() {
		Game.movePlayer(0,0);
	}
	protected String getActionText(){
		return actionText;
	}
	
	
}

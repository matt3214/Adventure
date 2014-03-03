
public class ReadAction extends Action implements IAction {
	protected String actionText ="Read Note";
	public String message;
	public ReadAction(String text){
		message = text;
	}
	
	public void performAction(){
		super.performAction();
		Game.printThought(message);
	}
	
	protected String getActionText(){
		return actionText;
	}
}


public class Action implements IAction{
	public String actionText = "";
	private ActionType actionType;
	
	public Action(ActionType type){
		this.actionType = type;
	}
	public Action(ActionType type,String text){
		this.actionType = type;
		actionText = text;
	}
	
	
	public void performAction(Object... params) {
		switch(actionType){
		case MOVE:
			int x = (int) params[0];
			int y = (int) params[1];
			Game.movePlayer(x,y);
			break;
		}
	}
	
	
}

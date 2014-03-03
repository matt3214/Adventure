
public class MoveAction extends Action {
	
	protected String actionText = "Open door";
	public  int direction;
	
	public MoveAction(int direction){
		this.direction = direction;
	}
	
	public void performAction(){
		super.performAction();
		int[] x = {0,1,0,-1};
		int[] y = {-1,0,1,0};
		Game.movePlayer(x[direction], y[direction]);
	}
	@Override
	protected String getActionText(){
		return actionText;
	}
}


public class EatAction extends Action {
	protected String actionText = "Eat the food";
	
	@Override
	public void performAction() {
		super.performAction();
		Game.playerHunger += 25;
		Game.playerHunger = Game.constrain(0, 100, Game.playerHunger);
		Game.removeItem("food");
	}
	
	
	@Override
	protected String getActionText() {
		return actionText;
	}
	
}

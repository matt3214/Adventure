public class UnlockAction extends Action {
	private int side = 0;
	private boolean golden = false;

	public UnlockAction(int side, boolean golden) {
		this.side = side;
		this.golden = golden;
		this.actionText = "Unlock "
				+ (side == 0 ? "North" : side == 1 ? "East"
						: side == 2 ? "South" : "West") + " Door";
	}

	@Override
	public void performAction() {
		super.performAction();
		int[] x = { 0, 1, 0, -1 };
		int[] y = { -1, 0, 1, 0 };
		if (Game.getPlayerRoomPlus(x[side], y[side]) != null) {
			if (golden) {
				if (Game.getPlayerRoomPlus(x[side], y[side]).locked) {
					Game.getPlayerRoomPlus(x[side], y[side]).locked = false;
					Game.removeItem("key");

				} else {
					Game.printThought("That door isn't locked");
				}
			} else {
				if (Game.getPlayerRoomPlus(x[side], y[side]).crystalLocked) {
					Game.getPlayerRoomPlus(x[side], y[side]).crystalLocked = false;
					Game.removeItem("crystal Key");

				} else {
					Game.printThought("That door isn't crystal locked");
				}
			}
		} else {
			Game.printThought("There is no door there");
		}
	}

	@Override
	protected String getActionText() {
		return this.actionText;
	}

}

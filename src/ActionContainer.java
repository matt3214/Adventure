public class ActionContainer extends Container {

	public ActionContainer() {
		super(ContainerType.ACTIONS);
	}

	Action[] actions = new Action[10];

	public void update(Observable object) {
		if (object != null) {
			if (object.actions.size() <= actions.length) {
				for (int i = 0; i < object.actions.size(); i++) {
					if (actions[i] == null) {
						actions[i] = object.actions.get(0);
					}
				}
			} else {
				System.out.println("Ruh Roh");
			}
		}
	}

	public void draw() {
		for (int i = 0; i < actions.length; i++) {
			if(actions[i]!=null){
				System.out.println(actions[i].actionText);
				GLib.drawString(new Vec2(0,i*30), actions[i].actionText, true);
			}
		}
	}
}

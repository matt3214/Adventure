public class Escape extends Observation {

	public Escape() {
		super("escape");
		init();
	}
	
	private void init(){
		actions.add(new EscapeAction());
	}

}

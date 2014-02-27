import java.util.ArrayList;

public class Observable {
	public String name;
	public String displayName;
	protected String flavorText = "";
	protected ArrayList<Action> actions = new ArrayList<Action>();

	public Observable(String name) {
		this.name = name;
		this.displayName = (String.valueOf(name.charAt(0)).toUpperCase() + name
				.substring(1, name.length()));
	}

	public String getName() {
		return displayName;
	}

}

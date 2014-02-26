import java.util.ArrayList;
import java.util.Random;


public class Observation {

	public String name;
	public String displayName;
	protected String flavorText = "";
	
	protected ArrayList<Action> actions = new ArrayList<Action>();
	
	public Observation(String name){
		this.name = name;
		this.displayName = String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1,name.length());
		flavorText = Strings.observation1[Game.rand.nextInt(Strings.observation1.length)] + displayName +" "+ Strings.observation2[Game.rand.nextInt(Strings.observation2.length)];
	}
	
	

	
}

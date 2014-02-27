import java.util.ArrayList;
import java.util.Random;


public class Observation extends Observable{

	protected String flavorText = "";
	
	
	public Observation(String name){
		super(name);
		flavorText = Strings.observation1[Game.rand.nextInt(Strings.observation1.length)] + displayName +" "+ Strings.observation2[Game.rand.nextInt(Strings.observation2.length)];
	}
	
	

	
}

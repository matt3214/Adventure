import java.util.Random;


public class Item {

	public String name;
	public String displayName;
	private String flavorText = "";
	
	public Item(String name){
		this.name = name;
		this.displayName = String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1,name.length());
		flavorText = Strings.observation1[Game.rand.nextInt(Strings.observation1.length)] + displayName +" "+ Strings.observation2[Game.rand.nextInt(Strings.observation2.length)];
		System.out.println(flavorText);
	}
	
	

	
}

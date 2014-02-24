
public class Container {
	public static enum ContainerType{
		INVENTORY,OBSERVATIONS,ACTIONS,MAP,THOUGHTS
	}
	private ContainerType type;
	public Container(ContainerType type){
		this.type = type;
	}
	
}

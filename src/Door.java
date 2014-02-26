
public class Door extends Observation {
	
	
	public boolean locked = false;
	
	public Door(int direction) {
		super((direction==0?"north":direction==1?"east":direction==2?"south":"west") + " Door");
		System.out.println(displayName);
	}

}

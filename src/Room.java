import java.util.ArrayList;

public class Room {
	public boolean hasKey = false;
	public boolean locked = false;
	public boolean golden = false;
	public boolean escape = false;
	public boolean hasPlayer = false;
	public boolean discovered = false;

	public int numberOfObservations = 0;
	public ArrayList<Observation> observations = new ArrayList<Observation>();
	public Door[] doors = new Door[4];
	
	
	public Room() {
		
	}
	

}

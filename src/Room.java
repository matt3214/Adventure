import java.util.ArrayList;

public class Room {
	public boolean hasKey = false;
	public boolean isLocked = false;
	public boolean hasPlayer = false;
	public boolean discovered = false;

	public int numberOfObservations = 0;
	public ArrayList<Item> items = new ArrayList<Item>();

	public Room() {
		
	}
	

}

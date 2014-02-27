public class ObservationsContainer extends Container {

	private Room playerRoom = null;
	public int selected = -1;

	public ObservationsContainer(Room playerRoom) {
		super(ContainerType.OBSERVATIONS);
		this.playerRoom = playerRoom;
	}

	public void update() {
		
	}
	
	public void setRoom(Room newRoom){
		playerRoom = newRoom;
	}
	
	public Observable getObservation(int selected){
		if(selected!=-1){
			return playerRoom.observations.get(0);
		}else{
			return null;
		}
	}

	public void draw() {

	}

}

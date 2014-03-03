import org.newdawn.slick.opengl.Texture;

public enum ContainerType{
		INVENTORY("inventory"),OBSERVATIONS("observations"),ACTIONS("actions"),MAP("map"),THOUGHTS("thoughts"), STATUS("status");
		public String text;
		
		ContainerType(String text){
			this.text = text;
		}
		
}
import java.awt.Rectangle;


public class InventoryButton {
	public boolean hovered = false;
	public Rectangle hitbox;
	
	
	public InventoryButton(double x,double y, int width, int height){
		hitbox = new Rectangle((int)x,(int)y,width,height);
	}
	
	
}

import java.awt.Dimension;


public class Container {
	
	protected ContainerType type;
	protected Vec2 position;
	protected Dimension size;
	
	
	public Container(ContainerType type){
		double x = 0;
		double y = 0;
		
		switch(type){
		case INVENTORY:
			
			break;
		}
		
		position = new Vec2(x,y);
		this.type = type;
	}
	
	public void paint(){
		GraphicsLib.pop();
		GraphicsLib.translate((int)position.x, 0, (int)position.y);
		GraphicsLib.drawRect(0, 0, Strings.textWidth, Strings.textHeight,Game.textureStore.getTexture("text_inventory"));
		GraphicsLib.push();
	}
	
}

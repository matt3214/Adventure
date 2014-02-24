import java.awt.Dimension;

import org.lwjgl.Sys;
import org.newdawn.slick.Color;

public class Game {
	private Dimension size;
	public static TextureStore textureStore;
	private static long last;
	Container testCont;

	public Game(Dimension size) {
		this.size = size;
		init();
	}

	private void init() {
		textureStore = new TextureStore();
		last = getTime();
		testCont = new Container(ContainerType.INVENTORY);
	}

	public void update() {
		System.out.println("updated in " + getDelta());
		last = getTime();

	}

	public void paint() {
		GraphicsLib.setColor(Color.black);
		GraphicsLib.fillScreen(null);
		GraphicsLib.setColor(Color.red);
		GraphicsLib.drawString(Mouse.getPos(), "Mouse Position:" + Mouse.getX()
				+ ", " + Mouse.getY());
		testCont.paint();
	}

	public static long getDelta() {
		long time = getTime();
		int delta = (int) (time - last);

		return delta;

	}

	public static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

}

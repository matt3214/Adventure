import java.awt.Color;
import java.awt.Dimension;

import org.lwjgl.Sys;

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
		last = getTime();
	}

	public void paint() {
		GraphicsLib.clear();
		GraphicsLib.setColor(Color.white);
		GraphicsLib.fillScreen(null);
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

import java.awt.Dimension;

import org.lwjgl.Sys;

public class Game {
	private Dimension size;
	public static TextureStore textureStore;
	private static long last;
	public static Vec2 gravity = new Vec2(0,-9.8);

	public Game(Dimension size) {
		this.size = size;
		init();
	}

	private void init() {
		textureStore = new TextureStore();
		last = getTime();
	}

	public void update() {
		last = getTime();
	}

	public void paint() {
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

import java.awt.Dimension;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;

public class Window {
	Dimension size = new Dimension(1200, 780);
	Texture test;
	Game game;

	public Window() throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(size.width,size.height));
		Display.setTitle("Platformer");
//		Display.setFullscreen(true);
		Display.create();
		init();
		run();
	}

	private void init() {
		GraphicsLib.init(size);
		game = new Game(size);
	}

	private void run() {
		while (!Display.isCloseRequested()
				&& !Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			update();
			paint();

			Display.update();
			Display.sync(60);

		}
		Display.destroy();
		System.exit(0);
	}

	private void update() {
		game.update();
	}

	private void paint() {
		GraphicsLib.clear();
		game.paint();
	}

	public static void main(String[] args) throws LWJGLException {
		new Window();
	}
}

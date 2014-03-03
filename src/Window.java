import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.ImageIOImageData;
import org.newdawn.slick.opengl.Texture;

public class Window {
	Dimension size = new Dimension(1200, 780);
	Texture test;
	Game game;

	public Window() throws LWJGLException, IOException {
		Display.setDisplayMode(new DisplayMode(size.width, size.height));
		Display.setTitle("Castle Crawler");
		Display.setIcon(new ByteBuffer[] {
				new ImageIOImageData().imageToByteBuffer(
						ImageIO.read(new File("res/textures/icon/small.png")),
						false, false, null),
				new ImageIOImageData().imageToByteBuffer(
						ImageIO.read(new File("res/textures/icon/large.png")),
						false, false, null) });
		// Display.setFullscreen(true);'
		Display.create();
		init();
		run();
	}

	private IntBuffer loadCursor(String input) throws IOException {
		BufferedImage img = ImageIO.read(new File(input));
		int[] rgbs = new int[img.getWidth() * img.getHeight()];
		return IntBuffer.wrap(img.getRGB(0, 0, img.getWidth(), img.getHeight(),
				rgbs, 0, img.getWidth()));
	}

	private void init() throws LWJGLException, IOException {

		GLib.init(size);
		// Cursor c = new Cursor(16, 16, 0, 15, 1,
		// loadCursor("res/mouse/cursor.png"),
		// IntBuffer.wrap(new int[] { 0 }));
		//
		// Mouse.setNativeCursor(c);
		// Mouse.updateCursor();

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
		GLib.clear();
		game.paint();
	}

	public static void main(String[] args) throws LWJGLException, IOException {
		new Window();
	}
}

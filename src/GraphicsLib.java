import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
import java.awt.Dimension;

import org.newdawn.slick.opengl.Texture;

public class GraphicsLib {
	private static Dimension size;

	public static void init(Dimension size_) {
		glMatrixMode(GL_PROJECTION_MATRIX);
		glOrtho(0, size_.width, 0, size_.height, 1, -1);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glMatrixMode(GL_MODELVIEW_MATRIX);
		size = size_;
	}

	public static void clear() {
		glClear(GL_COLOR_BUFFER_BIT);
	}

	public static void setColor(Color c) {
		glColor4d(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
	}

	public static void fillScreen() {
		glBegin(GL_QUADS);
		glVertex2d(0, 0);
		glVertex2d(0 + size.width, 0);
		glVertex2d(0 + size.width, 0 + size.height);
		glVertex2d(0, 0 + size.height);
		glEnd();
	}

	public static void fillScreen(Texture texture) {
		if(texture == null){
			texture = Game.textureStore.getTexture(0);
		}
		texture.bind();
		glBegin(GL_QUADS);
		glTexCoord2d(0, 1);
		glVertex2d(0, 0);

		glTexCoord2d(1, 1);
		glVertex2d(0 + size.width, 0);

		glTexCoord2d(1, 0);
		glVertex2d(0 + size.width, 0 + size.height);

		glTexCoord2d(0, 0);
		glVertex2d(0, 0 + size.height);
		glEnd();
	}

	public static void drawRect(int x, int y, int w, int h) {
		glBegin(GL_QUADS);
		glVertex2d(x, y);
		glVertex2d(x + w, y);
		glVertex2d(x + w, y + h);
		glVertex2d(x, y + h);
		glEnd();

	}

	public static void drawRect(double x, double y, int w, int h) {
		glBegin(GL_QUADS);
		glVertex2d(x, y);
		glVertex2d(x + w, y);
		glVertex2d(x + w, y + h);
		glVertex2d(x, y + h);
		glEnd();

	}

	public static void drawRect(double x, double y, int w, int h, Texture texture) {
		texture.bind();
		glBegin(GL_QUADS);
		glTexCoord2d(0, 1);
		glVertex2d(x, y);

		glTexCoord2d(1, 1);
		glVertex2d(x + w, y);

		glTexCoord2d(1, 0);
		glVertex2d(x + w, y + h);

		glTexCoord2d(0, 0);
		glVertex2d(x, y + h);
		glEnd();
	}

	public static void push() {
		glPushMatrix();
	}
	
	public static void pop(){
		glPopMatrix();
	}

	public static void translate(double x, int y, int z) {
		glTranslated(x, y, z);
	}

	public static void translateView(double x, int y, int z) {
		glTranslated(-x, y, z);
	}
}

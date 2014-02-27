import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW_MATRIX;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION_MATRIX;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor4d;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.awt.Dimension;
import java.awt.Font;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.opengl.Texture;

public class GLib {
	private static Dimension size;
	private static Color color;
	private static UnicodeFont font;
	private static UnicodeFont hoverFont;

	@SuppressWarnings("unchecked")
	public static void init(Dimension size_) {
		glMatrixMode(GL_PROJECTION_MATRIX);
		glOrtho(0, size_.width, size_.height, 0, 5, -5);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glMatrixMode(GL_MODELVIEW_MATRIX);
		size = size_;
		color = Color.black;
		font = new UnicodeFont(new Font("Times New Roman", Font.BOLD, 24));
		font.getEffects().add(new ColorEffect(java.awt.Color.white));
		font.addAsciiGlyphs();
		hoverFont = new UnicodeFont(new Font("Times New Roman", Font.BOLD, 16));
		hoverFont.getEffects().add(new ColorEffect(java.awt.Color.white));
		hoverFont.addAsciiGlyphs();
		try {
			hoverFont.loadGlyphs();
			font.loadGlyphs();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public static void switchText() {

	}

	public static void clear() {
		glClear(GL_COLOR_BUFFER_BIT);
	}

	public static void setColor(Color c) {
		color = c;
		glColor4d(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
	}

	public static Color getColor() {
		return color;
	}

	public static void fillScreen() {
		drawRect(0, 0, size.width, size.height);
	}

	public static void fillScreen(Texture texture) {
		if (texture == null) {
			texture = Game.textureStore.getTexture(0);
		}

		drawRect(0, 0, size.width, size.height, texture);

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

	public static void drawRect(double x, double y, int w, int h,
			Texture texture) {
		texture.bind();
		glBegin(GL_QUADS);
		glTexCoord2d(0, 0);
		glVertex2d(x, y);

		glTexCoord2d(1, 0);
		glVertex2d(x + w, y);

		glTexCoord2d(1, 1);
		glVertex2d(x + w, y + h);

		glTexCoord2d(0, 1);
		glVertex2d(x, y + h);
		glEnd();
	}

	public static void push() {
		glPushMatrix();
	}

	public static void pop() {
		glPopMatrix();
	}

	public static void translate(double x, int y, int z) {
		glTranslated(x, y, z);
	}

	public static void drawString(Vec2 position, String text, boolean small) {
		glPushMatrix();
		glDisable(GL_LIGHTING);
		
		if (!small) {
			font.drawString((float) position.x, (float) position.y, text, color);
		} else {
			position = position.add(new Vec2(2,-13));
			Color cache = getColor();
			setColor(Color.white);
			drawRect(position.x, position.y, hoverFont.getWidth(text),
					hoverFont.getHeight(text),Game.textureStore.getTexture("blank"));
			hoverFont.drawString((float) position.x, (float) position.y, text,
					cache);
			setColor(cache);
		}
		glPopMatrix();
	}

	public static Dimension screenDimension() {
		return size;
	}

}

import static org.lwjgl.opengl.GL11.*;

import java.awt.Dimension;
import java.awt.Font;

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
	private static UnicodeFont descriptorFont;

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
		descriptorFont = new UnicodeFont(new Font("Times New Roman", Font.BOLD,
				18));
		descriptorFont.getEffects().add(new ColorEffect(java.awt.Color.white));
		descriptorFont.addAsciiGlyphs();
		try {
			hoverFont.loadGlyphs();
			font.loadGlyphs();
			descriptorFont.loadGlyphs();
		} catch (SlickException e) {
			e.printStackTrace();
		}
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

	public static void drawString(Vec2 position, String text, int textType) {
		glPushMatrix();
		glDisable(GL_LIGHTING);

		if (textType == Strings.CONTAINERTEXT) {
			font.drawString((float) position.x, (float) position.y, text, color);
		} else if (textType == Strings.HOVERTEXT) {
			position = position.add(new Vec2(2, -13));
			Color cache = getColor();
			setColor(Color.white);
			drawRect(position.x, position.y, hoverFont.getWidth(text),
					hoverFont.getHeight(text),
					Game.textureStore.getTexture("blank"));
			hoverFont.drawString((float) position.x, (float) position.y, text,
					cache);
			setColor(cache);
		} else if (textType == Strings.DESCRIPTORTEXT) {
			descriptorFont.drawString((float) position.x, (float) position.y,
					text, color);
		}
		glPopMatrix();
	}

	public static Dimension screenDimension() {
		return size;
	}

}

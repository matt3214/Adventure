import org.lwjgl.opengl.Display;

public class Mouse {
	public static double getX() {
		return org.lwjgl.input.Mouse.getX();
	}

	public static double getY() {
		return Display.getHeight() - org.lwjgl.input.Mouse.getY();
	}

	public static Vec2 getPos() {
		return new Vec2(org.lwjgl.input.Mouse.getX(), Display.getHeight()
				- org.lwjgl.input.Mouse.getY());
	}
}

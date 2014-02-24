public class Vec2 {
	public double x, y;

	public Vec2(double x, double y) {
		this.x = x;
		this.setY(y);
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Vec2 copy() {
		return new Vec2(x,y);
	}
	
	@Override
	public String toString(){
		return x + ", " + y;
	}
}

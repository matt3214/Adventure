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
	
	public Vec2 add(Vec2 vec1){
		return new Vec2(x+vec1.x,y+vec1.y);
	}
	
	public static Vec2 add(Vec2 vec1,Vec2 vec2){
		return new Vec2(vec1.x + vec2.x,vec1.y +vec2.y);
	}
	
	@Override
	public String toString(){
		return x + ", " + y;
	}

	public Vec2 subtract(Vec2 vec2) {
		return new Vec2(x - vec2.x, y - vec2.y);
	}
}

package HyperionStudios.Core.Maths;

public class Vector2 {
	
	public static final Vector2 ZERO = new Vector2(0f, 0f);
	
	// ----- New Vector ----- \\
	
	public float x;
	public float y;
	
	public float kEpsilon = 0.00001F;
	
	public Vector2() {
		this.x = 0f;
		this.y = 0f;
	}
	
	public Vector2(Vector2 v) {
		this.x = v.x;
		this.y = v.y;
	}
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2 set(float x, float y) {
	    this.x = x;
	    this.y = y;
	    return this;
	}
	
	public Vector2 add(Vector2 v) {
	    this.x += v.x;
	    this.y += v.y;
	    return this;
	}
	
	public Vector2 sub(Vector2 v) {
	    this.x -= v.x;
	    this.y -= v.y;
	    return this;
	}
	
	public Vector2 mul(float value) {
	    this.x *= value;
	    this.y *= value;
	    return this;
	}
	
	public float dot(Vector2 v) {
	    return this.x * v.x + this.y * v.y;
	}
	
	public float distance(Vector2 v) {
	    float dx = v.x - this.x;
	    float dy = v.y - this.y;
	    
	    return (float) Math.sqrt(dx * dx + dy * dy);
	}
	
	public Vector2 mid(Vector2 a, Vector2 b) {
		return new Vector2((a.x + b.x) * 0.5f, (a.y + b.y) * 0.5f);
	}
	
	public Vector2 normalize() {
		float mag = (float) Math.sqrt(x * x + y * y);
	    
		if (mag > kEpsilon) {
            x /= mag;
            y /= mag;
		} else {
            x = ZERO.x;
            y = ZERO.y;
		}
		
	    return this;
	}
	
	public Vector2 normalized() {
		Vector2 v = new Vector2(x, y);
        v.normalize();
        return v;
	}
	
	public float length() {
	    return (float) Math.sqrt(x * x + y * y);
	}
}

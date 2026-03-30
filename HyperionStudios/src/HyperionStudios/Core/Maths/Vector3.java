package HyperionStudios.Core.Maths;

public class Vector3 {
	
	public static final Vector3 ZERO = new Vector3(0f, 0f, 0f);
	
	// ----- New Vector ----- \\
	
	public float a; // angle
	
	public float x;
	public float y;
	public float z;
	
	public float kEpsilon = 0.00001F;
	
	public Vector3() {
		this.a = 0f;
		this.x = 0f;
		this.y = 0f;
		this.z = 0f;
	}
	
	public Vector3(Vector2 v) {
		this.a = 0f;
		this.z = 0f;
		
		this.x = v.x;
		this.y = v.y;
	}
	
	public Vector3(Vector3 v) {
		this.a = v.a;
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
	}
	
	public Vector3(float x, float y, float z) {
		this.a = 0f;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3 set(float x, float y, float z) {
	    this.x = x;
	    this.y = y;
	    this.z = z;
	    return this;
	}
	
	public Vector3 add(Object obj) {
		if (obj instanceof Vector2) {
			Vector2 v = (Vector2) obj;
			
			this.x += v.x;
		    this.y += v.y;
		} else if (obj instanceof Vector3) {
			Vector3 v = (Vector3) obj;
			
			this.x += v.x;
		    this.y += v.y;
		    this.z += v.z;
		}
		
	    return this;
	}
	
	public Vector3 sub(Vector3 v) {
	    this.x -= v.x;
	    this.y -= v.y;
	    this.z -= v.z;
	    return this;
	}
	
	public Vector3 mul(float value) {
	    this.x *= value;
	    this.y *= value;
	    this.z *= value;
	    return this;
	}
	
	public float dot(Vector3 v) {
	    return this.x * v.x + this.y * v.y + this.z * v.z;
	}
	
	public float angle(Object obj) {
		float angle = 0f;
		
		if (obj instanceof Vector2) {
			Vector2 v = (Vector2) obj;
			
			float dx = v.x - this.x;
		    float dy = v.y - this.y;
		    
		    angle = (float) Math.toDegrees(Math.atan2(dx, dy));
		} else if (obj instanceof Vector3) {
			Vector3 v = (Vector3) obj;
			
			float dx = v.x - this.x;
		    float dy = v.y - this.y;
		    
		    angle = (float) Math.toDegrees(Math.atan2(dx, dy));
		}
		
		if (angle < 0) angle += 360f;
		
		return angle;
	}
	
	public float distance(Vector3 v) {
	    float dx = v.x - this.x;
	    float dy = v.y - this.y;
	    float dz = v.z - this.z;
	    
	    return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
	}
	
	public Vector3 normalize() {
		float mag = (float) Math.sqrt(x * x + y * y + z * z);
	    
		if (mag > kEpsilon) {
            x /= mag;
            y /= mag;
            z /= mag;
		} else {
            x = ZERO.x;
            y = ZERO.y;
            z = ZERO.z;
		}
		
	    return this;
	}
	
	public Vector3 normalized() {
		Vector3 v = new Vector3(x, y, z);
        v.normalize();
        return v;
	}
	
	public float length() {
	    return (float) Math.sqrt(x * x + y * y + z * z);
	}
}


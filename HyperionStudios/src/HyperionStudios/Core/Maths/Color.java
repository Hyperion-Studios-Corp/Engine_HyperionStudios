package HyperionStudios.Core.Maths;

public class Color {
	
	public static final int BLACK = 0x000000FF;
	public static final int WHITE = 0xFFFFFFFF;
	public static final int GREEN = 0x00FF00FF;
	public static final int BLUE  = 0x0000FFFF;
	public static final int RED   = 0xFF0000FF;
	
	public static final int TRANSPARENT = 0x00000000;
	
	public int R = 0;
	public int G = 0;
	public int B = 0;
	public int A = 0;
	
	public Color(int r, int g, int b) {
	    this(r, g, b, 255);
	}
	
	public Color(int r, int g, int b, int a) {
	    R = r;
	    G = g;
	    B = b;
	    A = a;
	}
	
	public Color(int color) {
		R = (color >> 24) & 0xFF;
		G = (color >> 16) & 0xFF;
		B = (color >> 8) & 0xFF;
		A = color & 0xFF;
	}
	
	public static Color fromHex(String hex) {
	    hex = hex.replace("#", "");
	    
	    int r = Integer.parseInt(hex.substring(0, 2), 16);
	    int g = Integer.parseInt(hex.substring(2, 4), 16);
	    int b = Integer.parseInt(hex.substring(4, 6), 16);
	    
	    return new Color(r, g, b, 255);
	}
	
	public float getRf() { return R / 255f; }
	public float getGf() { return G / 255f; }
	public float getBf() { return B / 255f; }
	public float getAf() { return A / 255f; }
}

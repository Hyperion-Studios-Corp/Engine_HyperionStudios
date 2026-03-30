package HyperionStudios.Core.Graphics.Font;

public class Glyph {
	
	public Character chars;
	
    public float x = 0f;
    public float y = 0f;
    
    public float width = 0f;
    public float height = 0f;
    
    public float xOffset = 0f;
    public float yOffset = 0f;
    
    public float xAdvance = 0f;
    
    // ----- Render ----- \\
    
    public int textureID = 0;
    
    public float u1 = 0f;
    public float u2 = 0f;
    
    public float v1 = 0f;
    public float v2 = 0f;
    
}

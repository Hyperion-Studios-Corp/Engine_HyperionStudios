package HyperionStudios.Core.Graphics;

public class Texture {
	
    private int textureID;
    
    private int width;
    private int height;
    
    private int originalWidth;
    private int originalHeight;
    
    private int[] pixels;
    
    public Texture(int textureID, int width, int height, int originalWidth, int originalHeight, int[] pixels) {
        this.textureID = textureID;
        this.width = width;
        this.height = height;
        this.originalWidth = originalWidth;
        this.originalHeight = originalHeight;
        
        this.pixels = pixels;
    }
    
    public int getTextureID() { return textureID; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    
    public int getOriginalWidth() { return originalWidth; }
    public int getOriginalHeight() { return originalHeight; }
    
    public int[] getPixels() { return pixels; }
}
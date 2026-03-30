package HyperionStudios.Core.Tiled.Renderer;

public class TileBlock {
    
    private int textureID;
    private float u, v, uvWidth, uvHeight;
    
    private int height;
    private int width;
    
    public TileBlock(int textureID, float u, float v, float uvWidth, float uvHeight) {
        this.textureID = textureID;
        this.u = u;
        this.v = v;
        this.uvWidth = uvWidth;
        this.uvHeight = uvHeight;
    }
    
    public int getTextureID() {
        return textureID;
    }
    
    public float getU() {
        return u;
    }
    
    public float getV() {
        return v;
    }
    
    public float getUVWidth() {
        return uvWidth;
    }
    
    public float getUVHeight() {
        return uvHeight;
    }

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
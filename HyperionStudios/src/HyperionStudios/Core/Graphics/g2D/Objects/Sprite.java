package HyperionStudios.Core.Graphics.g2D.Objects;

import HyperionStudios.Core.Graphics.Texture;
import HyperionStudios.Core.Maths.Vector2;
import HyperionStudios.Core.Maths.Vector3;
import HyperionStudios.Core.Resource.AssetManager;

public class Sprite {
	
	protected Texture texture;
	
    protected int textureID;
    protected float u, v, uvWidth, uvHeight;
    
    private Vector2 position;
    
    private String texturePath;
    
    private int width;
    private int height;
    
    private float pivotX = 0f;
    private float pivotY = 0f;
    
    private float scale = 1f;
    private float alpha = 1f;
    
    private Vector3 rotation = new Vector3(0f, 0f, 0f);
    
    private boolean flipX = false;
    private boolean flipY = false;
    
    private boolean visible = true;
    
    public Sprite(int textureID, Vector2 position, int width, int height, float u, float v, float uvWidth, float uvHeight) {
        this.textureID = textureID;
        this.position = position;
        this.width = width;
        this.height = height;
        this.u = u;
        this.v = v;
        this.uvWidth = uvWidth;
        this.uvHeight = uvHeight;
    }
    
    public Sprite(int textureID, Vector2 position, int width, int height) {
        this(textureID, position, width, height, 0.0f, 0.0f, 1.0f, 1.0f);
    }
    
    public Sprite(String path, Vector2 position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.texturePath = path;
        
        load(path, true);
    }
    
    public Sprite(String path, Vector2 position) {
        this.position = position;
        this.texturePath = path;
        
        load(path, false);
    }
    
    private void load(String path, boolean isSizeExplicit) {
        texture = AssetManager.getTexture(path);
        
        if (texture == null) {
            System.err.println("Erro: Não foi possível carregar a textura para o caminho: " + path);
            textureID = 0;
            return;
        }
        
        this.textureID = texture.getTextureID();
        this.u = 0.0005f;
        this.v = 0.0005f;
        this.uvWidth = (float) texture.getOriginalWidth() / texture.getWidth();
        this.uvHeight = (float) texture.getOriginalHeight() / texture.getHeight();
        
        this.uvWidth -= u;
        this.uvHeight -= u;
        
        if (!isSizeExplicit) {
            this.width = texture.getOriginalWidth();
            this.height = texture.getOriginalHeight();
        }
    }
    
    public String getTexturePath() {
        return texturePath;
    }
    
    public int getTextureID() {
        return textureID;
    }
    
    public float getU() { return u; }
    public float getV() { return v; }
    public float getUVWidth() { return uvWidth; }
    public float getUVHeight() { return uvHeight; }
    
    public float getAlpha() {
        return alpha;
    }
    
    public void setAlpha(float alpha) {
        this.alpha = alpha;
        
        if (alpha <= 0f) this.alpha = 0f;
        if (alpha >= 1f) this.alpha = 1f;
    }
    
    public float getScale() {
        return scale;
    }
    
    public void setScale(float scale) {
        this.scale = scale;
    }
    
    public float getScaleX() {
        return flipX ? -scale : scale;
    }
    
    public float getScaleY() {
        return flipY ? -scale : scale;
    }
    
    public Vector3 getRotation() {
        return rotation;
    }
    
    public void setRotation(Vector3 rotation) {
        this.rotation = rotation;
    }
    
    public boolean isFlipX() {
        return flipX;
    }
    
    public void setFlipX(boolean flipX) {
        this.flipX = flipX;
    }
    
    public boolean isFlipY() {
        return flipY;
    }
    
    public void setFlipY(boolean flipY) {
        this.flipY = flipY;
    }
    
    public int getWidth() {
        return width;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public float getY() {
        return position.y;
    }
    
    public void setY(float y) {
        this.position.y = y;
    }
    
    public float getX() {
        return position.x;
    }
    
    public void setX(float x) {
        this.position.x = x;
    }
    
    public boolean isVisible() {
        return visible;
    }
    
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

	public float getPivotX() {
		return pivotX;
	}

	public float getPivotY() {
		return pivotY;
	}
	
	public void setPivot(float pivotX, float pivotY) {
		this.pivotX = pivotX;
		this.pivotY = pivotY;
	}
}
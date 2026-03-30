package HyperionStudios.Core.Graphics.g2D.Objects;

import HyperionStudios.Core.Graphics.Texture;
import HyperionStudios.Core.Maths.Vector2;
import HyperionStudios.Core.Resource.AssetManager;

public class SpriteSheet extends Sprite {
	
	protected int tileWidth;
	protected int tileHeight;
	
	protected int maxTileX = 0;
	protected int maxTileY = 0;

	protected int currentTileX = 0;
	protected int currentTileY = 0;
	
    public SpriteSheet(String path, Vector2 position, int tileWidth, int tileHeight) {
        super(path, position, tileWidth, tileHeight);
        
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        
        Texture baseTexture = AssetManager.getTexture(path);
        
        if (baseTexture == null) {
            System.err.println("Erro: Não foi possível carregar a textura base para SpriteSheet: " + path);
            return;
        }
        
        maxTileX = baseTexture.getWidth() / tileWidth;
        maxTileY = baseTexture.getHeight() / tileHeight;
        
        updateUVsForCurrentTile();
    }
    
    private void updateUVsForCurrentTile() {
        Texture baseTexture = AssetManager.getTexture(super.getTexturePath());
        
        if (baseTexture == null) return;
        
        float tileUvWidth = (float) tileWidth / baseTexture.getWidth();
        float tileUvHeight = (float) tileHeight / baseTexture.getHeight();
        
        super.u = currentTileX * tileUvWidth;
        super.v = currentTileY * tileUvHeight;
        super.uvWidth = tileUvWidth;
        super.uvHeight = tileUvHeight;
    }
    
    public int getMaxTileX() {
        return maxTileX - 1;
    }
    
    public int getMaxTileY() {
        return maxTileY - 1;
    }
    
    public int getCurrentTileX() {
        return currentTileX;
    }
    
    public int getCurrentTileY() {
        return currentTileY;
    }
    
    public void setTileX(int x) {
        if (x < 0) x = 0;
        
        if (x >= maxTileX) x = maxTileX - 1;
        
        this.currentTileX = x;
        
        updateUVsForCurrentTile();
    }
    
    public void setTileY(int y) {
        if (y < 0) y = 0;
        
        if (y >= maxTileY) y = maxTileY - 1;
        
        this.currentTileY = y;
        
        updateUVsForCurrentTile();
    }
    
    public void setTile(int x, int y) {
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        
        if (x >= maxTileX) x = maxTileX - 1;
        if (y >= maxTileY) y = maxTileY - 1;
        
        this.currentTileX = x;
        this.currentTileY = y;
        
        updateUVsForCurrentTile();
    }
}
package HyperionStudios.Core.Tiled.Renderer;

import java.util.ArrayList;
import java.util.HashMap;

import HyperionStudios.Core.Graphics.Texture;
import HyperionStudios.Core.Graphics.g2D.Objects.Sprite;
import HyperionStudios.Core.Maths.Vector2;
import HyperionStudios.Core.Resource.AssetManager;
import HyperionStudios.Core.Tiled.Map;
import HyperionStudios.Core.Tiled.TiledMap;
import HyperionStudios.Core.Tiled.Tilesets;

public class TiledMapRenderer {
	
	protected java.util.Map<String, LayerRender> RenderLayer = new HashMap<>();
	
	protected ArrayList<TileBlock> world = new ArrayList<>(); 
	
	protected Map _map;
	
	public TiledMapRenderer(TiledMap tiledMap) {
		_map = tiledMap._map;
		
		int currentTileGid = 0;
		
		HashMap<Integer, TileBlock> gidToTileBlockMap = new HashMap<>();
		
		for (int i = 0; i < _map.Tileset.length; i++) {
			Tilesets currentTileset = _map.Tileset[i];
			
			Texture tilesetTexture = AssetManager.getTexture(AssetManager.getFolder(tiledMap.path) + "/" + currentTileset.Image.replace(".png", ""));
			
			if (tilesetTexture == null) {
				System.err.println("Erro: Não foi possível carregar o tileset texture: " + currentTileset.Image);
				continue;
			}
			
			int cols = currentTileset.ImageWidth / currentTileset.TileWidth;
			int rows = currentTileset.ImageHeight / currentTileset.TileHeight;
			
			float tileUvWidth = (float) currentTileset.TileWidth / tilesetTexture.getWidth();
			float tileUvHeight = (float) currentTileset.TileHeight / tilesetTexture.getHeight();
			
			int firstGid = currentTileset.FirstGID;
			
			for (int row = 0; row < rows; row++) {
				for (int col = 0; col < cols; col++) {
					currentTileGid = firstGid + (row * cols) + col;
					
					float u = col * tileUvWidth;
					float v = row * tileUvHeight;
					
					TileBlock tileBlock = new TileBlock(tilesetTexture.getTextureID(), u, v, tileUvWidth, tileUvHeight);
					tileBlock.setHeight(currentTileset.TileHeight);
					tileBlock.setWidth(currentTileset.TileWidth);
					
					gidToTileBlockMap.put(currentTileGid, tileBlock);
				}
			}
		}
		
		int maxGid = 0;
		
		for(int gid : gidToTileBlockMap.keySet()) {
			if (gid > maxGid) maxGid = gid;
		}
		
		world.ensureCapacity(maxGid);
		
		while(world.size() < maxGid) {
		    world.add(null);
		}
		
		for (java.util.Map.Entry<Integer, TileBlock> entry : gidToTileBlockMap.entrySet()) {
		    world.set(entry.getKey() - 1, entry.getValue());
		}
	}
	
	public LayerRender render(String LayerName) {
		if (RenderLayer.containsKey(LayerName)) {
			return RenderLayer.get(LayerName);
		}
		
		int[][] tiles = _map.Layer.get(LayerName).Tile;
		
		LayerRender ll = new LayerRender(_map.Width, _map.Height);
		
		System.out.println("\n\nRender : " + LayerName);
		
		System.out.println(
        		" Layers : " + LayerName +
        		"\n ( Height : " + _map.Height + " )" +
        		"\n ( Width : " + _map.Width + " )" +
        		"\n\n"
        );
		
        for (int y = 0; y < _map.Height; y++) {
            for (int x = 0; x < _map.Width; x++) {
            	
                int gid = tiles[y][x];
                
                if (gid == 0 || gid > world.size() || world.get(gid - 1) == null) {
                	ll.tile[y][x] = null;
                	continue;
                }
                
                TileBlock tileBlock = world.get(gid - 1);
                
                float mapTileW = _map.TileWidth;
                float mapTileH = _map.TileHeight;
                
                float offsetX = _map.Layer.get(LayerName).OffsetX;
                float offsetY = _map.Layer.get(LayerName).OffsetY;
                
                float screenX = x * (mapTileW / 2f);
                float screenY = y * (mapTileH / 2f);
                
                if (_map.Orientation.equals("isometric")) {
                	screenX = (x - y) * (mapTileW / 2f);
                	screenY = (x + y) * (mapTileH / 2f);
                }
                
                screenX += offsetX;
                screenY += offsetY;
                
                screenY -= (tileBlock.getHeight() - mapTileH);
                
                screenY += mapTileH;
                
                ll.tile[y][x] = new Sprite(
                		tileBlock.getTextureID(),
                		new Vector2(screenX, screenY),
                		tileBlock.getWidth(),
                		tileBlock.getHeight(),
                		tileBlock.getU(), tileBlock.getV(), tileBlock.getUVWidth(), tileBlock.getUVHeight()
                );
            }
        }
        
        RenderLayer.put(LayerName, ll);
        
		return ll;
	}
}
package HyperionStudios.Core.Tiled;

import java.util.HashMap;

public class Map {
	
	public String RenderOrder;
	public String Orientation;
	
	public int TileHeight;
	public int TileWidth;
	public int Height;
	public int Width;
	
	public int NextObjectID;
	public int NextLayerID;
	public int Infinite;
	
	public Tilesets[] Tileset;
	
	public HashMap<String, Layers> Layer = new HashMap<>();;
	
}

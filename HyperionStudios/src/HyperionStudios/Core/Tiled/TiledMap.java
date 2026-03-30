package HyperionStudios.Core.Tiled;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import HyperionStudios.Core.Resource.AssetManager;

public class TiledMap {
	
	public String path;
	public Map _map;
	
	public TiledMap(String path) {
		this.path = path;
		
		try {
			Document doc = AssetManager.getXml(path);
			
			_map = new Map();
			
			getMap(doc);
			getTileset(doc);
			getLayers(doc);
		} catch (Exception e) {
			System.err.println("Erro ao carregar TiledMap do caminho: " + path);
			e.printStackTrace();
		}
	}
	
	private void getMap(Document doc) throws Exception {
	    NodeList mapp = doc.getElementsByTagName("map");
	    
	    Element map = (Element) mapp.item(0);
	    
	    System.out.println("\n\nVersão do TMX: " + map.getAttribute("tiledversion"));
	    
	    _map.RenderOrder = map.getAttribute("renderorder");
	    _map.Orientation = map.getAttribute("orientation");
	    
	    _map.TileHeight = Integer.parseInt(map.getAttribute("tileheight"));
	    _map.TileWidth = Integer.parseInt(map.getAttribute("tilewidth"));
	    _map.Height = Integer.parseInt(map.getAttribute("height"));
	    _map.Width = Integer.parseInt(map.getAttribute("width"));
	    
	    String nextObjectIdStr = map.getAttribute("nextobjectid");
	    _map.NextObjectID = nextObjectIdStr.isEmpty()? 0 : Integer.parseInt(nextObjectIdStr);
	    
	    String nextLayerIdStr = map.getAttribute("nextlayerid");
	    _map.NextLayerID = nextLayerIdStr.isEmpty()? 0 : Integer.parseInt(nextLayerIdStr);
	    
	    String infiniteStr = map.getAttribute("infinite");
	    _map.Infinite = infiniteStr.isEmpty()? 0 : Integer.parseInt(infiniteStr);
	    
        System.out.println(
        		" Map : " + _map.Orientation +
        		"\n ( TileHeight : " + _map.TileHeight + " )" +
        		"\n ( TileWidth : " + _map.TileWidth + " )" +
        		"\n ( Height : " + _map.Height + " )" +
        		"\n ( Width : " + _map.Width + " )"
        );
	}
	
	private void getTileset(Document doc) throws Exception {
	    NodeList tilesets = doc.getElementsByTagName("tileset");
	    
	    System.out.println("\n\nNumeros de Tileset: " + tilesets.getLength());
	    
	    _map.Tileset = new Tilesets[tilesets.getLength()];
	    
	    for (int i = 0; i < tilesets.getLength(); i++) {
	    	
	        Element tileset = (Element) tilesets.item(i);
	        
	        Tilesets ts = new Tilesets();
	        
	        ts.FirstGID = Integer.parseInt(tileset.getAttribute("firstgid"));
	        
	        ts.TileHeight = Integer.parseInt(tileset.getAttribute("tileheight"));
	        ts.TileWidth = Integer.parseInt(tileset.getAttribute("tilewidth"));
	        
	        ts.TileCount = Integer.parseInt(tileset.getAttribute("tilecount"));
	        ts.Columns = Integer.parseInt(tileset.getAttribute("columns"));
	        
	        Element image = (Element) tileset.getElementsByTagName("image").item(0);
	        
	        ts.Image = image.getAttribute("source");
	        
	        ts.ImageWidth = Integer.parseInt(image.getAttribute("width"));
	        ts.ImageHeight = Integer.parseInt(image.getAttribute("height"));
	        
	        _map.Tileset[i] = ts;
	        
	        System.out.println(
	        		" Tileset : " + i +
	        		"\n ( ImageHeight : " + ts.ImageHeight + " )" +
	        		"\n ( ImageWidth : " + ts.ImageWidth + " )" +
	        		"\n ( TileHeight : " + ts.TileHeight + " )" +
	        		"\n ( TileWidth : " + ts.TileWidth + " )" +
	        		"\n ( TileCount : " + ts.TileCount + " )" +
	        		"\n ( FirstGID : " + ts.FirstGID + " )" +
	        		"\n ( Columns : " + ts.Columns + " )"
	        );
	    }
	}
	
	private void getLayers(Document doc) {
	    NodeList layers = doc.getElementsByTagName("layer");
	    
	    System.out.println("\n\nNumeros de Layers: " + layers.getLength());
	    
	    for (int i = 0; i < layers.getLength(); i++) {
	        Element layer = (Element) layers.item(i);
	        
	        Layers ls = new Layers();
	        
	        ls.Height = Integer.parseInt(layer.getAttribute("height"));
	        ls.Width = Integer.parseInt(layer.getAttribute("width"));
	        ls.Name = layer.getAttribute("name");
	        ls.ID = Integer.parseInt(layer.getAttribute("id"));
	        
	        if (!layer.getAttribute("offsetx").isBlank()) {
	        	ls.OffsetX = Integer.parseInt(layer.getAttribute("offsetx"));
	        }
	        
	        if (!layer.getAttribute("offsety").isBlank()) {
	        	ls.OffsetY = Integer.parseInt(layer.getAttribute("offsety"));
	        }
	        
	        System.out.println(
	        		" Layers : " + i +
	        		"\n ( Offset X : " + ls.OffsetX + " )" +
	        		"\n ( Offset Y : " + ls.OffsetY + " )" +
	        		"\n ( Height : " + ls.Height + " )" +
	        		"\n ( Width : " + ls.Width + " )" +
	        		"\n ( Name : " + ls.Name + " )" +
	        		"\n ( ID : " + ls.ID + " )"
	        );
	        
	        Element data = (Element) layer.getElementsByTagName("data").item(0);
	        
	        String csv = data.getTextContent().trim();
	        
	        String[] tiles = csv.replace("\n", "").split(",");
	        
	        int width = ls.Width;
	        int height = ls.Height;
	        
	        int[][] mapData = new int[height][width];
	        
	        for (int j = 0; j < tiles.length && j < width * height; j++) {
	            String value = tiles[j].trim();
	            
	            if (value.isEmpty()) {
					mapData[j / width][j % width] = 0;
					continue;
				}
	            
	            int tile = Integer.parseInt(value);
	            
	            int x = j % width;
	            int y = j / width;
	            
	            mapData[y][x] = tile;
	        }
	        
	        ls.Tile = mapData;
	        
	        _map.Layer.put(ls.Name, ls);
	    }
	}
}
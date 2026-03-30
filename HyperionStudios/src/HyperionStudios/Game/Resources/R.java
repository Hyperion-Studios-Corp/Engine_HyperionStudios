package HyperionStudios.Game.Resources;

import java.util.HashMap;

import HyperionStudios.Core.Graphics.Font.FontBitmap;
import HyperionStudios.Core.Graphics.Font.Glyph;
import HyperionStudios.Core.Resource.AssetManager;
import HyperionStudios.Core.Tiled.TiledMap;

public class R {
	
	private static HashMap<String, HashMap<Character, Glyph>> font = new HashMap<>();
	
	private static HashMap<String, TiledMap> _TiledMap = new HashMap<>();
	
	// ----- Obter Assets Carregados ----- \\
	
	public static TiledMap getTiledMap(String name) {
		return _TiledMap.get(name);
	}
	
	public static HashMap<Character, Glyph> getFont(String name) {
		return font.get(name);
	}
	
	// ----- Carregar Assets ----- \\
	
	public static void loadTiledMap(String path) {
		_TiledMap.put(AssetManager.getFile(path), new TiledMap(path));
	}
	
	public static void loadFont(String path) {
		font.put(AssetManager.getFile(path), new FontBitmap(path).getFont());
	}
}

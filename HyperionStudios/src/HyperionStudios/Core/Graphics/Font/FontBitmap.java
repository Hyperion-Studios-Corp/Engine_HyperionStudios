package HyperionStudios.Core.Graphics.Font;

import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import HyperionStudios.Core.Graphics.Texture;
import HyperionStudios.Core.Resource.AssetManager;

public class FontBitmap {
	
	private String path;
	
	private HashMap<Character, Glyph> glyphs = new HashMap<>();
	
	public HashMap<Character, Glyph> getFont() {
		return glyphs;
	}
	
	public FontBitmap(String path) {
		this.path = path;
		
		try {
			Document doc = AssetManager.getXml(path);
			
			loadGlyph(doc);
			loadTexture();
		} catch (Exception e) {
			System.err.println("Erro ao carregar Font Bitmap do caminho: " + path);
			e.printStackTrace();
		}
	}
	
	private void loadTexture() {
		Texture _texture = AssetManager.getTexture(path);
		
		if (_texture == null) {
			System.err.println("Erro: Não foi possível carregar o Glyph texture: " + path);
			return;
		}
		
		float texH = _texture.getOriginalHeight();
		float texW = _texture.getOriginalWidth();
		
		for (Glyph glyph : glyphs.values()) {
			glyph.u1 = glyph.x / texW;
			glyph.v1 = glyph.y / texH;
			
			glyph.u2 = (glyph.x + glyph.width) / texW;
			glyph.v2 = (glyph.y + glyph.height) / texH;
			
			glyph.textureID = _texture.getTextureID();
		}
	}
	
	private void loadGlyph(Document doc) {
		NodeList Letter = doc.getElementsByTagName("Letter");
		
		System.out.println("\n\nNumeros de Glyph: " + Letter.getLength());
		
		for (int i = 0; i < Letter.getLength(); i++) {
			Element chars = (Element) Letter.item(i);
			
			Glyph glyph = new Glyph();
			
			glyph.x = Float.parseFloat(chars.getAttribute("X").replace(",", "."));
			glyph.y = Float.parseFloat(chars.getAttribute("Y").replace(",", "."));
			
			glyph.height = Float.parseFloat(chars.getAttribute("Height").replace(",", "."));
			glyph.width = Float.parseFloat(chars.getAttribute("Width").replace(",", "."));
			
			String str = chars.getAttribute("Char");
			
			if (str == null || str.isEmpty()) continue;
			
			char c = str.charAt(0);
			
			glyph.chars = c;
			
			glyphs.put(c, glyph);
		}
	}
}

package HyperionStudios.Core.Graphics.Hud;

import java.util.HashMap;

import HyperionStudios.Core.Graphics.Font.DrawFont;
import HyperionStudios.Core.Graphics.Font.Glyph;
import HyperionStudios.Core.Graphics.g2D.Objects.Sprite;
import HyperionStudios.Core.Graphics.g2D.Objects.SpriteButton;
import HyperionStudios.Core.Graphics.g2D.Objects.SpriteSheet;
import HyperionStudios.Core.Graphics.g3D.Object3D;
import HyperionStudios.Core.Maths.Color;

public class HudBatch {
	
	private HudBackend backend;
	
	public HudBatch(HudBackend backend) {
		this.backend = backend;
	}
	
	public void draw(HashMap<Character, Glyph> bitmap, String text, int x, int y, int size, Color color) {
		int posX = x;
		int posY = y;
		
		if (backend != null) {
			for (int i = 0; i < text.length(); i++) {
				char c = text.charAt(i);
				
				if (bitmap.get(c) == null) continue;
				
				DrawFont d = new DrawFont();
				d.bitmap = bitmap.get(c);
				d.color = color;
				d.size = size;
				
				d.x = posX;
				d.y = posY;
				
				backend.drawText(d);
				
				posX += (d.bitmap.width / 2);
			}
		}
	}
	
	public void draw(HashMap<Character, Glyph> bitmap, String text, int x, int y, int size) {
		draw(bitmap, text, x, y, size, new Color(Color.WHITE));
	}
	
	public void draw(Object obj) {
		if (backend == null || obj == null) { return; }
		
		if (obj instanceof SpriteButton) {
        	backend.drawSprite((Sprite) obj);
        	return;
        }
        
        if (obj instanceof SpriteSheet) {
        	backend.drawSprite((SpriteSheet) obj);
        	return;
        }
        
        if (obj instanceof Sprite) {
        	backend.drawSprite((Sprite) obj);
        	return;
        }
        
        if (obj instanceof Object3D) {
            backend.drawObject((Object3D) obj);
            return;
        }
	}
	
	public void begin() {
		if (backend == null) return;
		
		backend.begin();
	}
	
	public void end() {
		if (backend == null) return;
		
		backend.end();
	}
}

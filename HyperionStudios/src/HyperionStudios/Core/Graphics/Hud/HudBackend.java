package HyperionStudios.Core.Graphics.Hud;

import HyperionStudios.Core.Graphics.Font.DrawFont;
import HyperionStudios.Core.Graphics.g2D.Objects.Sprite;
import HyperionStudios.Core.Graphics.g3D.Object3D;
import HyperionStudios.Core.Maths.Color;

public interface HudBackend {
	
	void begin();
	
	void end();
	
	void drawCube(int x, int y, int width, int height, Color color);
	
	void drawText(DrawFont font);
	
	void drawSprite(Sprite sprite);
	
	void drawObject(Object3D obj);
	
}

package HyperionStudios.Core.Tiled.Renderer;

import HyperionStudios.Core.Graphics.g2D.Objects.Sprite;

public class LayerRender {
	
	public Sprite[][] tile;
	
	public LayerRender(int x, int y) {
		tile = new Sprite[y][x];
	}
}

package HyperionStudios.Game.Scenes;

import HyperionStudios.Core.GameApplication;
import HyperionStudios.Core.Graphics.JWindow;
import HyperionStudios.Core.Graphics.g2D.SpriteBatch;
import HyperionStudios.Core.Maths.Color;
import HyperionStudios.Core.Screens.Camera;
import HyperionStudios.Core.Screens.Screen;
import HyperionStudios.Core.Tiled.TiledMap;
import HyperionStudios.Core.Tiled.Renderer.TiledMapRenderer;
import HyperionStudios.Game.Entitys.Player;
import HyperionStudios.Game.Resources.R;

public class GameScreen implements Screen {
	
	protected boolean isStartGame = false;
	
	protected TiledMapRenderer tiledD;
	protected TiledMap tiledMap;
	protected Camera camera;
	protected Player player;
	
	@Override
	public void update(float delta) {
		if (!isStartGame) return;
		
		player.update(delta);
		
		camera.setPosition(player.getPosition());
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(tiledD.render("Ground"));
		batch.draw(tiledD.render("Walls_1"));
		batch.draw(tiledD.render("Walls_2"));
		
		float x = player.getPosition().x;
		float y = player.getPosition().y;
		
		batch.drawCube(x - 22, y - 22, 22 * 2, 2, new Color(Color.RED));
		batch.drawCube(x - 22, y + 22, 22 * 2, 2, new Color(Color.RED));
		batch.drawCube(x - 22, y - 22, 2, 22 * 2, new Color(Color.RED));
		batch.drawCube(x + 22, y - 22, 2, 22 * 2, new Color(Color.RED));
		
		batch.draw(player);
		
		batch.hud.draw(R.getFont("Pixel"), "FPS : " + JWindow.fps, 0, 0, 12, new Color(Color.BLUE));
		batch.hud.draw(R.getFont("Pixel"), "X : " + x, 0, 15, 12, new Color(Color.BLUE));
		batch.hud.draw(R.getFont("Pixel"), "Y : " + y, 0, 30, 12, new Color(Color.BLUE));
	}
	
	@Override
	public void onEnter(GameApplication game) {
		tiledMap = R.getTiledMap("Map");
		tiledD = new TiledMapRenderer(tiledMap);
		
		player = new Player("Models/Player", "Entitys/Player/Player");
		camera = new Camera();
		
		game.setCamera(camera);
		
		isStartGame = true;
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onExit() {
		// TODO Auto-generated method stub
	}
}

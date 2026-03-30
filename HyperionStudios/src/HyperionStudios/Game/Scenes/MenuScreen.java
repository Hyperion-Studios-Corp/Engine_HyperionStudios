package HyperionStudios.Game.Scenes;

import HyperionStudios.Core.GameApplication;
import HyperionStudios.Core.Graphics.JWindow;
import HyperionStudios.Core.Graphics.g2D.SpriteBatch;
import HyperionStudios.Core.Graphics.g2D.Objects.Sprite;
import HyperionStudios.Core.Graphics.g2D.Objects.SpriteButton;
import HyperionStudios.Core.Maths.Vector2;
import HyperionStudios.Core.Screens.Screen;
import HyperionStudios.Core.Screens.ScreenManager;

public class MenuScreen implements Screen {
	
	private SpriteButton ButtonNewGame;
	private Sprite Background;
	
	public MenuScreen() {
		System.out.println("MainScreen (Game): MenuScreen(ScreenManager manager)");
	}
	
	@Override
    public void onEnter(GameApplication game) {
		System.out.println("MenuScreen (Game): onEnter()");
		
		ButtonNewGame = new SpriteButton("Gui/Button-New-Game", new Vector2());
		
		ButtonNewGame.setY((JWindow.getHeight() / 2) - (ButtonNewGame.getHeight() / 2));
		ButtonNewGame.setX((JWindow.getWidth() / 2) - (ButtonNewGame.getWidth() / 2));
		
		Background = new Sprite("Background/MenuScreen", new Vector2(), JWindow.getWidth(), JWindow.getHeight());
		Background.setPivot(0, 0);
		
		ButtonNewGame.setOnClick(new Runnable() {
				@Override
				public void run() {
					ScreenManager.setScreen(new GameScreen());
				}
			}
		);
    }
    
    @Override
    public void update(float delta) {
        
    }
    
    @Override
    public void render(SpriteBatch batch) {
    	batch.draw(Background);
    	
    	batch.draw(ButtonNewGame);
    }
    
    @Override
	public void onPause() {
		// TODO Auto-generated method stub
	}
	
    @Override
    public void onExit() {
        ButtonNewGame.dispose();
    }
}

package HyperionStudios.Core.Screens;

import HyperionStudios.Core.GameApplication;
import HyperionStudios.Core.Graphics.g2D.SpriteBatch;

public class ScreenManager {
	
	public static GameApplication game;
	
    private static Screen currentScreen;
    
    public static Screen getScreen() {
    	return currentScreen;
    }
    
    public static void setScreen(Screen newScreen) {
        if (currentScreen != null) {
            currentScreen.onExit();
        }
        
        currentScreen = newScreen;
        
        if (currentScreen != null) {
            currentScreen.onEnter(game);
        }
    }
    
    public static void update(float delta) {
        if (currentScreen != null) {
            currentScreen.update(delta);
        }
    }
    
    public static void draw(SpriteBatch batch) {
        if (currentScreen != null) {
            currentScreen.render(batch);
        }
    }
}
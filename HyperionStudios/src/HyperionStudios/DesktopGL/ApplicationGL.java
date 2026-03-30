package HyperionStudios.DesktopGL;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import HyperionStudios.Core.GameBackend;
import HyperionStudios.Core.Graphics.JWindow;
import HyperionStudios.Core.Graphics.Hud.HudBatch;
import HyperionStudios.Core.Graphics.g2D.SpriteBatch;
import HyperionStudios.Core.Input.Input;
import HyperionStudios.Core.Resource.AssetManager;
import HyperionStudios.Core.Screens.Camera;
import HyperionStudios.DesktopGL.Graphics.BatchGL;
import HyperionStudios.DesktopGL.Graphics.HudGL;
import HyperionStudios.DesktopGL.Input.InputGL;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class ApplicationGL implements GameBackend {
	
	private SpriteBatch _SpriteBatch;
	private HudBatch _HudBatch;
	
	public void initDisplay() {
        try {
            DisplayMode chosenMode = null;
            DisplayMode[] modes = Display.getAvailableDisplayModes();
            
            for (DisplayMode mode : modes) {
                if (mode.getWidth() == JWindow.getWidth() && mode.getHeight() == JWindow.getHeight() && mode.isFullscreenCapable()) {
                    if (chosenMode == null || mode.getFrequency() >= chosenMode.getFrequency()) {
                        chosenMode = mode;
                    }
                }
            }
            
        	Display.setDisplayMode(new DisplayMode(JWindow.getWidth(), JWindow.getHeight()));
            Display.setFullscreen(true);
            
            Display.setTitle(JWindow.getAppName());
            
            Display.create();
            
            JWindow.Height = Display.getHeight();
            JWindow.Width = Display.getWidth();
            
            Display.setVSyncEnabled(true);
            
            GL11.glViewport(0, 0, JWindow.getWidth(), JWindow.getHeight());
            GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
            
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDepthFunc(GL11.GL_LEQUAL);
            
            Keyboard.create();
            Mouse.create();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
	
	public void initGame() {
    	AssetManager.setBackend(new AssetGL());
    	Input.setBackend(new InputGL());
    	
    	_HudBatch = new HudBatch(new HudGL());
    	
    	_SpriteBatch = new SpriteBatch(new BatchGL(), _HudBatch);
    }
	
	public void Begin() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        
        GL11.glOrtho(0, JWindow.getWidth(), JWindow.getHeight(), 0, -1000, 1000); 
        
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        
        _SpriteBatch.begin();
	}
	
	public void ApplyCamera(Camera camera) {
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        
        GL11.glScalef(camera.zoom, camera.zoom, 1.0f);
        
        GL11.glTranslatef(
            -(camera.position.x - (JWindow.getWidth() / 2)),
            -(camera.position.y - (JWindow.getHeight() / 2)),
            camera.position.z
        );
	}
	
	public void End() {
   	 	_SpriteBatch.end();
   	 	
   	 	_HudBatch.begin();
		_HudBatch.end();
        
        Display.update();
        Display.sync(60);
    }
	
	public void cleanup() {
    	AssetManager.dispose();
    	
        Display.destroy();
        
        Keyboard.destroy();
        Mouse.destroy();
    }
	
	public boolean isRunning() {
		return Display.isCloseRequested();
	}
	
	public void InputLoop() {
		Input.update();
	}
	
	public SpriteBatch getBatch() {
		return _SpriteBatch;
	}
}
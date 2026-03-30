package HyperionStudios.Core;

import HyperionStudios.Core.Graphics.JWindow;
import HyperionStudios.Core.Graphics.g2D.SpriteBatch;
import HyperionStudios.Core.Screens.Camera;
import HyperionStudios.Core.Screens.ScreenManager;

public abstract class GameApplication {
	
    private boolean running = false;
	
	// ----- Iniciar Cebro da Engine ----- \\
	
	public void Start() {
		if (backend == null) return;
		
		backend.initDisplay();
		backend.initGame();
		
		Initialize();
		
		running = true;
		
		loop();
	}
	
	// ----- Camera do Jogo ----- \\
	
	private Camera camera;
    
    public void setCamera(Camera camera) {
    	this.camera = camera;
    }
    
    public Camera getCamera() {
    	return camera;
    }
	
	// ----- Finalizar Cebro da Engine ----- \\
	
	public void cleanup() {
		backend.cleanup();
	}
	
	// ----- Obter Backend ----- \\
	
	private GameBackend backend;
	
	public GameApplication(GameBackend backend) {
        this.backend = backend;
    	
        ScreenManager.game = this;
    }
	
	// ----- Loop do Jogo ----- \\
	
	private void loop() {
		long lastTime = System.nanoTime();
		long fpsTime = System.nanoTime();
        
        float accumulator = 0f;
        float fixedDeltaTime = 1f / 60f;
        
        while (!backend.isRunning() && running) {
        	long now = System.nanoTime();
            float frameTime = (now - lastTime) / 1_000_000_000f;
            lastTime = now;
            
            accumulator += frameTime;
            
            while(accumulator >= fixedDeltaTime) {
                accumulator -= fixedDeltaTime;
                
                backend.InputLoop();
                
                ScreenManager.update(fixedDeltaTime);
                
                if (ScreenManager.getScreen() == null ) {
                	Update(fixedDeltaTime);
                }
                
                backend.Begin();
                
                if (camera != null) {
                	backend.ApplyCamera(camera);
                }
                
                ScreenManager.draw(backend.getBatch());
                
                if (ScreenManager.getScreen() == null ) {
                	Draw(backend.getBatch());
                }
                
                backend.End();
                
                JWindow.frame++;
            }
            
            if (now - fpsTime >= 1_000_000_000f) {
            	JWindow.fps = JWindow.frame;
            	JWindow.frame = 0;
            	
            	fpsTime = now;
            }
        }
	}
	
	// ----- Abstract Para Inicializar Game ----- \\
	
	public abstract void Initialize();
    public abstract void Update(float delta);
    public abstract void Draw(SpriteBatch batch);
    public abstract void Dispose();
}

package HyperionStudios.Core;

import HyperionStudios.Core.Graphics.g2D.SpriteBatch;
import HyperionStudios.Core.Screens.Camera;

public interface GameBackend {
	
	void initDisplay();
	void initGame();
	
	void cleanup();
	
	boolean isRunning();
	
	SpriteBatch getBatch();
	
	void ApplyCamera(Camera camera);
	
	// ----- Updates ----- \\
	
	void InputLoop();

	void Begin();
	void End();
	
}

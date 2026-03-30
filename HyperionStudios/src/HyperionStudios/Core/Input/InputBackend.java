package HyperionStudios.Core.Input;

public interface InputBackend {
	
	void update();
	
	boolean isKeyDown(int key);
	boolean isKeyPressed(int key);
	boolean isKeyReleased(int key);
	
	boolean isMouseDown(int button);
	boolean isMousePressed(int button);
	boolean isMouseReleased(int button);
	
	float getMouseX();
	float getMouseY();
	
	int getMouseWheelDelta();
}

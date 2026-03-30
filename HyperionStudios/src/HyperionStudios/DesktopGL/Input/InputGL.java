package HyperionStudios.DesktopGL.Input;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import HyperionStudios.Core.Input.Code;
import HyperionStudios.Core.Input.InputBackend;

public class InputGL implements InputBackend {
	
	public InputGL() {
		System.out.print("InputGL (Engine): Inicializando...\n");
	}
	
	private boolean[] currentKeys = new boolean[Keyboard.getKeyCount()];
	private boolean[] previousKeys = new boolean[Keyboard.getKeyCount()];
	
	private boolean[] currentMouse = new boolean[Mouse.getButtonCount()];
	private boolean[] previousMouse = new boolean[Mouse.getButtonCount()];
	
	private int mouseWheelDelta;
	
	private boolean isValidKey(int key) {
		return key >= 0 && key < currentKeys.length;
	}
	
	private boolean isValidMouse(int button) {
		return button >= 0 && button < currentMouse.length;
	}
	
	private boolean getButtonCode(int key) {
		if (key == Code.BUTTON_LEFT) {
			return Mouse.isButtonDown(0);
		} else if (key == Code.BUTTON_RIGHT) {
			return Mouse.isButtonDown(1);
		}
		
		return false;
	}
	
	private boolean getKeyCode(int key) {
		switch(key) {
			case Code.KEY_Q:
				return Keyboard.isKeyDown(Keyboard.KEY_Q);
			case Code.KEY_W:
				return Keyboard.isKeyDown(Keyboard.KEY_W);
			case Code.KEY_E:
				return Keyboard.isKeyDown(Keyboard.KEY_E);
			case Code.KEY_R:
				return Keyboard.isKeyDown(Keyboard.KEY_R);
			case Code.KEY_T:
				return Keyboard.isKeyDown(Keyboard.KEY_T);
			case Code.KEY_Y:
				return Keyboard.isKeyDown(Keyboard.KEY_Y);
			case Code.KEY_U:
				return Keyboard.isKeyDown(Keyboard.KEY_U);
			case Code.KEY_I:
				return Keyboard.isKeyDown(Keyboard.KEY_I);
			case Code.KEY_O:
				return Keyboard.isKeyDown(Keyboard.KEY_O);
			case Code.KEY_P:
				return Keyboard.isKeyDown(Keyboard.KEY_P);
			case Code.KEY_A:
				return Keyboard.isKeyDown(Keyboard.KEY_A);
			case Code.KEY_S:
				return Keyboard.isKeyDown(Keyboard.KEY_S);
			case Code.KEY_D:
				return Keyboard.isKeyDown(Keyboard.KEY_D);
			case Code.KEY_F:
				return Keyboard.isKeyDown(Keyboard.KEY_F);
			case Code.KEY_G:
				return Keyboard.isKeyDown(Keyboard.KEY_G);
			case Code.KEY_H:
				return Keyboard.isKeyDown(Keyboard.KEY_H);
			case Code.KEY_J:
				return Keyboard.isKeyDown(Keyboard.KEY_J);
			case Code.KEY_K:
				return Keyboard.isKeyDown(Keyboard.KEY_K);
			case Code.KEY_L:
				return Keyboard.isKeyDown(Keyboard.KEY_L);
			case Code.KEY_SEMICOLON:
				return Keyboard.isKeyDown(Keyboard.KEY_SEMICOLON);
			case Code.KEY_Z:
				return Keyboard.isKeyDown(Keyboard.KEY_Z);
			case Code.KEY_X:
				return Keyboard.isKeyDown(Keyboard.KEY_X);
			case Code.KEY_C:
				return Keyboard.isKeyDown(Keyboard.KEY_C);
			case Code.KEY_V:
				return Keyboard.isKeyDown(Keyboard.KEY_V);
			case Code.KEY_B:
				return Keyboard.isKeyDown(Keyboard.KEY_B);
			case Code.KEY_N:
				return Keyboard.isKeyDown(Keyboard.KEY_N);
			case Code.KEY_M:
				return Keyboard.isKeyDown(Keyboard.KEY_M);
			
			// ----- Mouse Buttons ----- \\
			
			case Code.KEY_LSHIFT:
				return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);
			case Code.KEY_RSHIFT:
				return Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
		}
		
		return false;
	}
	
	@Override
	public void update() {
		System.arraycopy(currentMouse, 0, previousMouse, 0, currentMouse.length);
		System.arraycopy(currentKeys, 0, previousKeys, 0, currentKeys.length);
		
		for (int i = 0; i < currentKeys.length; i++) {
			currentKeys[i] = getKeyCode(i);
		}
		
		for (int i = 0; i < currentMouse.length; i++) {
			currentMouse[i] = getButtonCode(i);
		}
		
		mouseWheelDelta = Mouse.getDWheel();
	}
	
	@Override
	public boolean isKeyDown(int key) {
		return isValidKey(key) && currentKeys[key];
	}
	
	@Override
	public boolean isKeyPressed(int key) {
		return isValidKey(key) && currentKeys[key] && !previousKeys[key];
	}
	
	@Override
	public boolean isKeyReleased(int key) {
		return isValidKey(key) && currentKeys[key] && previousKeys[key];
	}
	
	@Override
	public boolean isMouseDown(int button) {
		return isValidKey(button) && currentMouse[button];
	}
	
	@Override
	public boolean isMousePressed(int button) {
		return isValidMouse(button) && currentMouse[button] && !previousMouse[button];
	}
	
	@Override
	public boolean isMouseReleased(int button) {
		return isValidMouse(button) && currentMouse[button] && previousMouse[button];
	}
	
	@Override
	public float getMouseX() {
		return Mouse.getX();
	}
	
	@Override
	public float getMouseY() {
		return Mouse.getY();
	}
	
	@Override
	public int getMouseWheelDelta() {
		return mouseWheelDelta;
	}
}

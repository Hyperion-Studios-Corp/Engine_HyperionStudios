package HyperionStudios.Core.Input;

import java.util.ArrayList;
import java.util.HashMap;

public class Input {
	
	private static ArrayList<Button> UIButtons = new ArrayList<>();
	
	public static void removeClick(Button button) {
		UIButtons.remove(button);
	}
	
	public static void addClick(Button button) {
		UIButtons.add(button);
	}
	
	public static int GetAxisRaw(String axis) {
		if (axis.equals("Horizontal")) {
			if (isKeyDown(Code.KEY_A)) {
				return -1;
			}
			
			if (isKeyDown(Code.KEY_D)) {
				return 1;
			}
		}
		
		if (axis.equals("Vertical")) {
			if (isKeyDown(Code.KEY_W)) {
				return -1;
			}
			
			if (isKeyDown(Code.KEY_S)) {
				return 1;
			}
		}
		
		return 0;
	}
	
	// ----- Get Bind Buttons ----- \\
	
	private static HashMap<String, Integer> bind = new HashMap<>();
	
	public static void bindAction(String name, int code) {
		bind.put(name, code);
	}
	
	public static Boolean getAction(String name) {
		return isKeyDown(bind.get(name));
	}
	
	// ----- Get Keys and Mouse ----- \\
	
	private static InputBackend backend;
	
	public static void setBackend(InputBackend inputBackend) {
		backend = inputBackend;
	}
	
	public static void update() {
		if (backend != null) {
			backend.update();
			
			ArrayList<Button> after = new ArrayList<>();
			after.addAll(UIButtons);
			
			for (Button b : after) {
				b.onMouseEvent(getMouseX(), getMouseY());
			}
		}
	}
	
	public static boolean isKeyDown(int key) {
		return backend != null && backend.isKeyDown(key);
	}
	
	public static boolean isKeyPressed(int key) {
		return backend != null && backend.isKeyPressed(key);
	}
	
	public static boolean isKeyReleased(int key) {
		return backend != null && backend.isKeyReleased(key);
	}
	
	public static boolean isMouseDown(int button) {
		return backend != null && backend.isMouseDown(button);
	}
	
	public static boolean isMousePressed(int button) {
		return backend != null && backend.isMousePressed(button);
	}
	
	public static boolean isMouseReleased(int button) {
		return backend != null && backend.isMouseReleased(button);
	}
	
	public static float getMouseX() {
		return backend != null ? backend.getMouseX() : 0f;
	}
	
	public static float getMouseY() {
		return backend != null ? backend.getMouseY() : 0f;
	}
	
	public static int getMouseWheelDelta() {
		return backend != null ? backend.getMouseWheelDelta() : 0;
	}
}

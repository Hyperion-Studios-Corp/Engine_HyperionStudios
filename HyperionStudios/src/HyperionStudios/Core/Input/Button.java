package HyperionStudios.Core.Input;

public interface Button {
	
	void onMouseEvent(float x, float y);
	void setOnClick(Runnable onClick);
	
}
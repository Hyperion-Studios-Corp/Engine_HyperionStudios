package HyperionStudios.Core.Screens;

import HyperionStudios.Core.Graphics.JWindow;
import HyperionStudios.Core.Maths.Vector2;
import HyperionStudios.Core.Maths.Vector3;

public class Camera {
	
    public Vector3 position = new Vector3();
    
    public float zoom = 1f;
    
    public Camera() {
        this.position.set(0, 0, 0); 
    }
    
    // --- Getters e Setters ---
    
    public float getWorldX() {
        return position.x;
    }
    
    public float getWorldY() {
        return position.y;
    }
    
    public float getViewPortWidth() {
        return JWindow.getWidth() / zoom;
    }
    
    public float getViewPortHeight() {
        return JWindow.getHeight() / zoom;
    }
    
    public float getZoom() {
        return zoom;
    }
    
    public void setZoom(float zoom) {
        this.zoom = zoom;
    }
    
    public void setPosition(Vector2 vector) {
        this.position = new Vector3(vector);
    }
    
    public void setPosition(Vector3 vector) {
        this.position = vector;
    }
    
    public void setPosition(float x, float y, float z) {
        this.position.set(x, y, z);
    }
}
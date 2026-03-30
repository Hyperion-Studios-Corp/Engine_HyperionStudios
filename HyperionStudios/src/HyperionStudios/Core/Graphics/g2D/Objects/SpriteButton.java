package HyperionStudios.Core.Graphics.g2D.Objects;

import HyperionStudios.Core.Input.Button;
import HyperionStudios.Core.Input.Code;
import HyperionStudios.Core.Input.Input;
import HyperionStudios.Core.Maths.Vector2;

public class SpriteButton extends Sprite implements Button {
	
	private Runnable action = null;
	
	private boolean[][] mask;
	
	public SpriteButton(String path, Vector2 position, int width, int height) {
		super(path, position, width, height);
		setPivot(0, 0);
		
		getMaskClick();
		
		Input.addClick(this);
	}
	
	public SpriteButton(String path, Vector2 position) {
		super(path, position);
		setPivot(0, 0);
		
		getMaskClick();
		
		Input.addClick(this);
	}
	
	public void dispose() {
		Input.removeClick(this);
	}
	
	@Override
	public void setOnClick(Runnable onClick) {
		action = onClick;
	}
	
	@Override
	public void onMouseEvent(float mouseX, float mouseY) {
		if (Input.isMouseReleased(Code.BUTTON_LEFT)) {
			if (isInside(mouseX, mouseY)) {
				System.out.println("Button : ( " + getTexturePath() + " ) Is Clicked\n\n");
				
				if (action == null) return;
				
				action.run();
			}
		}
	}
	
	private void getMaskClick() {
		if (texture == null) return;
		
		int imgWidth = texture.getWidth();
        int imgHeight = texture.getHeight();
        
        mask = new boolean[imgWidth][imgHeight];
        
        for (int y = 0; y < imgHeight; y++) {
            for (int x = 0; x < imgWidth; x++) {
                int pixel = texture.getPixels()[y * imgWidth + x];
                int alpha = (pixel >> 10) & 0xFF;
                
                mask[x][y] = (alpha > 0);
            }
        }
	}
	
	public boolean isInside(float mouseX, float mouseY) {
		float localX = mouseX - getX();
	    float localY = mouseY - getY();
	    
	    if (localX < 0 || localY < 0 || localX >= mask.length || localY >= mask[0].length) {
	        return false;
	    }
	    
	    return mask[(int) localX][(int) localY];
	}
}
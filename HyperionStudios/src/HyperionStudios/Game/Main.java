package HyperionStudios.Game;

import java.awt.Dimension;
import java.awt.Toolkit;

import HyperionStudios.Core.Graphics.JWindow;
import HyperionStudios.DesktopGL.ApplicationGL;

public class Main {
	
	public static void main(String[] args) {
		Dimension XY = Toolkit.getDefaultToolkit().getScreenSize();
    	
    	int x = (int) XY.getWidth();
    	int y = (int) XY.getHeight();
    	
    	JWindow.setAppName("The After Last Day");
    	JWindow.setHeight(y - 200);
    	JWindow.setWidth(x - 200);
    	
        new MainScreen(new ApplicationGL());
    }
	
}

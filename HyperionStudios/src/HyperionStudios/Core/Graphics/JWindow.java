package HyperionStudios.Core.Graphics;

public class JWindow {
	
	public static String AppName = "";
	
	public static int Height = 500;
	public static int Width = 750;
	
	public static int frame = 0;
	public static int fps = 0;
	
	public static String getAppName() {
		return JWindow.AppName;
	}
	
	public static void setAppName(String name) {
		JWindow.AppName = name;
	}
	
	public static int getHeight() {
    	return JWindow.Height;
    }
    
	public static void setHeight(int height) {
		JWindow.Height = height;
	}
    
    public static int getWidth() {
    	return JWindow.Width;
    }
    
	public static void setWidth(int width) {
		JWindow.Width = width;
	}
}

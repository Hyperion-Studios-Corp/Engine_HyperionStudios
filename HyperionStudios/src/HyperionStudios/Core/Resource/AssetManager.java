package HyperionStudios.Core.Resource;

import org.w3c.dom.Document;

import HyperionStudios.Core.Graphics.Texture;
import HyperionStudios.Core.Graphics.g3D.Models.ObjModel;

public class AssetManager {
	
	private static AssetBackend backend;
	
	public static String getFile(String path) {
	    int slash = path.lastIndexOf("/");
	    int dot = path.lastIndexOf(".");
	    
	    if (slash == -1) slash = -1;
	    if (dot == -1 || dot < slash) dot = path.length();
	    
	    return path.substring(slash + 1, dot);
	}
	
	public static String getFolder(String path) {
        int index = path.lastIndexOf("/");
        
        if (index == -1) return "";
        
        return path.substring(0, index);
    }
	
	public static void setBackend(AssetBackend assetBackend) {
		backend = assetBackend;
	}
	
	public static Document getXml(String path) throws Exception {
		if (backend != null) {
			return backend.getXml(path);
		}
		
		return null;
	}
	
	public static Texture getTexture(String path) {
		if (backend != null) {
			return backend.getTexture(path);
		}
		
		return null;
	}
	
	public static ObjModel loadOBJ(String path) {
		if (backend != null) {
			return backend.loadOBJ(path);
		}
		
		return null;
	}
	
	public static void dispose() {
		if (backend != null) {
			backend.dispose();
		}
	}
}

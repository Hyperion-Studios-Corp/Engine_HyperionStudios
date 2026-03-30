package HyperionStudios.Core.Resource;

import org.w3c.dom.Document;

import HyperionStudios.Core.Graphics.Texture;
import HyperionStudios.Core.Graphics.g3D.Models.ObjModel;

public interface AssetBackend {
	
	Document getXml(String path) throws Exception;
	
	Texture getTexture(String path);
	
	ObjModel loadOBJ(String path);
	
	void dispose();
}

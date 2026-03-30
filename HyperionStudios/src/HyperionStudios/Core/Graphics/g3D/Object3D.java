package HyperionStudios.Core.Graphics.g3D;

import HyperionStudios.Core.Graphics.Texture;
import HyperionStudios.Core.Graphics.g3D.Models.ObjModel;
import HyperionStudios.Core.Maths.Vector2;
import HyperionStudios.Core.Maths.Vector3;
import HyperionStudios.Core.Resource.AssetManager;

public class Object3D {
	
	protected ObjModel model;
	protected Texture texture;
	
	protected Vector3 rotation;
	protected Vector3 position;
	protected Vector3 scale;
	
	protected boolean visible;
	
	protected float alpha;
	
	public Object3D(String path, String texture) {
		this.texture = AssetManager.getTexture(texture);
		
		this.visible = true;
		this.alpha = 1f;
		
		this.model = AssetManager.loadOBJ(path);
	}
	
	public Vector3 getPosition() {
		return position;
	}
	
	public void setPosition(Vector2 position) {
		this.position.x = position.x;
		this.position.y = position.y;
	}
	
	public void setPosition(Vector3 position) {
		this.position = position;
	}
	
	public Vector3 getRotation() {
		return rotation;
	}
	
	public Vector3 getScale() {
		return scale;
	}
	
	public void setScale(Vector3 scale) {
		this.scale = scale;
	}
	
	public ObjModel getModel() {
		return model;
	}
	
	public int getTextureID() {
		return texture.getTextureID();
	}
}

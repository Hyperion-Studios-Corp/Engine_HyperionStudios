package HyperionStudios.Game.Entitys;

import HyperionStudios.Core.Entitys.Direction;
import HyperionStudios.Core.Entitys.State;
import HyperionStudios.Core.Graphics.JWindow;
import HyperionStudios.Core.Graphics.g3D.Object3D;
import HyperionStudios.Core.Maths.*;

public abstract class Entity extends Object3D {
	
	protected Vector2 movement;
	
	protected Direction direction;
	protected State state;
	
	protected float speed;
	protected float time;
	
	public Entity(String pathObj, String pathTexture) {
		super(pathObj, pathTexture);
		
		float y = (JWindow.getHeight() / 2);
		float x = (JWindow.getWidth() / 2);
		
		this.position = new Vector3(x, y, 100f);
		this.rotation = new Vector3(130f, 180f, 0f);
		this.scale = new Vector3(32f, 32f, 32f);
		
		this.movement = new Vector2();
		
		direction = Direction.DOWN;
		state = State.IDLE;
	}
	
	public void move(float delta) {
		if (movement.x == 0 && movement.y == 0) return;
		
		float angle = (float) Math.toRadians(rotation.y);
		
		float fowardX = (float) -Math.sin(angle);
		float fowardY = (float) Math.cos(angle);
		
		float rightX = (float) Math.cos(angle);
		float rightY = (float) Math.sin(angle);
		
		Vector2 dir = new Vector2(0, 0);
		
		dir.x += fowardX * movement.y;
		dir.y += fowardY * movement.y;
		
		dir.x += rightX * movement.x;
		dir.y += rightY * movement.x;
		
		dir.normalize();
		dir.mul(speed * delta);
		
		position.add(dir);
	}
	
	public abstract void animation(float delta);
	public abstract void update(float delta);
}

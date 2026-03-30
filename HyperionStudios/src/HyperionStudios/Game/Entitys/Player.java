package HyperionStudios.Game.Entitys;

import HyperionStudios.Core.Entitys.Direction;
import HyperionStudios.Core.Entitys.State;
import HyperionStudios.Core.Graphics.JWindow;
import HyperionStudios.Core.Input.Input;
import HyperionStudios.Core.Maths.Vector2;
import HyperionStudios.Core.Maths.Vector3;

public class Player extends Entity {
	
	public Player(String pathObj, String pathTexture) {
		super(pathObj, pathTexture);
		
		speed = 50.0f;
	}
	
	@Override
	public void update(float delta) {
		movement = Vector2.ZERO;
		
		movement.x = Input.GetAxisRaw("Horizontal");
		movement.y = Input.GetAxisRaw("Vertical");
		
		if (movement.length() > 0 && state == State.IDLE) {
			state = State.WALK;
		} else if (movement.length() < 0) {
			state = State.IDLE;
		}
		
		if (state == State.WALK) {
			if (Input.getAction("Run")) {
				state = State.RUN;
				speed *= 2.5f;
			}
		} else if (state == State.RUN) {
			if (!Input.getAction("Run")) {
				state = State.WALK;
				speed /= 2.5f;
			}
		}
		
		direction = Direction.getDirection(movement);
		
		rotation.y = new Vector3((JWindow.getWidth() / 2), (JWindow.getHeight() / 2), 0).angle(new Vector2(Input.getMouseX(), Input.getMouseY()));
		
		move(delta);
	}
	
	@Override
	public void animation(float delta) {
		
	}
}

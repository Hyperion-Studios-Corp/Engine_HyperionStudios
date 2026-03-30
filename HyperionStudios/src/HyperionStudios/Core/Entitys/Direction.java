package HyperionStudios.Core.Entitys;

import HyperionStudios.Core.Maths.Vector2;

public enum Direction {
	UP,           // Para Cima
	DOWN,         // Para Baixo
	LEFT,         // Para Esquerda
	RIGHT,        // Para Direita
	UP_LEFT,      // Para Cima e Esquerda
	UP_RIGHT,     // Para Cima e Direita
	DOWN_LEFT,    // Para Baixo e Esquerda
	DOWN_RIGHT;   // Para Baixo e Direita
	
	public static Direction getDirection(Vector2 movement) {
		if (movement.x == 0 && movement.y < 0) { return Direction.UP; }
		if (movement.x == 0 && movement.y > 0) { return Direction.DOWN; }
		if (movement.x < 0 && movement.y == 0) { return Direction.LEFT; }
		if (movement.x > 0 && movement.y == 0) { return Direction.RIGHT; }
		if (movement.x < 0 && movement.y < 0) { return Direction.UP_LEFT; }
		if (movement.x > 0 && movement.y < 0) { return Direction.UP_RIGHT; }
		if (movement.x < 0 && movement.y > 0) { return Direction.DOWN_LEFT; }
		if (movement.x > 0 && movement.y > 0) { return Direction.DOWN_RIGHT; }
		
		return Direction.DOWN;
	}
}
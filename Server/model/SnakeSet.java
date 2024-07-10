package proyectop3.Server.model;

import java.awt.Color;
import java.util.HashSet;
import java.util.Iterator;

import proyectop3.Server.util.GameRandom;
import proyectop3.Server.util.TileType;


public class SnakeSet {
	private HashSet<Snake> snakes = new HashSet<Snake>();
	private TileGrid grid;
	private Apple apple;
	
	public SnakeSet(TileGrid grid, Apple apple){
		this.grid = grid;
		this.apple = apple;
	}
	
	public void addSnake(Snake snake) {
		snakes.add(snake);
	}
	
	public Snake addRandomSnake() {
		Tile1 tile;
		do {
			tile = GameRandom.getRandomElement(grid.getTilesSet());
		}
		while(tile.getType()!=TileType.BACKGROUND);
		Snake snake = new Snake(tile, grid, apple, Color.CYAN, Color.GREEN);
		this.addSnake(snake);
		return snake;
	}
	

	public void removeSnake(Snake snake) {
		snakes.remove(snake);
	}
	
	public HashSet<Snake> getSnakeList(){
		return  this.snakes;
	}
	
	public void moveSnakes() {
		for(Snake snake:snakes) {
			snake.move();
		}
	}
	
	public void calculateSnakes() {
		for(Snake snake:snakes) {
			snake.calculateMoveStep();
		}
	}
	
	public void checkSnake() {		
		for (Iterator<Snake> i = snakes.iterator(); i.hasNext();) {
		    Snake element = i.next();
//		    System.out.println(element.toString());
		    if (element.isDead()) {
		        i.remove();
		    }
		}
	}
	
	
	
	public void reset() {
		this.snakes.clear();
	}
}

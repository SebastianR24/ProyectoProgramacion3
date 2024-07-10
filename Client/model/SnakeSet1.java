package proyectop3.Client.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class SnakeSet1 {
	private static HashSet<Snake1> snakes = new HashSet<>();
	
	private SnakeSet1() {
		
	}
	
	public static void addSnake(Snake1 snake) {
		snakes.add(snake);
	}
	
	public static int countSnake() {
		return snakes.size();
	}
	
	public static void removeSnake(Snake1 snake) {
		snakes.remove(snake);
	}
	
	public static Set<Snake1> getSnakeList(){
		return  SnakeSet1.snakes;
	}
	
	public static void moveSnakes() {
		for(Snake1 snake:snakes) {
			snake.move();
		}
	}
	
	public static void calculateSnakes() {
		for(Snake1 snake:snakes) {
			snake.calculateMoveStep();
		}
	}
	
	public static void checkSnake() {		
		for (Iterator<Snake1> i = snakes.iterator(); i.hasNext();) {
		    Snake1 element = i.next();
		    if (element.isDead()) {
		        i.remove();
		    }
		}
	}
	
	public static void reset() {
		SnakeSet1.snakes.clear();
	}
}
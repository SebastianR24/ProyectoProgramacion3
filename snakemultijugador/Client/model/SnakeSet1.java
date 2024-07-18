package snakemultijugador.Client.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class SnakeSet1 {
	private static HashSet<Snake1> snakes = new HashSet<>();
	
	private SnakeSet1() {
		
	}
	//añade la culebrita
	public static void addSnake(Snake1 snake) {
		snakes.add(snake);
	}
	//Cuenta las culebritas en el tablero
	public static int countSnake() {
		return snakes.size();
	}
	//Quita las culebritas del tablero al morir
	public static void removeSnake(Snake1 snake) {
		snakes.remove(snake);
	}
	//lista de las culebritas
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
	//Checkea que las culebritas no hayan perdido
	public static void checkSnake() {		
		for (Iterator<Snake1> i = snakes.iterator(); i.hasNext();) {
		    Snake1 element = i.next();
		    if (element.isDead()) {
		        i.remove();
		    }
		}
	}
	//Resetear culebritas
	public static void reset() {
		SnakeSet1.snakes.clear();
	}
}

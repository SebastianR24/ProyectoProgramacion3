package snakemultijugador.Client.model;

import java.awt.Color;
import java.util.HashSet;

import snakemultijugador.Client.util.TileType;

public class Apple1 {
	private static HashSet<Tile1> apples = new HashSet<Tile1>();
	
	//Genera las manzanas en lugares aleatorios del tablero
	public static void generate(int num) {
		while(apples.size() < num) {
			int appleX = (int)(Math.random()*(TileGrid1.getColumns() - 1));
			int appleY = (int)(Math.random()*(TileGrid1.getRows() - 1));
			if (TileGrid1.getTile(appleX,appleY).getType() == TileType.BACKGROUND){
				Apple1.addApple(TileGrid1.getTile(appleX,appleY));
				
			
			}
		}
	}
	//metodo que elimina las manzanas al agarrarlas
	public static void removeApple(Tile1 apple) {
		apples.remove(apple);
	}
	//metdoo que añade las manzanas al tablero
	public static void addApple(Tile1 apple) {
		apple.setType(TileType.FOOD, Color.RED);
		apples.add(apple);
	}
	//getter de las manzanas en un hashset
	public static HashSet<Tile1> getApples(){
		return Apple1.apples;
	}
	//reset de las manzanas
	public static void reset() {
		Apple1.apples.clear();
	}
}

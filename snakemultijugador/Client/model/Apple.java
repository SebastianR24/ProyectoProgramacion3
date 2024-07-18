package snakemultijugador.Client.model;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import snakemultijugador.Client.util.TileType;

public class Apple {
	private HashSet<Tile1> appleSet = new HashSet<>();
	private TileGrid grid;
	private int numberApples;
	
	public Apple(TileGrid tileGrid) {
		this.grid = tileGrid;
	}
	
	public void setNumberApples(int num ) {
		numberApples = num;
	}
	
	public void generate() {
		while(appleSet.size() < numberApples) {
			int appleX = (int)(Math.random()*(TileGrid1.getColumns() - 1));
			int appleY = (int)(Math.random()*(TileGrid1.getRows() - 1));
			if (TileGrid1.getTile(appleX,appleY).getType() == TileType.BACKGROUND){
				this.addApple(TileGrid1.getTile(appleX,appleY));
				
			
			}
		}
	}
	
	public void removeApple(Tile1 apple) {
		appleSet.remove(apple);
	}
	
	public void addApple(Tile1 apple) {
		apple.setType(TileType.FOOD, Color.RED);
		appleSet.add(apple);
	}
	
	public Set<Tile1> getApples(){
		return this.appleSet;
	}
	
	public void reset() {
		this.appleSet.clear();
	}
}

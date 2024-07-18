package snakemultijugador.Client.model;

import java.util.HashSet;

import snakemultijugador.Client.util.GameRandom;
import snakemultijugador.Client.util.TileType;

//Tamaño de las tiles del tablero
public class TileGrid1 {
	private static Tile1[][] tiles;
	private static int columns, rows, unitSize;
	private HashSet <Tile1> freeSet = new HashSet<Tile1>();
	public TileGrid1(int columns, int rows, int sizeTile){
		TileGrid1.tiles = new Tile1[columns][rows];
		TileGrid1.columns = columns;
		TileGrid1.rows = rows;
		TileGrid1.unitSize = sizeTile;
		
		for (int x = 0; x < columns;x++)
			for (int y = 0; y < rows; y++) {
				TileGrid1.tiles[x][y] = new Tile1(new int[]{x,y},new int[]{sizeTile,sizeTile}) ;
				freeSet.add(TileGrid1.getTile(x, y));
			}

	}
	
	public TileGrid1() {
		for (int x = 0; x < columns;x++)
			for (int y = 0; y < rows; y++)
				freeSet.add(TileGrid1.getTile(x, y));
	}
	
	public static Tile1[][] getTiles() {
		return TileGrid1.tiles;
	}
	
	public static Tile1 getTile(int x, int y) {
		return TileGrid1.tiles[x][y];
	}
	
	public static void setTile(int x, int y, TileType type) {
		TileGrid1.tiles[x][y].setType(type);
	}
	
	public static int getRows() {
		return TileGrid1.rows;
	}
	
	public static int getColumns() {
		return TileGrid1.columns;
	}
	
	public static int getUnitSize() {
		return TileGrid1.unitSize;
	}
	
	public HashSet<Tile1> getFreeSet(){
		return this.freeSet;
	}
	
	public Tile1 getRandomInFreeSet() {
		Tile1 tile = GameRandom.getRandomElement(this.freeSet);
		popFreeSetElement(tile);
		return tile;
	}
	
	public void popFreeSetElement(Tile1 tile) {
		this.freeSet.remove(tile);
	}
	
	public void resetFreeSet() {
		this.freeSet.clear();
		for (int x = 0; x < columns;x++)
			for (int y = 0; y < rows; y++)
				freeSet.add(TileGrid1.getTile(x, y));
	}
	
	public static void reset() {
		for (int x = 0; x < columns;x++)
			for (int y = 0; y < rows; y++) {
				TileGrid1.tiles[x][y].reset();
			}
	}
	

	
}

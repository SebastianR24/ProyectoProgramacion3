package snakemultijugador.Client.model;

import java.util.HashSet;
import java.util.Set;

import snakemultijugador.Client.util.TileType;

public class TileGrid {
	private Tile1[][] tiles;
	private int columns;
	private int rows;
	private int unitSize;
	private HashSet<Tile1> tilesSet;
	public TileGrid(int columns, int rows, int sizeTile){
		this.tiles = new Tile1[columns][rows];
		this.columns = columns;
		this.rows = rows;
		this.unitSize = sizeTile;
		this.tilesSet = new HashSet<>();
		for (int x = 0; x < columns;x++)
			for (int y = 0; y < rows; y++) {
				this.tiles[x][y] = new Tile1(new int[]{x,y},new int[]{sizeTile,sizeTile}) ;
				tilesSet.add(this.tiles[x][y]);
			}

	}
	
	
	public Tile1[][] getClone(){
		Tile1 [][] result = new Tile1[this.getColumns()][this.getRows()];
		for (int x = 0; x < this.getColumns();x++)
			for (int y = 0; y < this.getRows(); y++)
			{
				Tile1 tile = this.getTile(x, y);
				result[x][y] = new Tile1(tile.getIndex(),tile.getSize());
				result[x][y].setType(tile.getType(), tile.getColor());
			}
		return result;
	}
	
	public Tile1[][] getNew(){
		Tile1 [][] result = new Tile1[this.getColumns()][this.getRows()];
		for (int x = 0; x < this.getColumns();x++)
			for (int y = 0; y < this.getRows(); y++)
				result[x][y] = new Tile1( new int[] {x,y},new int[]{this.unitSize,this.unitSize});
		return result;
	}
	
	

	
	public Tile1[][] getTiles() {
		return this.tiles;
	}
	
	public Tile1 getTile(int x, int y) {
		return this.tiles[x][y];
	}
	
	public void setTile(int x, int y, TileType type) {
		this.tiles[x][y].setType(type);
	}
	
	public int getRows() {
		return this.rows;
	}
	
	public int getColumns() {
		return this.columns;
	}
	

	public Set<Tile1> getTilesSet(){
		return this.tilesSet;
	}

	
	public void reset() {
		for (int x = 0; x < columns;x++)
			for (int y = 0; y < rows; y++) {
				this.tiles[x][y].reset();
			}
	}
	

	
}

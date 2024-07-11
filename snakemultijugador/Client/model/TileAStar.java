package snakemultijugador.Client.model;

import java.util.HashSet;


public class TileAStar {
	public double f;
	public double g;
	public double h;
	public int i;
	public int j;


	public HashSet <TileAStar> neighbors;
	public TileAStar previous;

	public Tile1 tile1;

	public TileAStar(Tile1 tile1){
		this.tile1 = tile1;
		this.i = tile1.getIndexX();
		this.j = tile1.getIndexY();
		this.f = 0;
		this.g = 0;
		this.h = 0;

		this.neighbors =  new HashSet<TileAStar>();
		this.previous = null;
	}

	public void reset(){
		this.f = 0;
		this.g = 0;
		this.h = 0;
		this.previous = null;
	}




	public void addNeighbors(TileAStar[][] tiles){
		if (this.i < tiles.length -1){
			this.neighbors.add(tiles[i+1][j]);
		}

		if (this.i > 0){
			this.neighbors.add(tiles[i-1][j]);
		}

		if (this.j < tiles[0].length - 1){
			this.neighbors.add(tiles[i][j+1]);
		}

		if (this.j > 0){
			this.neighbors.add(tiles[i][j-1]);
		}
	}
}


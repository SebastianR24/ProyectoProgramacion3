package snakemultijugador.Client.model;

import java.util.*;

import snakemultijugador.Client.util.GameRandom;
import snakemultijugador.Client.util.TileType;



public class AStar {
	private HashSet <TileAStar1> openSet,closedSet ;
	private ArrayList <TileAStar1> path;
	
	private TileAStar1 current; 
	private TileAStar1[][] map;
	
	private Tile1 head, end;
	private Snake1 snake;
	private int deep;
	private int xResult, yResult;
	//constructor del Astar que hace crecer a la culebrita
	AStar(Snake1 snake, int deep){
		this.snake = snake;
		this.head = snake.getHead();
		this.openSet = new HashSet<TileAStar1>();
		this.closedSet = new HashSet<TileAStar1>();
		this.path = new ArrayList<TileAStar1>();
		this.map = new TileAStar1[TileGrid1.getColumns()][TileGrid1.getRows()];
		this.deep = deep;
		for (int x = 0; x < map.length;x++){
			for (int y = 0; y < map[0].length;y ++){
				map[x][y] = new TileAStar1(TileGrid1.getTile(x, y));
			}
		}
		
		for (int x = 0; x < map.length; x++){
			for (int y = 0; y < map[0].length; y ++){
				map[x][y].addNeighbors(map);
			}
		}
		
		this.end = null;
				
	}
	
	
	public void getPath() {
		this.findApple();
		this.head = this.snake.getHead();
		openSet.clear();
		closedSet.clear();
		path.clear();
		openSet.add(map[head.getIndexX()][head.getIndexY()]);

		for (int i =0; i < deep; i++){
			if(openSet.size() > 0){
				current =  openSet.iterator().next();
				for (TileAStar1 element : openSet )
					if(element.f < current.f)
						current  = element;
			}

			if (current.tile1 == end){	
				break;
			}

			openSet.remove(current);
			closedSet.add(current);

			for (TileAStar1 neighbor: current.neighbors){			
				if (!closedSet.contains(neighbor) && neighbor.tile1.getType()!=TileType.BODY){
					double tempG = current.g + 1;
					if (openSet.contains(neighbor)){
						if(tempG  < neighbor.g)
							neighbor.g = tempG;				
					}
	
					else {
						neighbor.g = tempG;
						openSet.add(neighbor);
					}

					neighbor.h = heuristic(neighbor);
					neighbor.f = neighbor.g + neighbor.h;
					neighbor.previous = current;
				}
			}

			path.clear();
			while(current.previous!=null){
				path.add(current);
				current = current.previous;
			}
		}
		
		if(path.size() == 0 ){
			xResult = current.i;
			yResult = current.j;
		}
		else{
			xResult = path.get(path.size() -1).i;
			yResult = path.get(path.size() -1).j;
		}
		
		
		resetTile();

	}
	
	private void findApple() {
		if(this.end == null) {
			this.end = GameRandom.getRandomElement(Apple1.getApples());
			return;
		}
		
		if(!Apple1.getApples().contains(end)){
			end = GameRandom.getRandomElement(Apple1.getApples());
		} 
	}
	
	public Tile1 getResult() {
		return TileGrid1.getTile(xResult, yResult);
	}
	
	

	private double heuristic(TileAStar1 neighbor){
		double d = Math.pow(neighbor.i - end.getIndexX(), 2) + Math.pow(neighbor.j - end.getIndexY(), 2 ) ;
		return Math.pow(d,2) ;
	}
	
	
	private void resetTile(){
		for (int x = 0; x < map.length;x++){
			for (int y = 0; y < map[0].length;y ++){
				map[x][y].reset();
			}
		}
	}
	

}

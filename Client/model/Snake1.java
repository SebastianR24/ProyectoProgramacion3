package proyectop3.Client.model;

import java.awt.Color;
import java.util.LinkedList;

import proyectop3.Client.util.SnakeDirection;
import proyectop3.Client.util.TileType;
import proyectop3.Server.model.Tile1;

public class Snake1 {
	private LinkedList<Tile1> body;
	private Tile1 head, nextHead;
	private Color colorBody, colorHead;
	private SnakeDirection direction;
	private boolean dead;
	private boolean isBot;
	private AStar brain;
	private String name;

	public Snake1(int x, int y, Color colorHead, Color colorBody) {
		this.body = new LinkedList<Tile1>();
		this.head = TileGrid1.getTile(x, y);
		this.colorBody = colorBody;
		this.colorHead = colorHead;
		this.direction = SnakeDirection.DOWN;
		this.dead = false;
		this.isBot = true;
		this.brain = new AStar(this, 10);
	}

	public Snake1(int x, int y, Color colorHead, Color colorBody, int deep) {
		this.body = new LinkedList<Tile1>();
		this.head = TileGrid1.getTile(x, y);
		this.colorBody = colorBody;
		this.colorHead = colorHead;
		this.direction = SnakeDirection.DOWN;
		this.dead = false;
		this.isBot = true;
		this.brain = new AStar(this, deep);
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void changeDirection(SnakeDirection direction) {
		if (this.isDead())
			return;

		if (this.direction == SnakeDirection.DOWN && direction == SnakeDirection.UP) {

		}

		else if (this.direction == SnakeDirection.UP && direction == SnakeDirection.DOWN) {

		}

		else if (this.direction == SnakeDirection.LEFT && direction == SnakeDirection.RIGHT) {

		}

		else if (this.direction == SnakeDirection.RIGHT && direction == SnakeDirection.LEFT) {

		} else {
			this.direction = direction;
		}

	}

	public void calculateMoveStep() {
		if (this.isDead())
			return;

		if (this.isBot) {
			this.brain.getPath();
			this.nextHead = this.brain.getResult();
			return;
		}

		int[] curHead = this.head.getIndex();

		switch (this.direction) {
			case UP:
				if (curHead[1] == 0) {
					this.nextHead = TileGrid1.getTile(curHead[0], TileGrid1.getRows() - 1);
					break;
				}
				this.nextHead = TileGrid1.getTile(curHead[0], curHead[1] - 1);
				break;
			case DOWN:
				if (curHead[1] == TileGrid1.getRows() - 1) {
					this.nextHead = TileGrid1.getTile(curHead[0], 0);
					break;
				}
				this.nextHead = TileGrid1.getTile(curHead[0], curHead[1] + 1);
				break;
			case LEFT:
				if (curHead[0] == 0) {
					this.nextHead = TileGrid1.getTile(TileGrid1.getColumns() - 1, curHead[1]);
					break;
				}
				this.nextHead = TileGrid1.getTile(curHead[0] - 1, curHead[1]);
				break;
			case RIGHT:
				if (curHead[0] == TileGrid1.getColumns() - 1) {
					this.nextHead = TileGrid1.getTile(0, curHead[1]);
					break;
				}
				this.nextHead = TileGrid1.getTile(curHead[0] + 1, curHead[1]);
				break;

			default:
				break;
		}
	}

	public void move() {
		if (this.isDead())
			return;

		this.body.addLast(this.head);

		if (this.nextHead.getType() == TileType.FOOD) {
			this.body.addFirst(this.nextHead);
			Apple1.removeApple(this.nextHead);
		}

		if (this.nextHead.getType() == TileType.BODY || this.nextHead.getType() == TileType.HEAD) {
			this.dead();
			return;
		}

		this.body.getLast().setType(TileType.BODY, this.colorBody);
		this.body.getFirst().setType(TileType.BACKGROUND);
		this.body.removeFirst();
		this.head = this.nextHead;
		this.head.setType(TileType.HEAD, this.colorHead);
	}

	public Tile1 getHead() {
		return this.head;
	}

	public int getSizeBody() {
		return body.size();
	}

	public void activePlayer() {
		this.isBot = false;
	}

	private void dead() {
		this.dead = true;
		for (Tile1 tile : body) {
			Apple1.addApple(tile);
		}
	}

	public boolean isDead() {
		return this.dead;
	}
}

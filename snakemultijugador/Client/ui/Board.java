package snakemultijugador.Client.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.nio.ByteBuffer;

import snakemultijugador.Client.model.Apple1;
import snakemultijugador.Client.model.Snake1;
import snakemultijugador.Client.model.SnakeSet1;
import snakemultijugador.Client.model.Tile1;
import snakemultijugador.Client.model.TileGrid1;
import snakemultijugador.Client.service.Client;
import snakemultijugador.Client.util.TileType;

public class Board extends MenuWidget {
	private static TileGrid1 grid;
	private static Snake1 player;
	private static boolean connected;
	private static Board instance;
	private static int numberApple = 100;
	private static long startedAt = System.currentTimeMillis();
	private static Panel root;
	private Board(Panel root) {
		super(root, 0, 0);
		Board.root = root;
	}

	public static void init(Panel root) {
		if (instance == null) {
			instance = new Board(root);
		}
	}
	
	public static void setSizeBoard( int columns, int rows) {
		Board.grid = new TileGrid1(columns,rows, Math.min(Board.root.getWidth()/columns, Board.root.getHeight()/rows));
		Board.grid.popFreeSetElement(TileGrid1.getTile(0, 0));
		Board.player = new Snake1(0, 0, Color.BLUE, Color.GREEN);
		Board.player.activePlayer();
		SnakeSet1.addSnake(Board.player);
		Board.connected = false;
	}
	
	public static void setSizeGrid(int columns, int rows) {
		Board.grid = new TileGrid1(columns,rows, Math.min(Board.root.getWidth()/columns, Board.root.getHeight()/rows));
		Board.grid.popFreeSetElement(TileGrid1.getTile(0, 0));
	}
	

	public static Board getInstance() {

		if (instance == null) {
			throw new AssertionError("debes hacer el llamado primero");
		}
		

		return instance;
	}

	public static void addBot(int number, int deep) {
		for (int i = 0; i < number; i++) {
			Tile1 tile = grid.getRandomInFreeSet();
			Snake1 snake = new Snake1(tile.getIndexX(), tile.getIndexY(),
			new Color((int) (Math.random() * 0x1000000)), new Color((int) (Math.random() * 0x1000000)), deep);
			SnakeSet1.addSnake(snake);
		}
	}
	public static boolean isConnected() {
		return Board.connected;
	}

	@Override
	public void update() {
		Board.numberApple = MenuSetting.getWidgets().get(0).getEnteredNumber();

		if (Board.connected) {
			ByteBuffer res = ByteBuffer.wrap(Client.sendMessage("GET Actualizar_Tablero"));
			int cnt = 0;
			while (true) {
				if (res.get((cnt) * 8 + 7) != (byte) 1) {
					break;
				}
				TileGrid1.getTile(res.get(cnt * 8 + 1) & 0xFF, res.get(cnt * 8 + 2) & 0xFF)
						.setType(TileType.values()[res.get(cnt * 8)], new Color(res.getInt(cnt * 8 + 3)));
				cnt++;
			}

			return;
		}

		Apple1.generate(Board.numberApple);
		SnakeSet1.calculateSnakes();

		if (System.currentTimeMillis() - startedAt < 1000 / MenuSetting.getWidgets().get(3).getEnteredNumber()) {
			return;
		}

		startedAt = System.currentTimeMillis();

		SnakeSet1.moveSnakes();
		SnakeSet1.checkSnake();
	}

	public void draw(Graphics g) {
		int spaceWidth = (this.getRoot().getWidth() - TileGrid1.getColumns()*TileGrid1.getUnitSize())/2;
		int spaceHeight = (this.getRoot().getHeight() - TileGrid1.getRows()*TileGrid1.getUnitSize())/2;
		
		for (int x = 0; x < TileGrid1.getColumns(); x++){
			for (int y = 0; y < TileGrid1.getRows(); y++) {
				Tile1 tile = TileGrid1.getTile(x, y);
				g.setColor(tile.getColor());
				g.fillRect(tile.getStepX() + spaceWidth, tile.getStepY()+spaceHeight, tile.getSize()[0] - 1, tile.getSize()[1] - 1);
			}
		
		}
		
		for (Snake1 snake : SnakeSet1.getSnakeList()) {
			if (snake.getName() == null){
				continue;
			}
			g.setColor(Color.WHITE);
			g.setFont(new Font("Ink Free", Font.BOLD, 15));
			g.drawString(snake.getName(), snake.getHead().getStepX() + 10, snake.getHead().getStepY() + 10);
		}
	}

	public static TileGrid1 getGrid() {
		return Board.grid;
	}

	public static void reset() {
		if (Board.connected) {
			Board.connected = false;
			try {
				Client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Apple1.reset();
		SnakeSet1.reset();
		TileGrid1.reset();
		Board.grid.resetFreeSet();

	}

	public static Snake1 getPlayerSnake() {
		return Board.player;
	}

	public static int connectServer(String host, int port) {
		if (Client.start(host, port) == null) {
			return -1;
		}
		Board.connected = true;
		return 0;
	}

	public static void setNumberApple(int n) {
		Board.numberApple = n;
	}

	public String toString() {
		return "this is board";
	}

}

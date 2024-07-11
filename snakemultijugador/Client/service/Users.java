package snakemultijugador.Client.service;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.HashMap;

import snakemultijugador.Client.model.Board;
import snakemultijugador.Client.model.Snake;
import snakemultijugador.Client.model.Tile1;

public class Users {
	private static HashMap<SocketChannel, Tile1[][]> userBoard = new HashMap<>();
	private static HashMap<SocketChannel, Board> serverBoard = new HashMap<>();
	private static HashMap<SocketChannel, Snake> userSnake = new HashMap<>();
	private static HashMap<String, String> userAccount = new HashMap<>();
	
	private Users() {

	}
	
	
	
	public static synchronized String createRoom(SocketChannel socket, int width, int height, int speed, int nOApple) {
		Board board = new Board(50,50,1, 4);
		board.setDelay(1000/speed);
		userBoard.put(socket,board.getGrid().getNew());
		serverBoard.put(socket, board);
		userSnake.put(socket,board.getSnakeList().addRandomSnake() );
		return BoardManager.addBoard(board);
		
	}
	
	public static synchronized byte joinRoom(SocketChannel socket, String roomID) {
		System.out.println(roomID);
		Board board = BoardManager.getBoard(roomID);
		if (board == null) {
			return 1;
		}
		userBoard.put(socket,board.getGrid().getNew());
		serverBoard.put(socket, board);
		userSnake.put(socket,board.getSnakeList().addRandomSnake() );

		return 0;
	}

	public static synchronized int createAccount(String username, String password){
		if (userAccount.get(username) != ""){
			return 1;
		}
		userAccount.put(username, password);
		return 0;
	}

	public static synchronized int loginAccount(String username, String password){

		if(userAccount.get(username) == "" ){
			return -1;
		}

		if(userAccount.get(username).equals(password) ){
			return 0;
		}

		return 1;
	}
	
	public static synchronized byte[] getUpdateGrid(SocketChannel socket) {
		return serverBoard.get(socket).getUpdateGrid(userBoard.get(socket));
	}
	
	public static synchronized Board getServerBoard(SocketChannel socket) {
		return serverBoard.get(socket);
	}
	
	public static synchronized void erase(SocketChannel socket) throws IOException {
		userBoard.remove(socket);
		userSnake.get(socket).dead();
		socket.close();
		userSnake.remove(socket);
		
	}
	
	public static Tile1[][] getGrid(SocketChannel socket) {
		return userBoard.get(socket);
	}
	
	public static Snake getSnake(SocketChannel socket) {
		return userSnake.get(socket);
	}
	
	

}

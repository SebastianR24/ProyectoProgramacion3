package snakemultijugador.Client.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import snakemultijugador.Client.model.Board;


public class BoardManager implements Runnable{
	private static HashMap <String, Board> boardMap =  new HashMap<>();
	private static BoardManager instance ;
	private static final String AlphaNumericString =   "0123456789" + "ABCDEFGHIJKLMNOPQRSTUVXYZ";
	private static final int lengthID = 6;
	private static Thread thread;
	private boolean running = true;
	
	private BoardManager() {
		running = true;
	}
	
	public static synchronized String addBoard(Board board) {
	String sb = "AW41AF";
		
		boardMap.put("AW41AF",board);
		return sb;
	}
	
	public static synchronized Board getBoard(String roomID) {
		return boardMap.get(roomID);
	}
	
	public static BoardManager getInstace() {
		if (instance == null) {
			instance = new BoardManager();
		}
		return BoardManager.instance;
	}
	
	public void start() {
		thread = new Thread(this);
		thread.start();
	}
	
	private static synchronized void update() {
		for (Iterator<Entry<String, Board>> i = boardMap.entrySet().iterator(); i.hasNext();) {
		    Map.Entry<String, Board> element = i.next();
		    if (element.getValue().countSnake() == 0) {
		        i.remove();
		    }
		    else {
		    	element.getValue().update();
		    }
		}
	}


	@Override
	public void run() {
		while(running) {
			BoardManager.update();
		}
		
	}
	
	
	
	
	
	
	
}

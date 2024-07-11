package snakemultijugador.Client.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import snakemultijugador.Client.service.Client;

public class MenuMultiPlayerRoomJoin extends Menu{
	private static MenuMultiPlayerRoomJoin instance;
	private static MenuWidget joinRoom, joinButton, backButton,notify;
	private static ArrayList<MenuWidget> widgets;
	
	private MenuMultiPlayerRoomJoin() {
		super();
	}
	
	
	public static void init(Panel root) {
		if (instance == null) {
			instance = new MenuMultiPlayerRoomJoin();
			widgets = new ArrayList<>();
			
			
			joinRoom = new MenuEntry(root, "Ingrese el ID: ", "", new Font("TimesRoman", Font.BOLD, 20),
					300, 350);

			joinButton = new MenuString(root, "Unirme", new Font("TimesRoman", Font.BOLD, 30),
					MenuString.Side.CENTER, 475);
			
			backButton = new MenuString(root, "Atras", new Font("TimesRoman", Font.BOLD, 30),
			MenuString.Side.CENTER, 550);
			backButton.setColor(Color.WHITE, Color.GREEN, Color.CYAN);

			notify = new MenuString(root, "", new Font("TimesRoman", Font.BOLD, 30),
					MenuString.Side.CENTER, MenuString.Side.L);
			
			joinRoom.setColor(Color.YELLOW, Color.WHITE, Color.CYAN, Color.BLUE);
			joinButton.setColor(Color.WHITE, Color.GREEN, Color.CYAN);
			notify.setColor(Color.RED, Color.RED, Color.RED);

			widgets.add(joinRoom);
			widgets.add(joinButton);
			widgets.add(notify);
			widgets.add(backButton);
		}
	}
	
	
	public static MenuMultiPlayerRoomJoin getInstance() {
		if (instance == null) {
			throw new AssertionError("Debes hacer la instancia primero");
		}

		return instance;
	}
	
	private static int fromByteArray(byte[] bytes, int index) {
	     return ((bytes[index + 0] & 0xFF) << 24) | 
	            ((bytes[index + 1] & 0xFF) << 16) | 
	            ((bytes[index + 2] & 0xFF) << 8 ) | 
	            ((bytes[index + 3] & 0xFF) << 0 );
	}
	
	@Override
	public void update() {
		for (MenuWidget widget : widgets) {
			widget.update();
		}

	}

	@Override
	public void draw(Graphics g) {
		for (MenuWidget widget : widgets) {
			widget.active();
			widget.draw(g);
		}

	}

	@Override
	public void clear() {
		for (MenuWidget widget : widgets) {
			widget.disable();
		}

	}

	@Override
	public void mouseAction(int button) {
		if (joinRoom.isClick(button)) {
			
		}

		if (backButton.isClick(button)){
			clear();
			Panel.setMenu(MenuMultiPlayerRoomOption.getInstance());
		}
		
		if (joinButton.isClick(button)) {
			if(joinRoom.getEnteredString() == ""){
				return;
			}
			clear();
			byte[] buffer = Client.sendMessage("PUT Join_Room"+" "+"AW41AF");
			if (buffer[0] == (byte)0) {
				byte[] res = Client.sendMessage("Get Tam_Tablero");
				Board.setSizeGrid(fromByteArray(res,0), fromByteArray(res,4));
				Panel.setMenu(MenuMultiPlayer.getInstance());
				MenuMultiPlayer.setStringInfo("ID: " + "AW41AF");
			}
			else {
				notify.setText("No se puedo encontrar la sala");
			}
			
			
		}
	}

	@Override
	public void keyAction(int keycode) {
		if (joinRoom.isClicked()) {
			joinRoom.getString(keycode);
		}
	}
}

package proyectop3.Client.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

//import model.SnakeSet1;
//import util.SnakeDirection;

import proyectop3.Client.model.SnakeSet1;
import proyectop3.Client.util.SnakeDirection;

public class MenuBotPlayer extends Menu {
	private static MenuBotPlayer instance;
	private static MenuWidget exitButton, score;
	private static ArrayList<MenuWidget> widgets;

	private MenuBotPlayer() {
		super();
	}

	public static void init(Panel root) {
		if (instance == null) {
			instance = new MenuBotPlayer();
			widgets = new ArrayList<>();

			exitButton = new MenuString(root, "Cerrar", new Font("TimesRoman", Font.BOLD, 30), 900, 50);
			score = new MenuString(root, "Numero de bots restantes: 0", new Font("TimesRoman", Font.BOLD, 30), 350, 50);

			exitButton.setColor(Color.WHITE, Color.GREEN, Color.CYAN);
			score.setColor(Color.WHITE, Color.GREEN, Color.CYAN);

			widgets.add(Board.getInstance());
			widgets.add(exitButton);
			widgets.add(score);

		}
	}

	public static MenuBotPlayer getInstance() {
		if (instance == null) {
			throw new AssertionError("Debes hacer el llamado primero");
		}

		return instance;
	}

	@Override
	public void update() {
		if (Board.getPlayerSnake().isDead()){
			Panel.setMenu(MenuLoseBotPlayer.getInstance());
			clear();
			return;
		}

		if (SnakeSet1.countSnake() - 1 == 0) {
			Panel.setMenu(MenuWinBotPlayer.getInstance());
			clear();
			return;
		}
		score.setText("Numero de bots restantes: " + (SnakeSet1.countSnake() - 1));
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
		if (exitButton.isClick(button)) {
			Board.reset();
			Panel.setMenu(MenuStart.getInstance());
			clear();
		}

	}

	@Override
	public void keyAction(int keycode) {
		switch (keycode) {
			case KeyEvent.VK_LEFT:
				Board.getPlayerSnake().changeDirection(SnakeDirection.LEFT);
				break;
			case KeyEvent.VK_RIGHT:
				Board.getPlayerSnake().changeDirection(SnakeDirection.RIGHT);
				break;
			case KeyEvent.VK_UP:
				Board.getPlayerSnake().changeDirection(SnakeDirection.UP);
				break;
			case KeyEvent.VK_DOWN:
				Board.getPlayerSnake().changeDirection(SnakeDirection.DOWN);
				break;
		}

	}
}

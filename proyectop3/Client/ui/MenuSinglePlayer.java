package proyectop3.Client.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import proyectop3.Client.util.SnakeDirection;

public class MenuSinglePlayer extends Menu {
	private static MenuSinglePlayer instance;
	private static MenuWidget exitButton, score;
	private static ArrayList<MenuWidget> widgets;

	private MenuSinglePlayer() {

		super();
	}

	public static void init(Panel root) {
		if (instance == null) {
			instance = new MenuSinglePlayer();
			widgets = new ArrayList<>();
			

			exitButton = new MenuString(root, "Cerrar", new Font("Arial", Font.BOLD, 25), 900, 50);
			score = new MenuString(root, "Puntaje: 0", new Font("Arial", Font.BOLD, 25), MenuString.Side.CENTER, 50);


			exitButton.setColor(Color.WHITE, Color.GREEN, Color.CYAN);
			score.setColor(Color.WHITE, Color.GREEN, Color.CYAN);

			widgets.add(Board.getInstance());
			widgets.add(score);
			widgets.add(exitButton);

		}
	}

	public static MenuSinglePlayer getInstance() {
		if (instance == null) {
			throw new AssertionError("Debes hacer la llamada de inicio primero");
		}

		return instance;
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

	@Override
	public void update() {
		score.setText("Puntaje: " + Board.getPlayerSnake().getSizeBody());

		if (Board.getPlayerSnake().isDead()) {
			clear();
			Panel.setMenu(MenuLoseSinglePlayer.getInstance());
		}
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

}

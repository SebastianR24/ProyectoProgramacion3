package snakemultijugador.Client.ui;

import java.awt.Graphics;

public abstract class Menu {

	Menu() {}
//manejo del menu del juego
	public abstract void update();

	public abstract void draw(Graphics g);

	public abstract void clear();

	public abstract void mouseAction(int button);

	public abstract void keyAction(int keycode);

}

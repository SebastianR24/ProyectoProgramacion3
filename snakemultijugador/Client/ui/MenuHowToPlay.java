package snakemultijugador.Client.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class MenuHowToPlay extends Menu {
	private static MenuHowToPlay instance;
    private static MenuWidget info, backButton,credits;
	private static ArrayList<MenuWidget> widgets;
	
	private MenuHowToPlay() {
		super();
	}
	
	public static void init(Panel root) {
		if (instance == null) {
			instance = new MenuHowToPlay();
			widgets = new ArrayList<>();
			
			
			info = new MenuString(root, "Para jugar debes elegir como lo haras, si tu solo, con bots o en multiplayer. Te mueves utilizando las flechitas del teclado ", new Font("TimesRoman", Font.BOLD, 20),
				MenuString.Side.CENTER,  350);

			credits = new MenuString(root, "Made by: Angel Rodriguez, Sebastian Rodriguez, Enyer moya, Alejandro Rivas y Frank Chacon", new Font("TimesRoman", Font.BOLD, 20), 
			MenuString.Side.CENTER,		400);
            backButton = new MenuString(root, "Back", new Font("Ink Free", Font.BOLD, 30), MenuString.Side.CENTER, 625);
            
            backButton.setColor(Color.WHITE, Color.YELLOW, Color.CYAN);
			info.setColor(Color.YELLOW, Color.WHITE, Color.CYAN, Color.BLUE);
			credits.setColor(Color.YELLOW, Color.WHITE, Color.CYAN, Color.BLUE);
           

			widgets.add(info);
			widgets.add(credits);
			widgets.add(backButton);
		}
	}
	public static ArrayList<MenuWidget> getWidgets() {
        return widgets;
    }
	public static MenuHowToPlay getInstance() {
		if (instance == null) {
			throw new AssertionError("Debes hacer el llamado primero");
		}

		return instance;
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
		if (info.isClick(button)) {
			
		}
		
		if (credits.isClick(button)) {
			
		}
		
		if (backButton.isClick(button)) {
			clear();
			Panel.setMenu(MenuStart.getInstance());
		}
		
	}

	@Override
	public void keyAction(int keycode) {
		
	}

}

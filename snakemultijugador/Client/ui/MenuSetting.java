package snakemultijugador.Client.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class MenuSetting extends Menu {
    private static MenuSetting instance;
    private static MenuWidget numberApple, back, unitSpeed,widthBoard,heightBoard , fullScreen ;
    private static ArrayList<MenuWidget> widgets;

    private MenuSetting() {
        super();
    }

    public static void init(Panel root) {
        if (instance == null) {
            instance = new MenuSetting();
            widgets = new ArrayList<>();
        //cONFIGURACION DE LAS SETTINGS DEL JUEGO
            
            fullScreen = new MenuString(root, "Pantalla completa: apagado", new Font("TimesRoman", Font.BOLD, 30), 300, 350);
            fullScreen.setColor(Color.YELLOW, Color.CYAN, Color.CYAN);
            
			widthBoard = new MenuEntry(root, "Ancho: ", "50", new Font("TimesRoman", Font.BOLD, 30),300, 400);
			widthBoard.setColor(Color.YELLOW, Color.WHITE, Color.CYAN, Color.BLUE);
			
			heightBoard = new MenuEntry(root, "Alto: ", "50", new Font("TimesRoman", Font.BOLD, 30),300, 450);
			heightBoard.setColor(Color.YELLOW, Color.WHITE, Color.CYAN, Color.BLUE);
			
			
            numberApple = new MenuEntry(root, "Numero de manzanas: ", new Font("TimesRoman", Font.BOLD, 30), 300, 500);
            numberApple.setText("10");
            numberApple.setColor(Color.YELLOW, Color.WHITE, Color.CYAN, Color.BLUE);

            unitSpeed = new MenuEntry(root, "Velocidad de movimiento: ", new Font("TimesRoman", Font.BOLD, 30), 300, 550);
            unitSpeed.setText("15");
            unitSpeed.setColor(Color.YELLOW, Color.WHITE, Color.CYAN, Color.BLUE);

            back = new MenuString(root, "Atras", new Font("TimesRoman", Font.BOLD, 30), MenuString.Side.CENTER, 600);
            back.setColor(Color.WHITE, Color.YELLOW, Color.CYAN);

            widgets.add(numberApple);
            widgets.add(unitSpeed);
            widgets.add(back);
            widgets.add(unitSpeed);
            widgets.add(widthBoard);
            widgets.add(heightBoard);
            widgets.add(fullScreen);
        }
    }

   public static ArrayList<MenuWidget> getWidgets() {
        return widgets;
    } 

    public static MenuSetting getInstance() {
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
    	if(widthBoard.isClick(button)) {
    		
    	}
    	
    	if(heightBoard.isClick(button)) {
    		
    	}
    	
        if (numberApple.isClick(button)) {

        }


        if (unitSpeed.isClick(button)) {

        }
        
        if (fullScreen.isClick(button)) {
        	Frame.setFullScreen();
        	if (Panel.isFullScreen()) {
        		fullScreen.setText("Pantalla completa: encendida");
        	}
        	else {
        		fullScreen.setText("Pantalla completa: apagado");
        	}
        	
        }

        if (back.isClick(button)) {
            Panel.setMenu(MenuStart.getInstance());
            clear();
        }

    }

    @Override
    public void keyAction(int keycode) {
    	if (widthBoard.isClicked()) {
    		widthBoard.getNumber(keycode);
    	}
    	
    	if (heightBoard.isClicked()) {
    		heightBoard.getNumber(keycode);
    	}
    	
        if (numberApple.isClicked()) {
            numberApple.getNumber(keycode);
        }


        if (unitSpeed.isClicked()) {
            unitSpeed.getNumber(keycode);
        }

    }

}

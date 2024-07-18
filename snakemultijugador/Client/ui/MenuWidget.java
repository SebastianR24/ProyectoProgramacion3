package snakemultijugador.Client.ui;

import java.awt.Color;
import java.awt.Graphics;

public class MenuWidget implements IWidget {
	private Panel root;
	private int[] position;
	private boolean disabled = false;
//Widgets del menu
	MenuWidget(Panel root, int x, int y) {
		this.root = root;
		position = new int[] { x, y };
	}

	public void active() {
		this.disabled = false;
	}

	public void disable() {
		this.disabled = true;
	}

	protected int getX() {
		return position[0];
	}

	protected int getY() {
		return position[1];
	}

	protected void setX(int x) {
		this.position[0] = x;
	}

	protected void setY(int y) {
		this.position[1] = y;
	}

	public boolean isDisable() {
		return this.disabled;
	}

	protected Panel getRoot() {
		return this.root;
	}

	@Override
	public void setColor(Color colorDefault, Color colorOnHover, Color colorOnClick) {
		

	}

	@Override
	public void setText(String text) {
	

	}

	@Override
	public void draw(Graphics g) {
	

	}

	@Override
	public void update() {


	}

	@Override
	public boolean isClick(int button) {

		return false;
	}

	@Override
	public void getNumber(int keycode) {


	}

	@Override
	public boolean isEntered() {
	
		return false;
	}

	@Override
	public int getEnteredNumber() {
		return 0;
	}

	public boolean isClicked() {
		
		return false;
	}

	public void setColor(Color cyan, Color white, Color yellow, Color cyan2) {
		

	}

	@Override
	public void setColor(Color colorTitle, Color colorText) {
		

	}

	@Override
	public void getString(int keycode) {
		
		
	}

	@Override
	public String getEnteredString() {
		return null;
	}
}

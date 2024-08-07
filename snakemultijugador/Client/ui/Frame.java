package snakemultijugador.Client.ui;

import javax.swing.JFrame;

public class Frame extends JFrame {
	private static Frame instance;
	private Panel panel;

	private Frame() {
		//inciializa la ventana del juego
		panel = Panel.getInstance();
		this.add(panel);
		this.setTitle("Culebrita");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setResizable(false);
		this.setVisible(true);
		this.pack();
		panel.startGame();
		
		
	}

	
	public static Frame getInstance() {
		if (instance == null) {
			instance = new Frame();
		}
		return instance;
	}

	public static void close() {
		System.exit(0);
		instance.dispose();
		instance.setVisible(false);
	}
	
	public static void setFullScreen() {
		instance.dispose();

		Panel.setFullScreen();

		if (Panel.isFullScreen()) {
			instance.setUndecorated(true);
		}
		else {
			instance.setUndecorated(false);
		}
		instance.pack();


		instance.setLocationRelativeTo(null);
		instance.setVisible(true);
	}
}

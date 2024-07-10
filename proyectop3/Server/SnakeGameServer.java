package proyectop3.Server;

import java.io.IOException;


import proyectop3.Server.service.BoardManager;
import proyectop3.Server.service.Server;

public class SnakeGameServer {
	public static void main(String[] args) throws IOException {

		BoardManager.getInstace().start();
		System.out.println("Server encendido");
		Server.start(3333);

	}

}
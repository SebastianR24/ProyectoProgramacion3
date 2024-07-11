package snakemultijugador.Client;

import java.io.IOException;

import snakemultijugador.Client.service.BoardManager;
import snakemultijugador.Client.service.Server;

public class SnakeGameServer {
	public static void main(String[] args) throws IOException {

		BoardManager.getInstace().start();
		System.out.println("Server encendido");
		Server.start(3333);

	}

}
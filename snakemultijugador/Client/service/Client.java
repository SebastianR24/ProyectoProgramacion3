package snakemultijugador.Client.service;

import java.io.*;
import java.net.*;
import java.nio.channels.*;
import java.nio.ByteBuffer;



public class Client {
    private static SocketChannel clientSocket;
    private static ByteBuffer buffer;
    private static Client instance;
    private static byte[] response;
    private final static int LEN_BUFFER = 4096;
    

    public static Client start(String hostname, int port) {
        if (instance == null)
        	try {
        		instance = new Client(hostname,port);
        	}
        	catch(Exception e) {
        		System.out.println("No se puede conectar al servidor");
        	}
        return instance;
    }
    public static void close() throws IOException {
    	Client.clientSocket.close();
    	Client.instance = null;
    }
    
    private Client(String hostname, int port ) throws IOException  {
        clientSocket = SocketChannel.open(new InetSocketAddress(hostname, port));
        buffer = ByteBuffer.allocate(LEN_BUFFER);    
    }
    
    public static byte[] sendMessage(String msg) {
    	Client.clearBufffer();
        Client.buffer.put(msg.getBytes());
        Client.response = null;
        try {
        	Client.clientSocket.write(Client.buffer);
        	Client.clearBufffer();
        	int len = Client.clientSocket.read(Client.buffer);
            System.out.println(len);
            if (len < 4096){
                System.out.println("khong du:" + len);
            }
            Client.response = Client.buffer.array();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Client.response;

    }

    
    
    private static void clearBufffer() {
        Client.buffer.clear();
        Client.buffer.put(new byte[Client.LEN_BUFFER]);
        Client.buffer.clear();
    }
    
    
    
}

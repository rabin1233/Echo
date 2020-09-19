package echo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public double rating = 5;
	protected ServerSocket mySocket;
	protected int myPort;
	public static boolean DEBUG = true;
	protected Class<?> handlerType;

	public Server(int port, String handlerType) {
		try {
			myPort = port;
			mySocket = new ServerSocket(myPort);
			this.handlerType = (Class.forName(handlerType));
		} catch(Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		} // catch
	}


	public void listen() {
		try {
		while(true) {
			if (DEBUG) {
				System.out.println("is listening at port 5555");
			}
			Socket clientSocket = mySocket.accept(); //accept
			if(DEBUG) {
				System.out.println("accept it");
			}
			RequestHandler requestHandler = makeHandler(clientSocket); //makeHandler
			requestHandler.run();
		}// while
		
		} catch(IOException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}
	}

	public RequestHandler makeHandler(Socket s) throws IllegalAccessException, InstantiationException {
		// handler = a new instance of handlerType
		RequestHandler handler  = (RequestHandler) handlerType.newInstance();
		handler.setServer(this);
		// set handler's socket to s
		handler.setSocket(s);
		// return handler
		return handler; //return handler
	}



	public static void main(String[] args) {
		int port = 5555;
		String service = "echo.RequestHandler";
		if (1 <= args.length) {
			service = args[0];
		}
		if (2 <= args.length) {
			port = Integer.parseInt(args[1]);
		}
		Server server = new Server(port, service);
		server.listen();
	}
}
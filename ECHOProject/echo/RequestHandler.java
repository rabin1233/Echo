package echo;

import buffers.Utils;
import java.net.Socket;

public class RequestHandler extends Correspondent implements Runnable {
	public RequestHandler(Socket s) { super(s); }
	public RequestHandler() { }
	// override in a subclass:
	protected String response(String msg) throws Exception {
		return "echo: " + msg;
	}
	public void run() {
		while(true) {
			try {
				String request  = receive();	//receive request
				System.out.println("request = " + request);
				if (request == "quit")break;
				
				String answer = response(request);	//send response
				send(answer);
				System.out.println("answer = " + answer);
				
				Thread.yield();	//sleep
			    // send response
			    // sleep
			} catch(Exception e) {
				send(e.getMessage() + "... ending session");
				break;
			}
		}
		// close
	}
}

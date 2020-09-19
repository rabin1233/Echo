package echo;
//import buffers.Utils;
import java.net.Socket;

public class RequestHandler extends Correspondent implements Runnable {
	int rated = 0;
	Server server;
	public RequestHandler(Socket s) { super(s); }
	public RequestHandler() { }
	// override in a subclass:
	protected String rararesponse(String msg) throws Exception {
		return "echo: " + msg;
	}
	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	protected String response(String [] msg) throws Exception {
		String res = "";
		for (int i = 0; i < msg.length; i++) {
			System.out.println(msg[i]);
		}

		if(msg[0].equals("rate"))
		{
			if(isNumeric(msg[1]))
			{
				int n = Integer.parseInt(msg[1]);
				if(n < 0 || n > 5 )
				{
					res = ":ratings must be 0 – 5";
				}else if (rated == 1)
				{
					res = "only one rating per session";
				}
				else {
					//update the rate
					int r = Integer.parseInt(msg[1]);
					server.rating += r;
					server.rating/=2;
					res = "average rating = " + server.rating;
					rated = 1;
				}
			}
			else {
				res = "Invalid command";
			}
		}
		else if(msg[0].equals("add"))
		{
			System.out.println("hi");
			int sum = 0;
			for (int i = 1; i < msg.length; i++) {
				if(isNumeric(msg[i]))
					sum+= Integer.parseInt(msg[i]);
				else {
					res = "Invalid command";
					break;
				}
			}
			if(!res.equals("Invalid command"))
				res = String.valueOf( sum);
		}
		else if (msg[0].equals("mul")){
			System.out.println("Hello");
			int mul = 0;
			for(int i = 1; i <msg.length; i++){
				if(isNumeric(msg[i]))
					mul *= Integer.parseInt(msg[i]);
				else {
					res = "Invalid command";
					break;
				}
			}
			if(!res.equals("Invalid command"))
				res = String.valueOf(mul);
		}
		return   res;
	}
	public void run() {
		while(true) {
			try {
				String request  = receive();	//receive request
				String[] commands = request.split("\\s+");
				String cmd = commands[0];
				System.out.println("request = " + cmd);
				if (cmd == "quit")
					break;
				
				String answer = response(commands);	//send response
				send(answer);
				System.out.println("answer = " + answer);
				
				Thread.yield();	//sleep
				
			} catch(Exception e) {
				send(e.getMessage() + "... ending session");
				break;
			}
		}
		
	}

	public void setServer(Server server) {
		this.server = server;
	}
}

package echo;

import java.net.Socket;

public class ProxyHandler extends RequestHandler {

		protected Correspondent peer;

		public ProxyHandler(Socket s) { super(s); }
		public ProxyHandler() { super(); }

		public void initPeer(String host, int port) {
			peer = new Correspondent();
			peer.requestConnection(host, port);
		}

		protected String response(String msg) throws Exception {
			String output = "Response: \t";
	       // forward msg to peer
			peer.send(msg);
			
			output = peer.receive();
	       // return peer's response
			return output;
		}
 }



package echo;

import java.util.*;

public class Buffer {

	private List<Message<String>> buffer = new LinkedList<Message<String>>();
	public int CAPACITY = 3;
			
	public synchronized boolean write(Message<String> msg) { 
		while(CAPACITY <= buffer.size()) {
			try {
				wait();
			} catch(InterruptedException e) {
				
			}
		}
		Utils.sleep(100);
		boolean success = false;
		buffer.add(msg);
		success = true;
		Utils.sleep(50);
		notify();
		return success;
	}
	
	public synchronized Message<String> read() {
		while(0 == buffer.size()) {
			try {
				wait();
			} catch(InterruptedException e) {
				
			}
		}
		Utils.sleep(50);
		Message<String> res = buffer.remove(0); 
		Utils.sleep(100);
		notify();
		return res;
	}
	
	public String toString() { return buffer.toString(); }

}
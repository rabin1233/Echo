package echo;

import java.util.HashMap;

public class UserTable extends HashMap<String, String> {
	private String getPassword(String user) {
		this.get(user);
		return null;
}

	 void newUser(String user, String password ) {
		put(user, password);
		
	}
}
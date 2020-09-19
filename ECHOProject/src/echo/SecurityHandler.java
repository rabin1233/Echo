package echo;

import java.util.HashMap;
import java.util.Map;

public class SecurityHandler extends ProxyHandler{
	private static UserTable user = new UserTable();
	private boolean loggedIn = false;
	String InputUser, InputPassword;
	static Map<String, String> loginTable = new HashMap<>();
	
	public String getPassword(String user) {
		return loginTable.get(user);
		
	}
	void newUser(String user, String password) {
		loginTable.put(user, password);
	}

	protected String response(String msg) throws Exception{
		String answer = "";
		String input[] = msg.split("\\s");

		if(input[0].equalsIgnoreCase("new")) {
			//check if its unique
			newUser(input[1], input[2]);
			answer = "Account created successfully";
			
		}else if(input[0].equalsIgnoreCase("login")) {
			if(input[2].equals(getPassword(input[1]))) {
				loggedIn =true;
				answer = "log in successful";
			}
			else {
				answer = "invalid user/password, try again";
			}
		}
			else if(!loggedIn) {
				return "not Logged in";
			}else {
				return super.response(msg);
			}
			return answer;
		}
	}


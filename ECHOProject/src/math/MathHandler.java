package math;
import echo.RequestHandler;

import java.net.Socket;

import echo.RequestHandler;

public class MathHandler extends RequestHandler {
    //private int sum, multiply, divide, subtract;

    public MathHandler() {

    }
    public MathHandler(Socket s) {

           super(s);
    }
protected String response(String request) throws Exception{

           String[] data = request.split(" ");

           String operator = "Result:\t";

           double fNum = 0;
           double sNum = 0;

           //checking for valid input
           
           //store the firstnum as a function and see if proper
           		String function = data[0];
           	if(function.equals("add")|| function.equals("mul")|| function.equals("div")|| function.equals("sub")) {
           		
           try {

                 fNum = Double.parseDouble(data[1]);

           }catch(NumberFormatException e) {

                 return "Invalid Numbers\n";
           }
          /* if(!operator.equals("add") && !operator.contentEquals("multiply") && !operator.contentEquals("subtract") && !operator.contentEquals("divide")) {

                 return "Enter a valid operator";

           }

          */
           sNum = fNum;
           double total = 0;
           //String popUpMessage = " ";
           for(int i = 2; i < data.length; i++) {
        	  total = Double.parseDouble(data[i]);
                 //checking for sum-div-etc
                 switch(function) {
                        case "add":
                               sNum += total;
                               break;
                        case "mul":
                               sNum *= total;
                               break;
                        case "div":
                              if(total!=0) {
                            	   sNum/= total;
                               }
                               break;

                        case "sub":
                               sNum -= total;
                               break;
                 }
           }
       operator = operator + sNum;
           return operator;
           	}
           	else {
           		return "invalid function\t"+ function +"\t-Select following :add | mul | div | sub\n";
           	}
    }
}
   
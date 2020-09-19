package math;

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

           String operator = data[0];

           double firstNum = 0;

           //checking for valid input

           try {

                 firstNum = Double.parseDouble(data[1]);

           }catch(NumberFormatException e) {

                 return "Invalid Numbers";

           }

          

           if(!operator.equals("add") && !operator.contentEquals("multiply") && !operator.contentEquals("subtract") && !operator.contentEquals("divide")) {

                 return "Enter a valid operator";

           }

          

           double total = firstNum;

           String popUpMessage = " ";

          

           for(int i = 1; i <= data.length; i++) {

                 //checking for sum-div-etc

                

                 switch(operator) {

                        case "add":

                               total += Double.parseDouble(data[i]);

                               break;

                        case "multiply":

                               total *= Double.parseDouble(data[i]);

                               break;

                        case "divide":

                               total /= Double.parseDouble(data[i]);

                               break;

                        case "subtract":

                               total -= Double.parseDouble(data[i]);

                               break;

                 }

           }

          

           popUpMessage = " "+ total;

           return popUpMessage;

          

    }
}
   
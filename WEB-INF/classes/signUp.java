

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

/** Shows all the parameters sent to the servlet via either
 *  GET or POST. Specially marks parameters that have
 *  no values or multiple values.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
*/

public class signUp extends HttpServlet {
//MySQLDataStoreUtilities userUtility = null;
public void init(){

//	userUtility = new MySQLDataStoreUtilities();
	/*us = new UserAccountSerializedDataStore();
	users = us.readUserAccountDataStore();*/

}





  public void doGet(HttpServletRequest request,HttpServletResponse response)
      throws ServletException, IOException {
  response.setContentType("text/html");
  PrintWriter out = response.getWriter();
  String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
  String title = "Reading All Request Parameters";

  String username = request.getParameter("userid");
	String password = request.getParameter("password");
	String firstName = request.getParameter("firstName");
	String lastName = request.getParameter("lastName");
	String email = request.getParameter("email");
	String phone = request.getParameter("phone");
	String userType = request.getParameter("userType");
	boolean found = false;
	System.out.println(username);

	try{
	found = MySQLDataStoreUtilities.doesExist(username.trim());
	System.out.println("I'm here" + found);
    } catch(Exception e) {
		e.printStackTrace();
	}




		if(found){
				showMessage(response, found,request);
		 } else{
				User u2 = new User(username,password,firstName,lastName,email,phone,userType);

				MySQLDataStoreUtilities.insertUser(u2);
				String message = "userdoe";
				showMessage(response, found,request);
		 }


  }

  public void showMessage(HttpServletResponse response,boolean found,HttpServletRequest request) throws ServletException, IOException{
	  String title = "Sing up Screen";
	  PrintWriter out = response.getWriter();
	  out.println(
                "<HTML>\n" +
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                "<BODY>");
	 if(found==true){
		  out.print("User already exists.");
		  out.println("<a href=\"signIn.html\">Click here to signIn</a>\n");
	 }
	  else{
		  out.println("User account created. ");
	      out.println("<a href=\"signIn.html\">Click here to signIn</a>\n");
	  }
		  out.println("</BODY>");

		 /* out.println( "<tr>\n" +
                       "<td colspan='2'>\n" +
                       "<center>" +
			           "<form method=\"get\" action=\"/BestDeal/signIn.html\">\n" +
                       "<input type=\"submit" value=\"Login" /></center>\n" +
                       "</form>\n" +
                       "</td>\n");
					   */

  }

  public void doPost(HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}

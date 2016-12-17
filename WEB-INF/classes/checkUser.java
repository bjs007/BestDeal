/*
 * LoginServlet.java
 *
 */


import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

public class checkUser extends HttpServlet {

  // MySQLDataStoreUtilities userUtility = null;
    /**
     * Initializes the servlet with some usernames/password
    */
    public void init() {
           //     us = new UserAccountSerializedDataStore();
			//	users = us.readUserAccountDataStore();

//				userUtility = new MySQLDataStoreUtilities();
    }

    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
  	HttpSession session = request.getSession(true);
    String user_id = request.getParameter("user_id");
    session.putValue("customer",user_id);
    java.io.PrintWriter out = response.getWriter();
    String userType = "customer";
    String InsertOrder =  "/BestDeal/InsertOrder";
    InsertOrder = response.encodeURL(InsertOrder);

        if(user_id ==null) {
            out.println("User name is blank!");
        }


        if(user_id != null ) {
		     boolean found = MySQLDataStoreUtilities.doesExist(user_id);
			      if (found)

				          session.putValue("customer", user_id);
				   		    //RequestDispatcher rd=request.getRequestDispatcher("/Home");
							  //  rd.forward(request, response);

                out.println("<FORM ACTION=\"" +InsertOrder + "\">");

                out.println("<TABLE BORDER=1 ALIGN=\"CENTER\">\n" +
                   "<TR BGCOLOR=\"#FFAD00\">\n");
                out.println("<tr><td>ItemID: </td><td><input type=\"TEXT\" name =\"item_id\"></td></tr>");
                out.println("<tr><td>Price: </td><td><input type=\"TEXT\" name =\"item_price\"></td></tr>");
                out.println("<tr><td>Description: </td><td><input type=\"TEXT\" name =\"item_desc\"></td></tr>");
                out.println("<tr><td colspan='2'>");
                out.println("<center><input type=\"submit\" value=\"Insert Item\" /></center>");
                out.println("</td></tr></table></form>");
						}
						else{
							out.println("User does not exist!");
						}
          }




    /**
     * Actually shows the <code>HTML</code> result page
     */
    protected void showPage(HttpServletResponse response,HttpServletRequest request, String message,boolean login)
    throws ServletException, java.io.IOException {

	if (login== true)
	{
		RequestDispatcher rd=request.getRequestDispatcher("/Home");
		rd.forward(request, response);
	}
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login Servlet Result</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>" + message + "</h2>");
        out.println("</body>");
        out.println("</html>");
        out.close();


    }

    /** Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    /** Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }
}

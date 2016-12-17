/*
 * LoginServlet.java
 *
 */


import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

public class logout extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
	HttpSession session = request.getSession(true);
//   MySQLDataStoreUtilities.deleteProducts();
     session.invalidate();
  java.io.PrintWriter out = response.getWriter();
	  RequestDispatcher rd=request.getRequestDispatcher("/Home");

		 rd.forward(request, response);

    }


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

/*
 * Order.java
 *
 */

import java.util.*;
import java.sql.Date;
import javax.servlet.*;
import javax.servlet.http.*;

public class OrderCancel extends HttpServlet {

   //MySQLDataStoreUtilities mysqlUtility = null;
    /**
     * Initializes the servlet with some usernames/password
    */
    public void init() {

      //  DateFormat formatter = new
    //    mysqlUtility = new MySQLDataStoreUtilities();
    }

    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
      	HttpSession session = request.getSession(true);
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        String orderid = request.getParameter("order_id");
        Integer order_id = Integer.parseInt(orderid.trim());
       /*
        java.util.Date order_date = new java.util.Date();
        java.util.Date delivery_date = new java.util.Date();
        Calendar c = Calendar.getInstance();
        c.setTime(delivery_date);
        c.add(Calendar.DATE, 15);
        delivery_date = c.getTime();
           */

       java.util.Date delivery_date =  MySQLDataStoreUtilities.getOrderDates(order_id);
        // Update user account
       java.util.Date current_date = new java.util.Date();

    //   java.util.Date order_date = dates.get(0);

        int diffInDays = (int) ((delivery_date.getTime() - current_date.getTime()) / (1000 * 60 * 60 * 24));
  //      int diffInDays = dates.size();
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        // out.println("In order");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>BestDeal</title>");
        out.println("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
        out.println("</head>");
        out.println("<body>");
        out.println("<div id=\"container\">");
        out.println("<header>");
        out.println("<h1 style="+ "\"color:red\"" + "<span>Best Deal</span></h1>");
        out.println("<h2 style=\"color:blue\"><I> Better than it is not possible</I></h2>");
        out.println("</header>\n");
        out.println("<div id=\"body\"> ");
        out.println("<div style=\"width: 30%;height:100%;margin:0 auto\">");
        if(diffInDays>=5){
           MySQLDataStoreUtilities.DeleteOrderDetail(order_id);
           out.println("<tr><td>Order Number:"+ order_id +" has been cancelled</td><td>");
        }else if(diffInDays>=0 && diffInDays<5){
          out.println("<tr><td>Order Number:"+ order_id +" can't be cancelled within 5 days of delivery.</td><td>");
        } else{
          out.println("<tr><td>Order Number:"+ order_id +" should be delivered by now</td><td>");
        }

        out.println("<table>");

        out.println("</table>");
        out.println("</div> \n");
        out.println("</div> \n");
        out.println("</div> \n");
        out.println("</body>");
        out.println("</html>");

        }
    /**
     * Actually shows the <code>HTML</code> result page
     */

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

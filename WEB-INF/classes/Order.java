/*
 * Order.java
 *
 */


import java.util.*;
import java.sql.Date;
import javax.servlet.*;
import javax.servlet.http.*;

public class Order extends HttpServlet {
   HashMap<String,ArrayList<ItemOrder>> existingcart = null;
   UserCarts us = null;
   OrderTrack ot = null;
   //MySQLDataStoreUtilities mysqlUtility = null;
    /**
     * Initializes the servlet with some usernames/password
    */
    public void init() {
				ot = new OrderTrack();
        us = new UserCarts();
				existingcart = us.readUserCart();
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
        ArrayList<ItemOrder> orderList = new ArrayList<ItemOrder>();

	  ShoppingCart cart;
	   synchronized(session) {
       cart = (ShoppingCart)session.getAttribute("shoppingCart");
      // New visitors get a fresh shopping cart.
      // Previous visitors keep using their existing cart.
       if (cart != null) {
         orderList = (ArrayList<ItemOrder>)cart.getItemsOrdered();
	       us.writeUserCart(userid,orderList);
	       showPage(response,request,orderList);
        }

    }
}
    /**
     * Actually shows the <code>HTML</code> result page
     */
    protected void showPage(HttpServletResponse response,HttpServletRequest request,ArrayList<ItemOrder> items)
    throws ServletException, java.io.IOException {
      HttpSession session = request.getSession(true);
	    String userid = (String)session.getValue("userid");
	    String name = (String)request.getParameter("name");
	    String cardNum = (String)request.getParameter("cardNum");
	    String phone = (String)request.getParameter("phone");
      String email = (String)request.getParameter("email");
	    String cardType = (String)request.getParameter("cardType");
      java.util.Date order_date = new java.util.Date();
      java.util.Date delivery_date = new java.util.Date();
      Account account = new Account(userid,name,cardNum,phone,email,cardType);
      int order_id = MySQLDataStoreUtilities.OrderCount() + 1;
      Calendar c = Calendar.getInstance();
      c.setTime(delivery_date);
      c.add(Calendar.DATE, 15);
      delivery_date = c.getTime();
      OrderDetail od = new OrderDetail(order_id,userid,items,order_date,delivery_date);
      MySQLDataStoreUtilities.insertUserAccount(account); // Update user account
      MySQLDataStoreUtilities.insertOrderDetail(od);
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
		  out.println("<table>");
  		out.println("<tr><td>Order Number:"+ order_id +"</td><td>");
  		out.println("</table>");
  		out.println("</div> \n");
  		out.println("</div> \n");
  		out.println("</div> \n");
  		out.println("</body>");
  		out.println("</html>");

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

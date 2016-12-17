/*
 * product.java
 *
 */


import java.util.*;
import java.io.*;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

public class Checkout extends HttpServlet {

 public Map<String,ArrayList<String>> products = new  HashMap<String,ArrayList<String>>();

    /**
     * Initializes the servlet with some usernames/password
    */
    public void init() {

	}
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {



    ArrayList<ItemOrder> orderList = new ArrayList<ItemOrder>();

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
    ShoppingCart cart;
    cart = (ShoppingCart)session.getAttribute("shoppingCart");
    orderList = (ArrayList<ItemOrder>)cart.getItemsOrdered();
		String checkoutURL =
    response.encodeURL("/BestDeal/Order");

		out.println("<html>");
    out.println("<head>");
		out.println("<title>spigot - Free CSS Template by ZyPOP</title>");
		out.println("<title>spigot - Free CSS Template by ZyPOP</title>");
		out.println("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
		out.println("</head>");
		out.println("<body>");

		out.println("<div id=\"container\">");
		out.println("<header>");
		out.println("<h1 style="+ "\"color:red\"" + "<span>BestDeal</span></h1>");
		out.println("<h2 style=\"color:blue\"><I> Better than it is not possible</I></h2>");
		out.println("</header>\n");
    out.println("<div id=\"body\"> ");
    out.println("<div style=\"width: 30%;height:100%;margin:0 auto\">");
    out.println("<table>");

    double totalPrice = 0;
    for(int i=0;i<orderList.size(); i++) {
      ItemOrder it = orderList.get(i);
      out.println("<tr><td>Item:"+it.getShortDescription() +"</td>");
      out.println("<td>Price"+it.getUnitCost() +"</td><tr>");
      totalPrice = totalPrice + it.getUnitCost();
    }
    out.println("<tr><td>Total Price:"+ totalPrice +"</td><td>");
    out.println("</table>");

//		out.println("<div style=\"width: 30%;height:100%;margin:0 auto\">");
	//	out.println("<CENTER> <h3> Please provide below detail to buy</h3> </CENTER>");
		out.println("<FORM ACTION=\"" + checkoutURL + "\">");
		out.println("<table align =\"CENTER\" style = \"margin: 0px auto;\" cellpadding='2' cellspacing = '1'>\n");
		out.println("<tr><td>Name:</td><td><input type=");
		out.println("\"TEXT\" size=\"15\"");
		out.println("name=\"name\"></input></td> </tr>");


		out.println("<tr><td>Card Number:</td><td><input type=");
		out.println("\"TEXT\" size=\"15\"");
		out.println("name=\"cardNum\"></input></td> </tr>");

		out.println("<tr><td>Phone Number:</td><td><input type=");
		out.println("\"TEXT\" size=\"15\"");
		out.println("name=\"phone\"></input></td> </tr>");

    out.println("<tr><td>Email:</td><td><input type=");
    out.println("\"TEXT\" size=\"15\"");
    out.println("name=\"email\"></input></td> </tr>");

		out.println("<tr><td>");
		out.println("<INPUT TYPE=\"RADIO\" NAME=\"cardType\" VALUE=\"visa\" checked>Visa");
    out.println("</tr></td>");

		out.println("<tr><td>");
		out.println("<INPUT TYPE=\"RADIO\" NAME=\"cardType\" VALUE=\"master\">"+"Master");

    out.println("</tr></td>");

		out.println("<tr><td colspan='2'>");
		out.println("<center><input type=\"submit\" value=\"Buy\" /></center>");
		out.println("</td></tr></table></form>");

		out.println("</div> \n");
	//	out.println("</div> \n");
		out.println("</div> \n");
		out.println("</body>");
		out.println("</html>");


//RequestDispatcher rd=request.getRequestDispatcher("index.html");
//rd.forward(request, response);



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

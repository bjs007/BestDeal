

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;


public class salesman extends HttpServlet {
 HashMap<String,ArrayList<Review>> reviewHashmap = null;



  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
    		HttpSession session = request.getSession(true);
        String itemID = request.getParameter("itemID");
    	  reviewHashmap =  MongoDBDataStoreUtilities.selectReviews();

        out.println("<html>");
        out.println("<head>");
    		out.println("<title>BestDeal</title>");
    		out.println("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
    		out.println("</head>");
    		out.println("<body>");

    		out.println("<div id=\"container\">");
    		out.println("<header>");
    		out.println("<h1 style="+ "\"color:red\"" + "<span> BestDeal</span></h1>");
    		out.println("<h2 style=\"color:blue\"><I> Better than this is not possible</I></h2>");
    		out.println("<CENTER><form method=\"get\" class=\"searchform\" action=\"#\" >\n" +
                         "<input type=\"text\" size=\"32\" value=\"\" name=\"s\" class=\"s\" />\n" +

    								" </form>\n" +
    					"</CENTER>\n" +



    				"</header>\n");
    		out.println("<div align=\"right\">\n" );

    		String userid = (String)session.getValue("userid");


    				//out.println(userid);
    		if(userid!=null){
    				out.println("Welcome Back "+userid+"!" );
    				out.println("<a href= \"http://localhost/BestDeal/logout\">Logout</a>");


    				}else{
    				out.println("<a href=\"signIn.html\">Sign In</a>\n" +
    				"<a href=\"register.html\"> Create account</a>\n");
    				}
    				out.println("</div>\n");
    				out.println(
    				"<nav>\n" +
    					"<ul>\n" +
    						"<li class=\"start selected\"><a href=\"http://localhost/BestDeal/Home\">Home</a></li>\n" +
    						"<li><a href= \"http://localhost/BestDeal/productTab?prodType=laptop&user="+userid+"\">Laptop</a></li> \n" +
    						"<li><a href= \"http://localhost/BestDeal/productTab?prodType=tablet&user="+userid+"\">Tablet</a></li> \n" +
    						"<li><a href= \"http://localhost/BestDeal/productTab?prodType=mobile&user="+userid+"\">Mobile</a></li> \n" +
    						"<li><a href= \"http://localhost/BestDeal/productTab?prodType=tv&user="+userid+"\">TV</a></li> \n" +
    						"<li class=\"end\"><a href=\"http://localhost/BestDeal/OrderStatus\">Track Order</a></li> \n" +
                "<li class=\"end\"><a href=\"#\">CONTACT</a></li> \n" +
    					"</ul>\n" +
    				"</nav>\n");
            out.println("<div id=\"body\"> \n" + "<section id=\"content\"> \n" );
            //Write your code here
                  String createUser =
                  response.encodeURL("/BestDeal/createUser");
            String DeleteOrder =
                      response.encodeURL("/BestDeal/OrderCancel");
            String checkUser =
                      response.encodeURL("/BestDeal/checkUser");
          String OrderUpdateDetail =
                                response.encodeURL("/BestDeal/OrderUpdateDetail");
          //		out.println("<div style=\"width: 30%;height:100%;margin:0 auto\">");
          	//	out.println("<CENTER> <h3> Please provide below detail to buy</h3> </CENTER>");
            out.println("<table BORDER=\"1\">");
            out.println("<tr><td>");
            out.println("<FORM ACTION=\"" + createUser + "\">");
            out.println("<center><input type=\"submit\" value=\"Create User\" /></center></td></tr>");
            out.println("</FORM>");
            out.println("<tr><td colspan='2'>");
            out.println("<FORM ACTION=\"" + DeleteOrder + "\">");
            out.println("<td><center><input type=\"text\" size=\"4\" name=\"order_id\"/></center></td>");
            out.println("<td><center><input type=\"submit\" value=\"Delete Order\" />");
            out.println("</center></td></tr>");
            out.println("</form>");
            out.println("<tr><td colspan='2'>");
            out.println("<FORM ACTION=\"" + checkUser + "\">");
            out.println("<td><center><input type=\"text\" size=\"4\" name=\"user_id\"/></center></td>");
            out.println("<td><center><input type=\"submit\" value=\"Enter user name to create Order\" />");
            out.println("</center></td></tr>");
            out.println("</form>");
            out.println("<tr><td colspan='2'>");
            out.println("<FORM ACTION=\"" + OrderUpdateDetail + "\">");
            out.println("<td><center><input type=\"text\" size=\"4\" name=\"order_id\"/></center></td>");
            out.println("<td><center><input type=\"submit\" value=\"Enter order number to Update\" />");
            out.println("</center></td></tr>");
            out.println("</form>");

            out.println("</table>");
            out.println("</section> \n" +
              " <aside class=\"sidebar\"> \n" +

    					  "  <ul>	\n" +
    						  " <li> \n" +
    							   " <h4>Laptop</h4> \n" +
    							   " <ul> \n" +
    								   " <li><a href=\"http://localhost/BestDeal/productTab?company=asus&prodType=laptop\"\">Asus</a></li> \n" +

    								"</ul> \n" +

    								 " <ul> \n" +
    								   " <li><a href=\"http://localhost/BestDeal/productTab?company=dell&prodType=laptop\"\">Dell</a></li> \n" +

    								"</ul> \n" +

    								 " <ul> \n" +
    								   " <li><a href=\"http://localhost/BestDeal/productTab?company=hp&prodType=laptop\"\">HP</a></li> \n" +

    								"</ul> \n" +
    								" <ul> \n" +
    								   " <li><a href=\"http://localhost/BestDeal/productTab?company=samsung&prodType=laptop\"\">Samsung</a></li> \n" +

    								"</ul> \n" +
    								 "<ul> \n <li><a href=\"http://localhost/BestDeal/productTab?company=lenovo&prodType=laptop\"\">Lenovo</a></li> \n" +

    								"</ul> \n" +

    						   " </li> \n" +

    						   " <li> \n" +
    							   " <h4>Smart Phone</h4> \n" +
    							   " <ul> \n" +
    								   " <li><a href=\"http://localhost/BestDeal/productTab?company=asus&prodType=mobile\"\">Asus</a></li> \n" +

    								"</ul> \n" +

    								 " <ul> \n" +
    								   " <li><a href=\"http://localhost/BestDeal/productTab?company=iphone&prodType=mobile\"\">Iphone</a></li> \n" +

    								"</ul> \n" +

    								 " <ul> \n" +
    								   " <li><a href=\"http://localhost/BestDeal/productTab?company=microsoft&prodType=mobile\"\">Microsoft</a></li> \n" +

    								"</ul> \n" +
    								" <ul> \n" +
    								   " <li><a href=\"http://localhost/BestDeal/productTab?company=nokia&prodType=mobile\"\">Nokia</a></li> \n" +

    								"</ul> \n" +
    								 "<ul> \n <li><a href=\"http://localhost/BestDeal/productTab?company=samsung&prodType=mobile\"\">Samsung</a></li> \n" +

    								"</ul> \n" +

    						   " </li> \n" + " <li> \n" +
    							   " <h4>Tablet</h4> \n" +
    							   " <ul> \n" +
    								   " <li><a href=\"http://localhost/BestDeal/productTab?company=apple&prodType=tablet\"\">Apple</a></li> \n" +

    								"</ul> \n" +

    								 " <ul> \n" +
    								   " <li><a href=\"http://localhost/BestDeal/productTab?company=microsoft&prodType=tablet\"\">Microsoft</a></li> \n" +

    								"</ul> \n" +

    								 " <ul> \n" +
    								   " <li><a href=\"http://localhost/BestDeal/productTab?company=nokia&prodType=tablet\"\">Nokia</a></li> \n" +

    								"</ul> \n" +
    								" <ul> \n" +
    								   " <li><a href=\"http://localhost/BestDeal/productTab?company=samsung&prodType=tablet\"\">Samsung</a></li> \n" +

    								"</ul> \n" +
    								 "<ul> \n <li><a href=\"http://localhost/BestDeal/productTab?company=sony&prodType=tablet\"\">Sony</a></li> \n" +

    								"</ul> \n" +

    						   " </li> \n" +
    						   " <li> \n" +
    							   " <h4>TV</h4> \n" +
    							   " <ul> \n" +
    								   " <li><a href=\"http://localhost/BestDeal/productTab?company=lg&prodType=tv\"\">LG</a></li> \n" +

    								"</ul> \n" +

    								 " <ul> \n" +
    								   " <li><a href=\"http://localhost/BestDeal/productTab?company=onida&prodType=tv\"\">Onida</a></li> \n" +

    								"</ul> \n" +

    								 " <ul> \n" +
    								   " <li><a href=\"http://localhost/BestDeal/productTab?company=philips&prodType=tv\"\">Philips</a></li> \n" +

    								"</ul> \n" +
    								" <ul> \n" +
    								   " <li><a href=\"http://localhost/BestDeal/productTab?company=samsung&prodType=tv\"\">Samsung</a></li> \n" +

    								"</ul> \n" +
    								 "<ul> \n <li><a href=\"http://localhost/BestDeal/productTab?company=sony&prodType=tv\"\">Sony        </a></li> \n" +

    								"</ul> \n" +

    						   " </li> \n" +

    						   " <li> \n"+

    								"<h4>About us</h4> \n" +
    								"<ul> \n" +
    								   " <li class=\"text\"> \n" +
    										"<p style=\"margin: 0;\">Aenean nec massa a tortor auctor sodales sed a dolor. Duis vitae lorem sem. Proin at velit vel arcu pretium luctus. 					<a href=\"#\" class=\"readmore\">Read More &raquo;</a></p> \n" +
    									 " </li> \n" +
    								"</ul>\n" +
    							"</li>\n" +




    						"</ul>\n" +

    				   " </aside> \n" +
    					"<div class=\"clear\"></div> \n" +
    				"</div>\n" +


    			   "<footer>\n" +
    					"<div class=\"footer-content\"> \n" +


    					   " <ul> \n" +
    							"<li><h6>Orders and Return</h6></li>\n" +
    						"</ul> \n" +
    						"<ul> \n" +
    							"<li><h6>Career</h6></li>\n" +



    						"</ul> \n" +
    					   " <ul class=\"endfooter\"> \n" +
    							"<li><h6>Conditions of Use</h6></li> \n" +


    						"</ul> \n" +

    						"<div class=\"clear\"></div> \n" +
    					"</div>\n" +
    					"<div class=\"footer-bottom\"> \n" +
    						"<p>&copy; BestDeal 2016. All rights reserved</p>\n" +
    					 "</div> \n" +
    				"</footer> \n" +
    			"</div> \n");









    		out.println("</body>");
    		out.println("</html>");

  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, java.io.IOException {
      doGet(request, response);
  }
}

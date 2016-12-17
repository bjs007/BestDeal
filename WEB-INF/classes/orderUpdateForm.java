

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;


public class orderUpdateForm extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
    		HttpSession session = request.getSession(true);
        String itemID = request.getParameter("itemID");
    		String reviewInsertURL =
        response.encodeURL("/BestDeal/reviewInsert");

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
    		out.println("<h2 style=\"color:blue\"><I> Better than this is not possible</I></h2>");
    		out.println("</header>\n");
        out.println("<div id=\"body\"> ");
        out.println("<div style=\"width: 50%;margin:0 auto\">");

    //		out.println("<div style=\"width: 30%;height:100%;margin:0 auto\">");
    	//	out.println("<CENTER> <h3> Please provide below detail to buy</h3> </CENTER>");
    		out.println("<FORM ACTION=\"" + reviewInsertURL + "\">");
        out.println(  "<INPUT TYPE=\"HIDDEN\" NAME=\"itemID\"VALUE=\"" +itemID+"\">");
    		out.println("<table align =\"CENTER\" style = \"margin: 0px auto;\" cellpadding='2' cellspacing = '1'>\n");
    		out.println("<tr><td>ProductModelName: </td><td><input type=\"TEXT\" name =\"ProductModelName\"></td></tr>");
        out.println("<tr><td>ProductCategory: </td><td><input type=\"TEXT\" name =\"ProductCategory\"></td></tr>");
        out.println("<tr><td>ProductPrice: </td><td><input type=\"TEXT\" name =\"ProductPrice\"></td></tr>");
        out.println("<tr><td>RetailerName: </td><td><input type=\"TEXT\" name =\"RetailerName\"></td></tr>");
        out.println("<tr><td>RetailerZip: </td><td><input type=\"TEXT\" name =\"RetailerZip\"></td></tr>");
        out.println("<tr><td>RetailerCity: </td><td><input type=\"TEXT\" name =\"RetailerCity\"></td></tr>");
        out.println("<tr><td>RetailerState: </td><td><input type=\"TEXT\" name =\"RetailerState\"></td></tr>");
        out.println("<tr><td>ProductOnSale: </td><td><input type=\"TEXT\" name =\"ProductOnSale\"></td></tr>");
        out.println("<tr><td>ManufacturerName: </td><td><input type=\"TEXT\" name =\"mname\"></td></tr>");
        out.println("<tr><td>ManufacturerRebate: </td><td><input type=\"TEXT\" name =\"mrebate\"></td></tr>");
        out.println("<tr><td>Userid: </td><td><input type=\"TEXT\" name =\"uid\"></td></tr>");
        out.println("<tr><td>UserAge: </td><td><input type=\"TEXT\" name =\"age\"></td></tr>");
        out.println("<tr><td>UserGender: </td><td><input type=\"TEXT\" name =\"gender\"></td></tr>");
        out.println("<tr><td>UserOccupation: </td><td><input type=\"TEXT\" name =\"occupation\"></td></tr>");


        out.println("<tr><td>ReviewDate: </td><td><input type=\"TEXT\" name =\"rdate\"></td></tr>");
        out.println("</td></tr>");

        out.println("<tr><td>Review Rating<select name=\"rating\">");
        out.println("<option value=\"1\">1</option>");
        out.println("<option value=\"2\">2</option>");
        out.println("<option value=\"3\">3</option>");
        out.println("<option value=\"4\">4</option>");
        out.println("<option value=\"5\">5</option>");
        out.println("</select></td></tr>");
        //out.println("<tr><td>Review TEXT:</td><td><input type=");
        //out.println("\"TEXT\"name = \"reviewtext\" size=\"50\"</td> </tr>");
        out.println("\n<tr><td>ReviewText</td>\n"+
											"<td><textarea name=\"reviewText\" rows=\"2\" cols=\"40\"></textarea></td></tr>\n");
    		out.println("<tr><td colspan='2'>");
    		out.println("<center><input type=\"submit\" value=\"Submt Review\" /></center>");
    		out.println("</td></tr></table></form>");

    		out.println("</div> \n");
    	//	out.println("</div> \n");
    		out.println("</div> \n");
    		out.println("</body>");
    		out.println("</html>");


  }
}

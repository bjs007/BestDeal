/*
 * product.java
 *
 */
 

import java.util.*;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

public class Mobile extends HttpServlet {
  
 public Map<String,Product> products = new  HashMap<String,Product>();
   
    /** 
     * Initializes the servlet with some usernames/password
    */  
    public void init() {
 try{
	//products = new  HashMap<String,ArrayList<String>>();
	
	String productXmlFileName = "C:/apache-tomcat-7.0.34/webapps/BestDeal/WEB-INF/classes/MobileCatalog.xml";
	SaxParser4BestDealProducts sx = new SaxParser4BestDealProducts(productXmlFileName);
    	System.out.println("hello");
	this.products = sx.prettyPrint();
	
	   }
	catch(Exception e){ e.printStackTrace();}
 
    }

    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
     
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
    	
		//Product prod = it.next();
		out.println("<html>");
        	out.println("<head>");
		out.println("<title>spigot - Free CSS Template by ZyPOP</title>");
		out.println("<title>spigot - Free CSS Template by ZyPOP</title>");
		out.println("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
		out.println("</head>");
		out.println("<body>");
		out.println("<div id=\"container\">");
		
		
			
			out.println("<header>");
			out.println("<h1 style=\"color:red\">Best<span>Deal</span></h1>");
			out.println("<h2 style=\"color:blue\"><I> Better than it is not possible</I></h2>");
			out.println("</header>");
		
		
			 String formURL =
        "/BestDeal/OrderPage";
		  formURL = response.encodeURL(formURL);
			for(String key: products.keySet()){
			Product prd = products.get(key);
			//ArrayList<String> detail = products.get(key);

			out.println("<FORM ACTION=\""+formURL +  "\">\n"+ 
			 "<INPUT TYPE=\"HIDDEN\" NAME=\"itemID\" " +
           "       VALUE=\"" + prd.getId()+"\">");
		   
			out.println("<table>");
				out.println("<tr>");
					out.println("<td>");
						out.println("<img src=" +request.getContextPath()+"/images/mobile/"+prd.getImage());
						out.println("alt=\"Product\" width=\"250\" height=\"200\">");
					out.println("</td>");
					
					out.println("<td>");
						out.println("<B>shortDescription:</B>"+prd.getShortDescription());
						out.println("<BR><B>Price: $</B>"+prd.getPrice());
					out.println("</td>");
					out.println("<td>");
			out.println("<INPUT TYPE=\"SUBMIT\" " +
			"VALUE=\"Add to Shopping Cart\">\n</FORM>");
			out.println("</td>");
				out.println("</tr>");
			//out.println(" <BR><button type=\"button\">Add to cart</button> ");
		
					
			
			out.println("</table>");
			
			}
			
			
			
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
 
	
	System.out.println("Im new mobile");
	
	
 
 
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

/*
 * product.java
 *
 */
 

import java.util.*;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

public class productManage extends HttpServlet {
  
 public HashMap<String,Product> laptop = new  HashMap<String,Product>();
 public HashMap<String,Product> mobile = new  HashMap<String,Product>();
 public HashMap<String,Product> tablet = new  HashMap<String,Product>();
 public HashMap<String,Product> tv = new  HashMap<String,Product>();
    /** 
     * Initializes the servlet with some usernames/password
    */  
    public void init() {
 try{
			String productXmlFileName;
			SaxParser4BestDealProducts  sx;
			String TOMCAT_HOME = "C:/apache-tomcat-7.0.34";
			productXmlFileName = TOMCAT_HOME+"/webapps/BestDeal/WEB-INF/classes/ProductCatalog.xml";
			sx = new SaxParser4BestDealProducts (productXmlFileName);
			this.laptop = sx.prettyPrint();
			
				
			productXmlFileName = "C:/apache-tomcat-7.0.34/webapps/BestDeal/WEB-INF/classes/MobileCatalog.xml";
			sx = new SaxParser4BestDealProducts (productXmlFileName);
			this.mobile = sx.prettyPrint();
			
			productXmlFileName = "C:/apache-tomcat-7.0.34/webapps/BestDeal/WEB-INF/classes/TabletCatalog.xml";
			sx = new SaxParser4BestDealProducts (productXmlFileName);
			this.tablet = sx.prettyPrint();
			
			productXmlFileName = "C:/apache-tomcat-7.0.34/webapps/BestDeal/WEB-INF/classes/TvCatalog.xml";
			sx = new SaxParser4BestDealProducts (productXmlFileName);
			this.tv = sx.prettyPrint();
		
	}
	catch(Exception e){ 
	e.printStackTrace();
	}
 
  }

    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
     
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
    	HttpSession session = request.getSession(true);
		
		
		
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
		out.println("<div align=\"right\">\n" );
				
				String userid = (String)session.getValue("userid");
				
						
				//out.println(userid);
				if(userid!=null){
				out.println("Welcome Back "+userid+"!" );	
				out.println("<a href= \"http://localhost/BestDeal/logout\">Logout</a>");
				
				
				}else{
				out.println("<a href=\"signIn.html\">Sign In</a>\n" +
				"<a href=\"createAccount.htm\"> Create account</a>\n");
				}
				out.println("</div>\n"); 
		String company  = null;
		HashMap<String,Product> products = new HashMap<String,Product>();
		String prodType = request.getParameter("prodType");
		company = request.getParameter("company");
		
	
		
		String imageFolder;
		//out.println(userid);
		
		if(prodType.equals("laptop")){
			imageFolder = "/images/laptop/";
			products = laptop;
		}
		else if(prodType.equals("tablet")){
			imageFolder = "/images/tablet/";
			products = tablet;
		}
		else if (prodType.equals("mobile")){
			products = mobile;
			imageFolder = "/images/mobile/";
		}
		else {
			products = tv;
			imageFolder = "/images/tv/";
		}
		
			
			
			String formURL =
        "/BestDeal/OrderPage";
		  formURL = response.encodeURL(formURL);
		  
		  if(company==null){
			  	
			for(String key: products.keySet()){
			Product prd = products.get(key);
			//ArrayList<String> detail = products.get(key);

			out.println("<FORM ACTION=\""+formURL +  "\">\n"+ 
			 "<INPUT TYPE=\"HIDDEN\" NAME=\"itemID\" " +
           "       VALUE=\"" + prd.getId()+"\">");
		   
				out.println("<table>");
					out.println("<tr>");
					out.println("<td>");
					out.println("<img src=" +request.getContextPath()+imageFolder+prd.getImage());
					out.println("alt=\"Product\" width=\"250\" height=\"200\">");
					out.println("</td>");
					
						out.println("<td>");
						out.println("<B>shortDescription:</B>"+prd.getShortDescription());
						out.println("<BR><B>Price: $</B>"+prd.getPrice());
					out.println("</td>");
					out.println("<td>");
			
			//out.println(userid);
			if(userid!=null)
				//if(userid == null)
			out.println("<INPUT TYPE=\"SUBMIT\" " + "VALUE=\"Add to Shopping Cart\">\n");
				else{
			out.println("<a href= \"http://localhost/BestDeal/signIn.html\">Login to buy</a>");
					
						
			
			out.println("</td>");
			out.println("</tr>");
			//out.println(" <BR><button type=\"button\">Add to cart</button>
			out.println("</table>");
				out.println("</FORM>");
				
				}
			}	
			}else{
				
				
				
				for(String key: products.keySet())
				
			{
			Product prd = products.get(key);
			//ArrayList<String> detail = products.get(key);
			if(prd.getName().equals(company)){
			out.println("<FORM ACTION=\""+formURL +  "\">\n"+ 
			 "<INPUT TYPE=\"HIDDEN\" NAME=\"itemID\" " +
           "       VALUE=\"" + prd.getId()+"\">");
		   
				out.println("<table>");
					out.println("<tr>");
					out.println("<td>");
					out.println("<img src=" +request.getContextPath()+imageFolder+prd.getImage());
					out.println("alt=\"Product\" width=\"250\" height=\"200\">");
					out.println("</td>");
					
						out.println("<td>");
						out.println("<B>shortDescription:</B>"+prd.getShortDescription());
						out.println("<BR><B>Price: $</B>"+prd.getPrice());
					out.println("</td>");
					out.println("<td>");
			
			//out.println(userid);
			if(userid!=null)
				//if(userid == null)
			out.println("<INPUT TYPE=\"SUBMIT\" " + "VALUE=\"Add to Shopping Cart\">\n");
				else
			out.println("<a href= \"http://localhost/BestDeal/signIn.html\">Login to buy</a>");
					
						
			
			out.println("</td>");
			out.println("</tr>");
			//out.println(" <BR><button type=\"button\">Add to cart</button>
			out.println("</table>");
			out.println("</FORM>");
				
			}	
				
				
			}
			
			
			
			
			}
			
			
			
			out.println("</div>");
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



import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;


public class removeProduct extends HttpServlet {

  public HashMap<String,Product> laptop = new  HashMap<String,Product>();
  public HashMap<String,Product> mobile = new  HashMap<String,Product>();
  public HashMap<String,Product> tablet = new  HashMap<String,Product>();
  public HashMap<String,Product> tv = new  HashMap<String,Product>();

  public HashMap<String,Product> laptopSession ;
  public HashMap<String,Product> mobileSession ;
  public HashMap<String,Product> tabletSession ;
  public HashMap<String,Product> tvSession ;



  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
    		HttpSession session = request.getSession(true);



        String productXmlFileName;
        SaxParser4BestDealProducts  sx;
        String TOMCAT_HOME = "C:/apache-tomcat-7.0.34";

        laptopSession = (HashMap<String,Product>)session.getValue("laptopSession");
        mobileSession = (HashMap<String,Product>)session.getValue("mobileSession");
        tabletSession = (HashMap<String,Product>)session.getValue("tabletSession");
        tvSession = (HashMap<String,Product>)session.getValue("tvSession");

       if(laptopSession == null){

        productXmlFileName = TOMCAT_HOME+"/webapps/BestDeal/WEB-INF/classes/ProductCatalog.xml";
        sx = new SaxParser4BestDealProducts (productXmlFileName);
        this.laptop = sx.prettyPrint();
        session.setAttribute("laptopSession",this.laptop);
        for(String key:laptop.keySet()){
          MySQLDataStoreUtilities.insertProduct(laptop.get(key),"laptop");
        }
      }else{
        this.laptop = laptopSession;
      }

        if(mobileSession == null){
        productXmlFileName = "C:/apache-tomcat-7.0.34/webapps/BestDeal/WEB-INF/classes/MobileCatalog.xml";
        sx = new SaxParser4BestDealProducts (productXmlFileName);
        this.mobile = sx.prettyPrint();
        session.setAttribute("mobileSession",this.mobile);
        for(String key:mobile.keySet()){
          MySQLDataStoreUtilities.insertProduct(mobile.get(key),"mobile");
        }
      }else{
        this.mobile = mobileSession;
      }

       if(tabletSession == null){
        productXmlFileName = "C:/apache-tomcat-7.0.34/webapps/BestDeal/WEB-INF/classes/TabletCatalog.xml";
        sx = new SaxParser4BestDealProducts (productXmlFileName);
        this.tablet = sx.prettyPrint();
        session.setAttribute("tabletSession",this.tablet);
        for(String key:tablet.keySet()){
          MySQLDataStoreUtilities.insertProduct(tablet.get(key),"tablet");
        }

      }else{
        this.tablet = tabletSession;
      }

      if(tvSession ==  null){
        productXmlFileName = "C:/apache-tomcat-7.0.34/webapps/BestDeal/WEB-INF/classes/TvCatalog.xml";
        sx = new SaxParser4BestDealProducts (productXmlFileName);
        this.tv = sx.prettyPrint();
        session.setAttribute("tvSession",this.tv);
        for(String key:tv.keySet()){
          MySQLDataStoreUtilities.insertProduct(tv.get(key),"tv");
        }
      }else{
         this.tv = tvSession;
      }



      String product_id = request.getParameter("product_id");
      String prod_Type = MySQLDataStoreUtilities.ProductType(product_id);



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

            if(prod_Type.equals("laptop")){
              MySQLDataStoreUtilities.deleteProduct(product_id);
              laptopSession.remove(product_id.toString());
              out.println("<h2 style=\"color:blue;\"> Product" +product_id+"Removed</h2>");
            }
            if(prod_Type.equals("mobile")){
              MySQLDataStoreUtilities.deleteProduct(product_id);
              mobileSession.remove(product_id.toString());
              out.println("<h2 style=\"color:blue;\"> Product" +product_id+"Removed</h2>");
            }
            if(prod_Type.equals("tablet")){
                  MySQLDataStoreUtilities.deleteProduct(product_id);
                 tabletSession.remove(product_id.toString());
                out.println("<h2 style=\"color:blue;\"> Product" +product_id+"Removed</h2>");
            }
            if(prod_Type.equals("tv")){
                MySQLDataStoreUtilities.deleteProduct(product_id);
              tvSession.remove(product_id.toString());
              out.println("<h2 style=\"color:blue;\"> Product" +product_id+"Removed</h2>");
            }

























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

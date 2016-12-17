import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author nbuser
 */
public class AutoCompleteServlet extends HttpServlet {

    private ServletContext context;
    private ProductData prodData = new ProductData();
    private HashMap<String,Product> products = prodData.getProducts();

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getParameter("action");
        String targetId = request.getParameter("id");
      //  System.out.println("id is "+targetId);
        StringBuffer sb = new StringBuffer();

        if (targetId != null) {
            targetId = targetId.trim().toLowerCase();
        } else {
            context.getRequestDispatcher("/error.jsp").forward(request, response);
        }

        boolean namesAdded = false;
        if (action.equals("complete")) {

            // check if user sent empty string
            if (!targetId.equals("")) {

                Iterator<String> it = products.keySet().iterator();

                while (it.hasNext()) {
                    String id = it.next();
                    Product prod = products.get(id);


                    if ( // targetId matches first name
                         prod.getName().toLowerCase().startsWith(targetId) ||
                         // targetId matches last name
                         prod.getShortDescription().toLowerCase().startsWith(targetId) ||
                         // targetId matches full name
                         prod.getName().toLowerCase().concat(" ")
                            .concat(prod.getShortDescription().toLowerCase()).startsWith(targetId)) {

                        sb.append("<product>");
                        sb.append("<id>" + prod.getId() + "</id>");
                        sb.append("<prodName>" + prod.getName() + "</prodName>");
                        sb.append("<shortDescription>" + prod.getShortDescription() + "</shortDescription>");
                        sb.append("</product>");
                      //  System.out.println(sb);
                      //  System.out.println();
                       namesAdded = true;
                    }
                }
            }

            if (namesAdded) {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<prods>" + sb.toString() + "</prods>");

            } else {
                //nothing to show
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        }

        if (action.equals("lookup")) {

            // put the target product in the request scope to display
            if ((targetId != null) && products.containsKey(targetId.trim())) {
              request.setAttribute("product_id", targetId);
              //  request.setAttribute("product_id", products.get(targetId));
                context.getRequestDispatcher("/productT").forward(request, response);
            }
        }
    }
}

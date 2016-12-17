import java.util.*;
import java.sql.*;

public class  AjaxUtility{


  static Connection conn = null;

static	Connection getConnection(){
		try{
			if(conn == null){
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","mysql");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
   return conn;
	}

public static HashMap<String,Product> getProducts(){

  HashMap<String,Product> products = new HashMap<String,Product>();

  Connection	conn = null;
  try{
    conn = getConnection();
    String queryString = "SELECT * FROM product";
    PreparedStatement pst = conn.prepareStatement(queryString);
    ResultSet rs = pst.executeQuery();
    while(rs.next()){
     String product_id = rs.getString(1);
     String retailer = rs.getString(2);
     String name = rs.getString(3);
     String short_desc = rs.getString(4);
     String image = rs.getString(5);
     Integer price = rs.getInt(6);
     String prodType = rs.getString(7);
     Product prod = new Product();
     prod.setId(product_id);
     prod.setRetailer(retailer);
     prod.setImage(image);
     prod.setShortDescription(short_desc);
     prod.setPrice(price);
     prod.setName(name);
     products.put(product_id,prod);
    }
 }catch(Exception e){
   e.printStackTrace();
 }
 return products;
}

}

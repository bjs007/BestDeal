import java.util.*;
import java.sql.*;
public class MySQLDataStoreUtilities{

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
	public  static void insertUser(User user){
		Connection conn;
		String userid = user.getUsername();
		String password = user.getPassword();
		String firstName = user.getfirstName();
		String lastName = user.getlastName();
		String email = user.getemail();
		String phone = user.getphone();
		String userType = user.getUserType();
		try{
    conn = getConnection();
		String queryString = "INSERT INTO USER(userid,password,firstName,lastName,email,phone,userType)"
		+ "VALUES(?,?,?,?,?,?,?);";
		PreparedStatement pst = conn.prepareStatement(queryString);
		pst.setString(1,userid);
		pst.setString(2,password);
		pst.setString(3,firstName);
		pst.setString(4,lastName);
		pst.setString(5,email);
		pst.setString(6,phone);
		pst.setString(7,userType);
		pst.execute();


		}catch(Exception e){

			e.printStackTrace();
		}
	}

	public static void insertProduct(Product product,String prod_Type ){
	 Connection	conn = null;


	 String id = product.getId();
	 String retailer = product.getRetailer();
	 String name = product.getName();
	 String shortDescription = product.getShortDescription();
	 String image = product.getImage();
	 Integer price = product.getPrice();

	 try{
	 conn = getConnection();
	 String queryString = "INSERT INTO product(product_id,retailer,name,short_desc,image,price,prod_Type)"
	 + "VALUES(?,?,?,?,?,?,?);";
	 PreparedStatement pst = conn.prepareStatement(queryString);
	 pst.setString(1,id);
	 pst.setString(2,retailer);
	 pst.setString(3,name);
	 pst.setString(4,shortDescription);
	 pst.setString(5,image);
	 pst.setInt(6,price);
	 pst.setString(7,prod_Type);
	 pst.execute();


	 }catch(Exception e){

		 e.printStackTrace();
	 }
 }

 public static void updateProduct(Product product ){
	 Connection	conn = null;


	 String product_id = product.getId();
	 String retailer = product.getRetailer();
	 String name = product.getName();
	 String short_desc = product.getShortDescription();
	 String image = product.getImage();
	 Integer price = product.getPrice();

	 try{
	 conn = getConnection();
	 String queryString = "update product set retailer = ?, name = ?,short_desc =?, image = ?, price = ? where product_id = ?";
	 PreparedStatement pst = conn.prepareStatement(queryString);
	 pst.setString(1,retailer);
	 pst.setString(2,name);
	 pst.setString(3,short_desc);
	 pst.setString(4,image);
	 pst.setInt(5,price);
	 pst.setString(6,product_id);
	 pst.executeUpdate();


	 }catch(Exception e){

		 e.printStackTrace();
	 }
 }


 public static boolean doesProductExist(String product_id){
			boolean found = false;

			try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","mysql");
			String queryString = "SELECT product FROM USER WHERE product_id = ?";
			PreparedStatement pst = conn.prepareStatement(queryString);
			pst.setString(1,product_id);

			ResultSet rs = pst.executeQuery();
			if (rs.next()){
				found = true;
			}

			}catch(Exception e){
				e.printStackTrace();
			}

			return found;
		}

	public static boolean deleteProduct(String product_id){
	Connection	conn = null;
	boolean deleted = false;
		try{
			conn = getConnection();
			String queryString = "Delete FROM product where product_id = ?";
			PreparedStatement pst = conn.prepareStatement(queryString);
			pst.setString(1,product_id);
			deleted = pst.execute();

	 }catch(Exception e){
		 e.printStackTrace();
	 }
	 return deleted;
 }

 public static boolean deleteProducts(){
 Connection	conn = null;
 boolean deleted = false;
	 try{
		 conn = getConnection();
		 String queryString = "truncate product";
		 PreparedStatement pst = conn.prepareStatement(queryString);

		 deleted = pst.execute();

	}catch(Exception e){
		e.printStackTrace();
	}
	return deleted;
}



	 public static int OrderCount(){
		Connection	conn = null;
			try{
				conn = getConnection();
				String queryString = "SELECT MAX(order_id) FROM OrderDetail";
			  PreparedStatement pst = conn.prepareStatement(queryString);
			  ResultSet rs = pst.executeQuery();
			  while(rs.next()){
				 return rs.getInt(1);
			  }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return 0;
	 }

	 public static Integer ProductCount(){
	 Connection	conn = null;
		 try{
			 conn = getConnection();
			 String queryString = "SELECT MAX(product_id) FROM product";
			 PreparedStatement pst = conn.prepareStatement(queryString);
			 ResultSet rs = pst.executeQuery();
			 while(rs.next()){
				return rs.getInt(1);
			 }
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}


	public static HashMap<String,Product> getProducts(){
        HashMap<String,Product> products = new HashMap<String,Product>();
			try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","mysql");
			String queryString = "SELECT * FROM product";
			PreparedStatement pst = conn.prepareStatement(queryString);


			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				String product_id = rs.getString("product_id");
				String retailer = rs.getString("retailer");
				String name = rs.getString("name");
				String short_desc = rs.getString("short_desc");
				String image = rs.getString("image");
				Integer price = rs.getInt("price");

				Product product = new Product();
				product.setId(product_id);
				product.setRetailer(retailer);
				product.setName(name);
				product.setShortDescription(short_desc);
				product.setImage(image);
				product.setPrice(price);
				products.put(product_id,product);
			}

			}catch(Exception e){
				e.printStackTrace();
			}

			return products;
		}

	public static void insertUserAccount(Account account){
	  Connection	conn = null;
		String userid = account.getuserid();
		String name = account.getName();
		String cardNum = account.getcardNum();
		String email = account.getemail();
		String phone = account.getphone();
		String cardType = account.getcardType();

		try{
		conn = getConnection();
		String queryString = "INSERT INTO accounts(username,Nameoncard,cardNum,phone,email,cardType)"
		+ "VALUES(?,?,?,?,?,?);";
		PreparedStatement pst = conn.prepareStatement(queryString);
		pst.setString(1,userid);
		pst.setString(2,name);
		pst.setString(3,cardNum);
		pst.setString(4,phone);
		pst.setString(5,email);
		pst.setString(6,cardType);
		pst.execute();


		}catch(Exception e){

			e.printStackTrace();
		}
	}

	public static String ProductType(String product_id){
 	 Connection	conn = null;
 		 try{
 			 conn = getConnection();
 			 String queryString = "SELECT prod_Type FROM product where product_id = ?";
 			 PreparedStatement pst = conn.prepareStatement(queryString);
 			 pst.setString(1,product_id);
 			 ResultSet rs = pst.executeQuery();
 			 while(rs.next()){
 				return rs.getString(1);
 			 }
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		return new String("");
 	}

		public static boolean doesExist(String userid){
		boolean found = false;

		try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","mysql");
		String queryString = "SELECT userid FROM USER WHERE USERID = ?";
		PreparedStatement pst = conn.prepareStatement(queryString);
		pst.setString(1,userid);


		ResultSet rs = pst.executeQuery();
		if (rs.next()){
			found = true;
		}

		}catch(Exception e){
			e.printStackTrace();
		}

		return found;
	}


	public static boolean doesExist(String userid,String password,String userType){
		boolean found = false;

		try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","mysql");
		String queryString = "SELECT userid FROM USER WHERE USERID = ? and USERTYPE = ? and password = ?";
		PreparedStatement pst = conn.prepareStatement(queryString);
		pst.setString(1,userid);
		pst.setString(2,userType);
		pst.setString(3,password);


		ResultSet rs = pst.executeQuery();
		if (rs.next()){
			found = true;
			//System.out.println("I'm here in Utility");
		}

		}catch(Exception e){
			e.printStackTrace();
		}

		return found;
	}
//public boolean insertPayment()

public static void insertOrderDetail(OrderDetail order){
	Connection	conn = null;
	Integer orderid = order.getId();
	String userid = order.getuserid();
	Integer price = order.getPrice();
	java.util.Date order_date = order.getOrderDate();
	java.util.Date delivery_date = order.getDeliveryDate();

	//ArrayList<ItemOrder> itmes = order.getItemsOrder();
	//java.util.Date order_date = new order
	java.sql.Date sqlDate_order = new java.sql.Date(order_date.getTime());
	java.sql.Date sqlDate_delivery = new java.sql.Date(delivery_date.getTime());
  insertItemsDetail(order);

	try{
	conn = getConnection();
	String queryString = "INSERT INTO OrderDetail(order_id,userid,total,order_date,delivery_date)"
	+ "VALUES(?,?,?,?,?);";
	PreparedStatement pst = conn.prepareStatement(queryString);
	pst.setInt(1,orderid);
	pst.setString(2,userid);
	pst.setInt(3,price);
	pst.setDate(4,sqlDate_order);
	pst.setDate(5,sqlDate_delivery);
	pst.execute();


	}catch(Exception e){

		e.printStackTrace();
	}
}




public static void insertItemsDetail(OrderDetail order){
	Connection	conn = null;
	Integer orderid = order.getId();
	ArrayList<ItemOrder> items = order.getItemsOrder();


	try{
	conn = getConnection();
  for(ItemOrder item:items){
	String id = item.getItem().getId();
	Integer itemID = Integer.parseInt(id.trim());
	String short_desc = item.getItem().getShortDescription();
  Integer price = order.getPrice();
	String queryString = "INSERT INTO orderitem(order_id,item_id,short_desc,unit_price)"
	+ "VALUES(?,?,?,?);";
	PreparedStatement pst = conn.prepareStatement(queryString);
	pst.setInt(1,order.getId());
	pst.setInt(2,itemID);
	pst.setString(3,short_desc);
	pst.setInt(4,price);
	pst.execute();
}

	}catch(Exception e){

		e.printStackTrace();
	}
}

public static HashMap<Integer,purchaseDetail> getOrderDetail(String userid){
	Connection	conn = null;

	HashMap<Integer,purchaseDetail> purchase_orders = new HashMap<Integer,purchaseDetail>();
  ArrayList<ItemSold> items = null;
	try{
	conn = getConnection();
	String queryString1 = "SELECT order_id,total,order_date,delivery_date from orderdetail where userid = ?";
  String queryString2 = "SELECT item_id,short_desc,unit_price from orderitem where order_id = ?";
	PreparedStatement pst = conn.prepareStatement(queryString1);
	pst.setString(1,userid);

	ResultSet rs = pst.executeQuery();
		while (rs.next()){
			System.out.println("userid" +userid);
			 items = new ArrayList<ItemSold>();
		   Integer order_id = rs.getInt(1);
		   Integer total = rs.getInt(2);
			 java.util.Date order_date = rs.getDate(3);
			 java.util.Date delivery_date = rs.getDate(4);
			 PreparedStatement pst1 = conn.prepareStatement(queryString2);
			 pst1.setInt(1,order_id);
			 ResultSet rs1 = pst1.executeQuery();
       while(rs1.next()){
				 Integer item_id = rs1.getInt(1);
				 String short_desc = rs1.getString(2);
				 Integer unit_price = rs1.getInt(3);
				 ItemSold item = new ItemSold(item_id,short_desc,unit_price);
				 items.add(item);
			 }
       purchaseDetail pd = new purchaseDetail(order_id,userid,items,order_date,delivery_date);
       purchase_orders.put(order_id,pd);
	  }

	}catch(Exception e){
		e.printStackTrace();
	}
	return purchase_orders;
}


public static HashMap<Integer,purchaseDetail> getOrderDetailOrderId(Integer orderid){
	Connection	conn = null;

	HashMap<Integer,purchaseDetail> purchase_orders = new HashMap<Integer,purchaseDetail>();
  ArrayList<ItemSold> items = null;
	try{
	conn = getConnection();
	String queryString1 = "SELECT order_id,userid,total,order_date,delivery_date from orderdetail where order_id = ?";
  String queryString2 = "SELECT item_id,short_desc,unit_price from orderitem where order_id = ?";
	PreparedStatement pst = conn.prepareStatement(queryString1);
	pst.setInt(1,orderid);

	ResultSet rs = pst.executeQuery();
		while (rs.next()){

			 items = new ArrayList<ItemSold>();
		   Integer order_id = rs.getInt(1);
			 String userid = rs.getString(2);
		   Integer total = rs.getInt(3);
			 java.util.Date order_date = rs.getDate(4);
			 java.util.Date delivery_date = rs.getDate(5);
			 PreparedStatement pst1 = conn.prepareStatement(queryString2);
			 pst1.setInt(1,order_id);
			 ResultSet rs1 = pst1.executeQuery();
       while(rs1.next()){
				 Integer item_id = rs1.getInt(1);
				 String short_desc = rs1.getString(2);
				 Integer unit_price = rs1.getInt(3);
				 ItemSold item = new ItemSold(item_id,short_desc,unit_price);
				 items.add(item);
			 }
       purchaseDetail pd = new purchaseDetail(order_id,userid,items,order_date,delivery_date);
       purchase_orders.put(order_id,pd);
	  }

	}catch(Exception e){
		e.printStackTrace();
	}
	return purchase_orders;
}




public static boolean DeleteOrderDetail(Integer order_id){
  boolean deleted = false;

	Connection	conn = null;

	try{
	conn = getConnection();
	String queryString = "DELETE FROM OrderDetail WHERE order_id = ?";
	PreparedStatement pst = conn.prepareStatement(queryString);
  pst.setInt(1,order_id);
	pst.execute();
  deleted = true;

	}catch(Exception e){

		e.printStackTrace();
	}

  DeleteItemsDetail(order_id);
	return deleted;

}

public static boolean DeleteItemsDetail(Integer order_id){
  boolean deleted = false;
	Connection	conn = null;
	try{
	conn = getConnection();


	String queryString = "delete from orderitem where order_id = ?;";
	PreparedStatement pst = conn.prepareStatement(queryString);
	pst.setInt(1,order_id);
	//pst.setInt(2,item);
	pst.execute();
	deleted = true;


	}catch(Exception e){

		e.printStackTrace();
	}
	return deleted;
}

public static java.util.Date getOrderDates(Integer order_id){
	Connection	conn = null;
	java.util.Date delivery_date = new java.util.Date();

	try{
	conn = getConnection();
	String queryString = "SELECT delivery_date from orderdetail where order_id = ?";

	PreparedStatement pst = conn.prepareStatement(queryString);
	pst.setInt(1,order_id);

	ResultSet rs = pst.executeQuery();
		while (rs.next()){
		delivery_date =(rs.getDate(1)) ;

			 }



	}catch(Exception e){
		e.printStackTrace();
	}
	return delivery_date;
}


public static void insertPurchaserDetail(purchaseDetail order){
	Connection	conn = null;
	Integer orderid = order.getId();
	String userid = order.getuserid();
	Integer price = order.getPrice();
	java.util.Date order_date = order.getOrderDate();
	java.util.Date delivery_date = order.getDeliveryDate();

	//ArrayList<ItemOrder> itmes = order.getItemsOrder();
	//java.util.Date order_date = new order
	java.sql.Date sqlDate_order = new java.sql.Date(order_date.getTime());
	java.sql.Date sqlDate_delivery = new java.sql.Date(delivery_date.getTime());
  insertPurchaseItemsDetail(order);

	try{
	conn = getConnection();
	String queryString = "INSERT INTO OrderDetail(order_id,userid,total,order_date,delivery_date)"
	+ "VALUES(?,?,?,?,?);";
	PreparedStatement pst = conn.prepareStatement(queryString);
	pst.setInt(1,orderid);
	pst.setString(2,userid);
	pst.setInt(3,price);
	pst.setDate(4,sqlDate_order);
	pst.setDate(5,sqlDate_delivery);
	pst.execute();


	}catch(Exception e){

		e.printStackTrace();
	}
}




public static void insertPurchaseItemsDetail(purchaseDetail order){
	Connection	conn = null;
	Integer orderid = order.getId();
	ArrayList<ItemSold> items = order.getItemsSold();


	try{
	conn = getConnection();
  for(ItemSold item:items){
	Integer order_id = item.getId();
	Integer itemID = item.getId();
	String short_desc = item.getShort_desc();
  Integer price = item.getPrice();
	String queryString = "INSERT INTO orderitem(order_id,item_id,short_desc,unit_price)"
	+ "VALUES(?,?,?,?);";
	PreparedStatement pst = conn.prepareStatement(queryString);
	pst.setInt(1,order_id);
	pst.setInt(2,itemID);
	pst.setString(3,short_desc);
	pst.setInt(4,price);
	pst.execute();
}

	}catch(Exception e){

		e.printStackTrace();
	}
}



public static void UpdatePurchaseItemsDetail(Integer orderid, Integer itemid,String desc,Integer price){
	Connection	conn = null;

	try{
	conn = getConnection();

	String queryString = "update orderitem set short_desc = ?, unit_price = ? where order_id = ? and item_id = ?";
	PreparedStatement pst = conn.prepareStatement(queryString);
	pst.setString(1,desc);
	pst.setInt(2,price);
	pst.setInt(3,orderid);
	pst.setInt(4,itemid);
	pst.executeUpdate();
  pst.close();

	}catch(Exception e){

		e.printStackTrace();
	}
}

	public static void main(String args[]){
//		Account a = new Account("bsharma","Bijay Sharma","3126787952","3126787952","bijays.nitdpg@gmail.com","Visa");
  //  MySQLDataStoreUtilities my = new MySQLDataStoreUtilities();
    //OrderDetail od = new OrderDetail(1234,"abc");
	//	HashMap<Integer,purchaseDetail> map =
	//	MySQLDataStoreUtilities.getOrderDates("bsharma");
	//	System.out.println(MySQLDataStoreUtilities.OrderCount());
	MySQLDataStoreUtilities.UpdatePurchaseItemsDetail(3,4,"samsung",200);
	 //System.out.println(delivery_date);

	 }


}

import java.util.*;
import java.io.*;
public class OrderTrack{


// The known platforms

	public  HashMap<String, Integer> orderList = null;
	
// polulate the data for the different platforms into HashMaps




// Polulate the data for Microsoft into HashMap

public void populateUserData(){
		Integer x= 2;
	writeUserOrder("bsharma",5);
			
}




public void  cancelUserOrder(String userid,Integer orderNumber){

	System.out.println(orderNumber);
	
	 HashMap<String, Integer> userOrder =  readUserCart();
	 Integer s = userOrder.get(userid);
	 System.out.println(s);
	 if(s!=null){
	
		 userOrder.put(userid,null);
		
	 }
	 
	 
	
	 
	 
    try{
		File file = new File("userOrderDataStore");
		
		String str = file.getAbsolutePath();
		File userCartDataStore = new File("C:/apache-tomcat-7.0.34/webapps/BestDeal/WEB-INF/classes/userOrderDataStore");
		FileOutputStream uos=new FileOutputStream(userCartDataStore);
        ObjectOutputStream oos=new ObjectOutputStream(uos);
        oos.writeObject(userOrder);
        oos.flush();
        oos.close();
		uos.close();
	//	userDataStore.close();
	
    }catch(Exception e){
		e.printStackTrace();
		System.out.println("Could NOT Write microsoft to GameSpeedDataStore ...");
	}

}


public void  writeUserOrder(String userid,Integer orderNumber){

	System.out.println(orderNumber);
	
	 HashMap<String, Integer> userOrder =  readUserCart();
	 Integer s = userOrder.get(userid);
	 System.out.println(s);
	 if(userOrder!=null){
	
		 userOrder.put(userid,orderNumber);
		// System.out.println("in cart2");
	 }else{
		 userOrder = new HashMap<String, Integer>();
		 userOrder.put(userid,orderNumber);
		 System.out.println("in cart3");
	 }
	 
	 
	
	 
	 
    try{
		File file = new File("userOrderDataStore");
		
		String str = file.getAbsolutePath();
		File userCartDataStore = new File("C:/apache-tomcat-7.0.34/webapps/BestDeal/WEB-INF/classes/userOrderDataStore");
		FileOutputStream uos=new FileOutputStream(userCartDataStore);
        ObjectOutputStream oos=new ObjectOutputStream(uos);
        oos.writeObject(userOrder);
        oos.flush();
        oos.close();
		uos.close();
	//	userDataStore.close();
	
    }catch(Exception e){
		e.printStackTrace();
		System.out.println("Could NOT Write microsoft to GameSpeedDataStore ...");
	}

}


// Read the HashMaps from the File GameSpeedDataStore

public  Integer readUserCart(String userid) {
	
 HashMap<String,Integer> userOrder = null ;
 Integer orderNumber =0;
 
  
  
    try{
		userOrder = new HashMap<String,Integer>();
		InputStream userCartDataStore = UserAccountSerializedDataStore.class.getResourceAsStream("userOrderDataStore");
       // File userDataStore=new File("UserDataStore");
       // FileInputStream uis=new FileInputStream(userDataStore);
        ObjectInputStream ois=new ObjectInputStream(userCartDataStore);

        userOrder =(HashMap<String,Integer>)ois.readObject();
		
		ois.close();
        
		orderNumber = userOrder.get(userid);
		
	/*	
        
        for(Map.Entry<String,User> m :usersStored.entrySet()){
            	System.out.println(m.getKey());
		User c = m.getValue();
		
		System.out.println("\t Firs Name : "+c.getfirstName());
		System.out.println("\t Last Name : "+c.getlastName());
		
		}*/
        
    }catch(Exception e){} 
	System.out.println("ordernumber:"+orderNumber);
	
	return orderNumber;
}






public  HashMap<String,Integer> readUserCart() {
	
  HashMap<String,Integer> userOrder = null ;
  Integer orderNumber = null;
 
 
  
    try{
		//userOrder = new HashMap<String,Integer>();
		InputStream userOrderDataStore = UserAccountSerializedDataStore.class.getResourceAsStream("userOrderDataStore");
       // File userDataStore=new File("UserDataStore");
       // FileInputStream uis=new FileInputStream(userDataStore);
        ObjectInputStream ois=new ObjectInputStream(userOrderDataStore);

        userOrder =(HashMap<String,Integer>)ois.readObject();
		
		ois.close();
        
	//	cart = usersCart.get("userid");
		
	/*	
        
        for(Map.Entry<String,User> m :usersStored.entrySet()){
            	System.out.println(m.getKey());
		User c = m.getValue();
		
		System.out.println("\t Firs Name : "+c.getfirstName());
		System.out.println("\t Last Name : "+c.getlastName());
		
		}*/
        
    }catch(Exception e){} 
	
	return userOrder;
}













// the testDrive method for SANITY tesing

public static void main(String args[]){

OrderTrack us = new OrderTrack();
	
	//User u = new User("bsharma","abc","Bijay","Sharma","bijays.nitdgp@gmail.com","3126787952");
	us.populateUserData();
	 Integer onum = us.readUserCart("bsharma");
	//us.populateUserData(u);
	//us.writeUserAccountDataStore();
	//us.readUserAccountDataStore();
	System.out.println(onum);

}



}
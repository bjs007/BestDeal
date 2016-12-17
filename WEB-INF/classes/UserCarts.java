import java.util.*;
import java.io.*;
public class UserCarts{


// The known platforms

	public  HashMap<String, ArrayList<ItemOrder>> carts = null;
	
// polulate the data for the different platforms into HashMaps




// Polulate the data for Microsoft into HashMap

public void populateUserData(){
		
	writeUserCart("bsharma",new ArrayList<ItemOrder>());
			
}




public void  writeUserCart(String userid,ArrayList<ItemOrder> carts){
	System.out.println("in cart");
	 HashMap<String, ArrayList<ItemOrder>> userCarts  =	readUserCart();
	 if(userCarts!=null){
		 userCarts.put(userid,carts);
	 }else{
		 userCarts = new HashMap<String, ArrayList<ItemOrder>>();
		 userCarts.put(userid,carts);
	 }
	 
	 
	 
    try{
		File file = new File("UserCartDataStore");
		
		String str = file.getAbsolutePath();
		File userCartDataStore = new File("C:/apache-tomcat-7.0.34/webapps/BestDeal/WEB-INF/classes/UserCartDataStore");
		FileOutputStream uos=new FileOutputStream(userCartDataStore);
        ObjectOutputStream oos=new ObjectOutputStream(uos);
        oos.writeObject(userCarts);
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

public  ArrayList<ItemOrder> readUserCart(String userid) {
	
 HashMap<String,ArrayList<ItemOrder>> usersCart = null ;
 ArrayList<ItemOrder> cart = new ArrayList<ItemOrder>();
 
  
  
    try{
		usersCart = new HashMap<String,ArrayList<ItemOrder>>();
		InputStream userCartDataStore = UserAccountSerializedDataStore.class.getResourceAsStream("UserCartDataStore");
       // File userDataStore=new File("UserDataStore");
       // FileInputStream uis=new FileInputStream(userDataStore);
        ObjectInputStream ois=new ObjectInputStream(userCartDataStore);

        usersCart =(HashMap<String,ArrayList<ItemOrder>>)ois.readObject();
		
		ois.close();
        
		cart = usersCart.get("userid");
		
	/*	
        
        for(Map.Entry<String,User> m :usersStored.entrySet()){
            	System.out.println(m.getKey());
		User c = m.getValue();
		
		System.out.println("\t Firs Name : "+c.getfirstName());
		System.out.println("\t Last Name : "+c.getlastName());
		
		}*/
        
    }catch(Exception e){} 
	
	return cart;
}






public  HashMap<String,ArrayList<ItemOrder>> readUserCart() {
	
 HashMap<String,ArrayList<ItemOrder>> usersCart = null ;
 
  
    try{
		usersCart = new HashMap<String,ArrayList<ItemOrder>>();
		InputStream userCartDataStore = UserAccountSerializedDataStore.class.getResourceAsStream("UserCartDataStore");
       // File userDataStore=new File("UserDataStore");
       // FileInputStream uis=new FileInputStream(userDataStore);
        ObjectInputStream ois=new ObjectInputStream(userCartDataStore);

        usersCart =(HashMap<String,ArrayList<ItemOrder>>)ois.readObject();
		
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
	
	return usersCart;
}













// the testDrive method for SANITY tesing

public static void main(String args[]){

UserCarts us = new UserCarts();
	
	//User u = new User("bsharma","abc","Bijay","Sharma","bijays.nitdgp@gmail.com","3126787952");
	us.populateUserData();
	 ArrayList<ItemOrder> s = us.readUserCart("bsharma");
	//us.populateUserData(u);
	//us.writeUserAccountDataStore();
	//us.readUserAccountDataStore();

}



}
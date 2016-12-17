import java.util.*;
import java.io.*;
public class DealMatch{
HashMap<String,Product> productMap =  null;
HashMap<String,String> deals;
public DealMatch(){
  productMap = new HashMap<String,Product>();
  deals = new HashMap<String,String>();
  productMap = MySQLDataStoreUtilities.getProducts();
}
public HashMap<String,String> getDeals(){return deals;}
public HashMap<String,Product> getMatchedProducts(){
System.out.println("size of deals is" + deals.size());
String TOMCAT_HOME = "C:/apache-tomcat-7.0.34";
HashMap<String,Product> selectedProducts = new HashMap<String,Product>();
String line = null;
              for(String key:productMap.keySet()){
               Product product = productMap.get(key);
                         if(selectedProducts.size()<2 && !selectedProducts.containsKey(key)){

                                    try{

                                       BufferedReader reader = new BufferedReader(new FileReader (new File(TOMCAT_HOME+"/webapps/BestDeal//DealMatches.txt")));
                                     line = reader.readLine();
                                     if(line==null)
                                     break;
                                               else{
                                                           do{
                                                             if(line.contains(product.getShortDescription()))
                                                                   {


                                                                     selectedProducts.put(key,product);
                                                                     deals.put(key,line);
                                                                  //   deals.add(line);


                                                                 }
                                                             }while((line = reader.readLine()) != null);
                                                 }
                                     }catch(Exception e){}

                          }

              }

              return  selectedProducts;
    }



}

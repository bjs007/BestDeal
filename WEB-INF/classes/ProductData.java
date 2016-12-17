import java.util.HashMap;

/**
 *
 * @author nbuser
 */
public class ProductData {

    private HashMap composers = new HashMap();
    private HashMap<String,Product> products = new HashMap<String,Product>();


    public HashMap<String,Product> getProducts() {
        return products;
    }

    public ProductData() {

    products =  AjaxUtility.getProducts();
    }
public static void main(String args[]){
 ProductData pd = new ProductData();
 HashMap<String,Product> map = pd.getProducts();

 for(String id : map.keySet())
 System.out.println(id);
}
}

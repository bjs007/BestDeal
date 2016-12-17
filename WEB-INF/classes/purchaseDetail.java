import java.util.*;
public class purchaseDetail{

  Integer id;
  String userid;
  Integer price;
  Date order_date;
  Date delivery_date;
  ArrayList<ItemSold> items;

  purchaseDetail(Integer id,String userid,ArrayList<ItemSold> items,Date order_date,Date delivery_date){
      this.id = id;
      this.userid = userid;
      this.items = items;
      this.price = 0;
      this.order_date = order_date;
      this.delivery_date = delivery_date;
      for(ItemSold item: items){
          this.price += item.getPrice();
      }
    }

  
    Integer getId(){
      return id;
    }

    String getuserid(){
      return userid;
    }
    ArrayList<ItemSold> getItemsSold(){
      return items;
    }
  Integer getPrice(){
    return price;
  }
  Date getOrderDate(){
    return order_date;
  }

  Date getDeliveryDate(){
    return delivery_date;
  }


}

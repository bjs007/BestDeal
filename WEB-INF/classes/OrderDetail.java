import java.util.*;
public class OrderDetail {
Integer id;
String userid;
Integer price;
Date order_date;
Date delivery_date;
ArrayList<ItemOrder> itemOrder;

OrderDetail(Integer id,String userid,ArrayList<ItemOrder> itemOrder,Date order_date,Date delivery_date){
    this.id = id;
    this.userid = userid;
    this.itemOrder = itemOrder;
    this.price = 0;
    this.order_date = order_date;
    this.delivery_date = delivery_date;
    for(ItemOrder item: itemOrder){
        this.price += item.getItem().getPrice();
    }
  }


  Integer getId(){
    return id;
  }

  String getuserid(){
    return userid;
  }
  ArrayList<ItemOrder> getItemsOrder(){
    return itemOrder;
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
